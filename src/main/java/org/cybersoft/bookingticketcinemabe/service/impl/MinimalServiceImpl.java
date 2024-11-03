package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.MinimalDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.entity.CinemaEntity;
import org.cybersoft.bookingticketcinemabe.entity.CinemaEntity_;
import org.cybersoft.bookingticketcinemabe.jooq.entity.tables.Districts;
import org.cybersoft.bookingticketcinemabe.jooq.entity.tables.Provinces;
import org.cybersoft.bookingticketcinemabe.mapper.MinimalMapper;
import org.cybersoft.bookingticketcinemabe.mapper.pagination.PageableMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.minimal.MinimalCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.minimal.MinimalDistrictCriteria;
import org.cybersoft.bookingticketcinemabe.query.CriteriaApiHelper;
import org.cybersoft.bookingticketcinemabe.query.dto.JooqPaginate;
import org.cybersoft.bookingticketcinemabe.query.impl.SelectQueryImpl;
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

import static org.jooq.impl.DSL.selectOne;

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
        org.cybersoft.bookingticketcinemabe.query.dto.Pageable pageable = org.cybersoft.bookingticketcinemabe.query.dto.Pageable.builder()
                .pageNumber(minimalCriteria.getPageNo())
                .pageSize(minimalCriteria.getPageLimit())
                .build();

        SelectQueryImpl<CinemaEntity> cinemas = this.criteriaApiHelper.select(CinemaEntity.class);

        if (minimalCriteria.getName() != null) {
            cinemas.like(CinemaEntity_.name, minimalCriteria.getName());
        }

        return new PageableMapper<>().toDTO(
                cinemas.findAll(pageable)
                        .map(minimalMapper::toCinemaMinimalDTO)
        );
    }

    @Override
    public PageableDTO<List<MinimalDTO>> getDistricts(MinimalDistrictCriteria minimalDistrictCriteria) {
        Condition districtCondition = DSL.noCondition();
        Condition provinceCondition = DSL.noCondition();

        if (minimalDistrictCriteria.getName() != null) {
            districtCondition = districtCondition.and(Districts.DISTRICTS.NAME.like('%' + minimalDistrictCriteria.getName() + '%'));
        }

        if (minimalDistrictCriteria.getProvinceName() != null) {
            provinceCondition = provinceCondition.and(Provinces.PROVINCES.NAME.like('%' + minimalDistrictCriteria.getProvinceName() + '%'));
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
                        .where(districtCondition)
                        .andExists(
                                selectOne()
                                        .from(Provinces.PROVINCES)
                                        .where(provinceCondition.and(Provinces.PROVINCES.ID.eq(Districts.DISTRICTS.PROVINCE_ID)))
                        ),
                new Field[]{Districts.DISTRICTS.ID},
                minimalDistrictCriteria.getPageLimit(),
                (minimalDistrictCriteria.getPageNo() - 1) * minimalDistrictCriteria.getPageLimit()
        );

        JooqPaginate pagination = jooqPaginateMapper.toPaginate(select, minimalDistrictCriteria);

        return PageableDTO.<List<MinimalDTO>>builder()
                .content(select.into(MinimalDTO.class))
                .pageSize(pagination.getPageSize())
                .pageNo(pagination.getPageNumber())
                .totalPages(pagination.getTotalPage())
                .totalItems(pagination.getTotalElement())
                .build();
    }
}
