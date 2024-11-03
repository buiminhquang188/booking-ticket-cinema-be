package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.minimal.MinimalBranchDTO;
import org.cybersoft.bookingticketcinemabe.dto.minimal.MinimalDTO;
import org.cybersoft.bookingticketcinemabe.dto.minimal.MinimalScreeningDTO;
import org.cybersoft.bookingticketcinemabe.jooq.entity.tables.*;
import org.cybersoft.bookingticketcinemabe.payload.request.minimal.MinimalCriteria;
import org.cybersoft.bookingticketcinemabe.query.dto.JooqPaginate;
import org.cybersoft.bookingticketcinemabe.query.mapper.JooqPaginateMapper;
import org.cybersoft.bookingticketcinemabe.query.utils.Helpers;
import org.cybersoft.bookingticketcinemabe.service.MinimalService;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MinimalServiceImpl implements MinimalService {
    private final DSLContext dsl;

    private final JooqPaginateMapper jooqPaginateMapper;

    @Override
    public PageableDTO<?> getScreenings(MinimalCriteria minimalCriteria) {
        Condition condition = DSL.noCondition();

        if (minimalCriteria.getSearch() != null) {
            condition = condition
                    .or(Movie.MOVIE.NAME.like('%' + minimalCriteria.getSearch() + '%'))
                    .or(Hall.HALL.NAME.like('%' + minimalCriteria.getSearch() + '%'))
                    .or(Branch.BRANCH.NAME.like('%' + minimalCriteria.getSearch() + '%'))
                    .or(Branch.BRANCH.ADDRESS.like('%' + minimalCriteria.getSearch() + '%'));
        }

        Result<?> result = Helpers.paginate(
                this.dsl,
                this.dsl.select(Screening.SCREENING.ID,
                                Screening.SCREENING.START_TIME,
                                Screening.SCREENING.END_TIME,
                                Movie.MOVIE.NAME.as("movieName"),
                                Hall.HALL.NAME.as("hallName"),
                                Branch.BRANCH.NAME.as("branchName"),
                                Branch.BRANCH.ADDRESS.as("branchAddress"))
                        .from(Screening.SCREENING)
                        .join(Movie.MOVIE)
                        .on(Screening.SCREENING.MOVIE_ID.eq(Movie.MOVIE.ID))
                        .join(Hall.HALL)
                        .on(Screening.SCREENING.HALL_ID.eq(Hall.HALL.ID))
                        .join(Branch.BRANCH)
                        .on(Hall.HALL.BRANCH_ID.eq(Branch.BRANCH.ID))
                        .where(Screening.SCREENING.STATUS.eq("not_reserved"), condition),
                new Field[]{Screening.SCREENING.ID},
                minimalCriteria.getPageLimit(),
                (minimalCriteria.getPageNo() - 1) * minimalCriteria.getPageLimit()
        );

        System.out.println(result);

        JooqPaginate pagination = this.jooqPaginateMapper.toPaginate(result, minimalCriteria);

        return PageableDTO.<List<MinimalScreeningDTO>>builder()
                .content(result.into(MinimalScreeningDTO.class))
                .pageSize(pagination.getPageSize())
                .pageNo(pagination.getPageNumber())
                .totalPages(pagination.getTotalPage())
                .totalItems(pagination.getTotalElement())
                .build();
    }

    @Override
    public PageableDTO<?> getMovies(MinimalCriteria minimalCriteria) {
        Condition condition = DSL.noCondition();

        if (minimalCriteria.getSearch() != null) {
            condition = condition.or(Movie.MOVIE.NAME.like('%' + minimalCriteria.getSearch() + '%'));
        }

        Result<?> result = Helpers.paginate(
                this.dsl,
                this.dsl.select(Movie.MOVIE.ID,
                                Movie.MOVIE.NAME)
                        .from(Movie.MOVIE)
                        .where(condition),
                new Field[]{Movie.MOVIE.ID},
                minimalCriteria.getPageLimit(),
                (minimalCriteria.getPageNo() - 1) * minimalCriteria.getPageLimit()
        );

        JooqPaginate pagination = this.jooqPaginateMapper.toPaginate(result, minimalCriteria);

        return PageableDTO.<List<MinimalDTO>>builder()
                .content(result.into(MinimalDTO.class))
                .pageSize(pagination.getPageSize())
                .pageNo(pagination.getPageNumber())
                .totalPages(pagination.getTotalPage())
                .totalItems(pagination.getTotalElement())
                .build();
    }

    @Override
    public PageableDTO<?> getBranches(MinimalCriteria minimalCriteria) {
        Condition condition = DSL.noCondition();

        if (minimalCriteria.getSearch() != null) {
            condition = condition
                    .or(Branch.BRANCH.NAME.like('%' + minimalCriteria.getSearch() + '%'))
                    .or(Branch.BRANCH.ADDRESS.like('%' + minimalCriteria.getSearch() + '%'));
        }

        Result<?> result = Helpers.paginate(
                this.dsl,
                this.dsl.select(Branch.BRANCH.ID,
                                Branch.BRANCH.NAME,
                                Branch.BRANCH.ADDRESS)
                        .from(Branch.BRANCH)
                        .where(condition),
                new Field[]{Branch.BRANCH.ID},
                minimalCriteria.getPageLimit(),
                (minimalCriteria.getPageNo() - 1) * minimalCriteria.getPageLimit()
        );

        JooqPaginate pagination = this.jooqPaginateMapper.toPaginate(result, minimalCriteria);

        return PageableDTO.<List<MinimalBranchDTO>>builder()
                .content(result.into(MinimalBranchDTO.class))
                .pageSize(pagination.getPageSize())
                .pageNo(pagination.getPageNumber())
                .totalPages(pagination.getTotalPage())
                .totalItems(pagination.getTotalElement())
                .build();
    }

    @Override
    public PageableDTO<List<MinimalDTO>> getCinemas(MinimalCriteria minimalCriteria) {
        Condition condition = DSL.noCondition();

        if (minimalCriteria.getSearch() != null) {
            condition = condition.or(Cinema.CINEMA.NAME.like('%' + minimalCriteria.getSearch() + '%'));
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

        JooqPaginate pagination = this.jooqPaginateMapper.toPaginate(select, minimalCriteria);

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

        if (minimalCriteria.getSearch() != null) {
            districtCondition = districtCondition
                    .or(Districts.DISTRICTS.NAME.like('%' + minimalCriteria.getSearch() + '%'));

            provinceCondition = provinceCondition
                    .or(Provinces.PROVINCES.NAME.like('%' + minimalCriteria.getSearch() + '%'));
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

        JooqPaginate pagination = this.jooqPaginateMapper.toPaginate(select, minimalCriteria);

        return PageableDTO.<List<MinimalDTO>>builder()
                .content(select.into(MinimalDTO.class))
                .pageSize(pagination.getPageSize())
                .pageNo(pagination.getPageNumber())
                .totalPages(pagination.getTotalPage())
                .totalItems(pagination.getTotalElement())
                .build();
    }
}
