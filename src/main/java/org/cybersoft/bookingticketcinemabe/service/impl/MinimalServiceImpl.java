package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.MinimalDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.jooq.entity.tables.Cinema;
import org.cybersoft.bookingticketcinemabe.jooq.entity.tables.Districts;
import org.cybersoft.bookingticketcinemabe.jooq.entity.tables.Provinces;
import org.cybersoft.bookingticketcinemabe.mapper.MinimalMapper;
import org.cybersoft.bookingticketcinemabe.mapper.pagination.PageableMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.minimal.MinimalCriteria;
import org.cybersoft.bookingticketcinemabe.query.CriteriaApiHelper;
import org.cybersoft.bookingticketcinemabe.query.dto.JooqPaginate;
import org.cybersoft.bookingticketcinemabe.query.mapper.JooqPaginateMapper;
import org.cybersoft.bookingticketcinemabe.query.utils.Helpers;
import org.cybersoft.bookingticketcinemabe.repository.BranchRepository;
import org.cybersoft.bookingticketcinemabe.repository.MovieRepository;
import org.cybersoft.bookingticketcinemabe.repository.ScreeningRepository;
import org.cybersoft.bookingticketcinemabe.service.MinimalService;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MinimalServiceImpl implements MinimalService {
    private final ScreeningRepository screeningRepository;

    private final BranchRepository branchRepository;

    private final MovieRepository movieRepository;

    private final MinimalMapper minimalMapper;

    private final CriteriaApiHelper criteriaApiHelper;

    private final DSLContext dsl;

    private final JooqPaginateMapper jooqPaginateMapper;

    @Override
    public PageableDTO<?> getScreenings(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        Page<?> page = this.screeningRepository.findAll(pageable)
                .map(minimalMapper::toScreeningMinimalDTO);
        return new PageableMapper<>().toDTO(page);
    }

    @Override
    public PageableDTO<?> getMovies(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        Page<?> page = this.movieRepository.findAll(pageable)
                .map(minimalMapper::toMovieDetailDTO);
        return new PageableMapper<>().toDTO(page);
    }

    @Override
    public PageableDTO<?> getBranches(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        Page<?> page = this.branchRepository.findAll(pageable)
                .map(minimalMapper::toBranchMinimalDTO);
        return new PageableMapper<>().toDTO(page);
    }

    @Override
    public PageableDTO<List<MinimalDTO>> getCinemas(MinimalCriteria minimalCriteria) {
        Condition condition = DSL.noCondition();

        if (minimalCriteria.getName() != null) {
            condition = condition.or(Cinema.CINEMA.NAME.like('%' + minimalCriteria.getName() + '%'));
        }

        Result<?> select = Helpers.paginate(
                this.dsl,
                this.dsl.select(Cinema.CINEMA.ID,
                                Cinema.CINEMA.NAME)
                        .from(Cinema.CINEMA)
                        .where(condition),
                new Field[]{Cinema.CINEMA.ID},
                minimalCriteria.getPageLimit(),
                (minimalCriteria.getPageNo() - 1) * minimalCriteria.getPageLimit()
        );

        JooqPaginate pagination = jooqPaginateMapper.toPaginate(select, minimalCriteria);

        return PageableDTO.<List<MinimalDTO>>builder()
                .content(select.into(MinimalDTO.class))
                .pageSize(pagination.getPageSize())
                .pageNo(pagination.getPageNumber())
                .totalPages(pagination.getTotalPage())
                .totalItems(pagination.getTotalElement())
                .build();
    }

    @Override
    public PageableDTO<List<MinimalDTO>> getDistricts(MinimalCriteria minimalCriteria) {
        Condition districtCondition = DSL.noCondition();
        Condition provinceCondition = DSL.noCondition();

        if (minimalCriteria.getName() != null) {
            districtCondition = districtCondition
                    .or(Districts.DISTRICTS.NAME.like('%' + minimalCriteria.getName() + '%'));

            provinceCondition = provinceCondition
                    .or(Provinces.PROVINCES.NAME.like('%' + minimalCriteria.getName() + '%'));
        }

        Result<?> select = Helpers.paginate(
                this.dsl,
                this.dsl.select(
                                Districts.DISTRICTS.ID,
                                Districts.DISTRICTS.NAME,
                                Provinces.PROVINCES.NAME.as("provinceName")
                        )
                        .from(Districts.DISTRICTS)
                        .join(Provinces.PROVINCES)
                        .on(Districts.DISTRICTS.PROVINCE_ID.eq(Provinces.PROVINCES.ID))
                        .where(districtCondition.or(provinceCondition)),
                new Field[]{Districts.DISTRICTS.ID},
                minimalCriteria.getPageLimit(),
                (minimalCriteria.getPageNo() - 1) * minimalCriteria.getPageLimit()
        );

        JooqPaginate pagination = jooqPaginateMapper.toPaginate(select, minimalCriteria);

        return PageableDTO.<List<MinimalDTO>>builder()
                .content(select.into(MinimalDTO.class))
                .pageSize(pagination.getPageSize())
                .pageNo(pagination.getPageNumber())
                .totalPages(pagination.getTotalPage())
                .totalItems(pagination.getTotalElement())
                .build();
    }
}
