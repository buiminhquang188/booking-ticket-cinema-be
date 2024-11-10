package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.AllArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.statistic.*;
import org.cybersoft.bookingticketcinemabe.enums.Roles;
import org.cybersoft.bookingticketcinemabe.enums.ScreeningSeatStatus;
import org.cybersoft.bookingticketcinemabe.jooq.entity.tables.*;
import org.cybersoft.bookingticketcinemabe.payload.request.statistic.StatisticCriteria;
import org.cybersoft.bookingticketcinemabe.service.StatisticService;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.jooq.impl.DSL.*;

@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final DSLContext dsl;

    @Override
    public StatisticDTO getStatistic(StatisticCriteria statisticCriteria) {
        StatisticUserDTO statisticUser = this.getStatisticUser(statisticCriteria);
        StatisticCinemaDTO statisticCinema = this.getStatisticCinema(statisticCriteria);
        List<StatisticBranchDTO> statisticBranches = this.getStatisticBranch(statisticCriteria);
        List<StatisticScreeningDTO> statisticScreening = this.getStatisticScreening(statisticCriteria);

        return StatisticDTO.builder()
                .cinema(statisticCinema)
                .user(statisticUser)
                .branches(statisticBranches)
                .screenings(statisticScreening)
                .build();
    }

    private StatisticUserDTO getStatisticUser(StatisticCriteria statisticCriteria) {
        Condition condition = DSL.noCondition();

        if (statisticCriteria.getCreatedAtFrom() != null && statisticCriteria.getCreatedAtTo() != null) {
            condition = condition.and(User.USER.CREATED_AT.between(statisticCriteria.getCreatedAtFrom())
                    .and(statisticCriteria.getCreatedAtTo()));
        }

        if (statisticCriteria.getUpdatedAtFrom() != null && statisticCriteria.getUpdatedAtTo() != null) {
            condition = condition.and(User.USER.UPDATED_AT.between(statisticCriteria.getUpdatedAtFrom())
                    .and(statisticCriteria.getUpdatedAtTo()));
        }

        return this.dsl
                .select(
                        sum(when(User.USER.ROLE.eq(Roles.user.name()), 1).otherwise(0)).as("totalUser"),
                        sum(when(User.USER.ROLE.eq(Roles.admin.name()), 1).otherwise(0)).as("totalAdmin"),
                        sum(when(User.USER.ROLE.eq(Roles.super_admin.name()), 1).otherwise(0)).as("totalSuperAdmin"),
                        count().as("total")
                )
                .from(User.USER)
                .where(User.USER.ROLE.in(Roles.user.name(), Roles.admin.name(), Roles.super_admin.name()))
                .and(condition)
                .fetchInto(StatisticUserDTO.class)
                .getFirst();
    }

    private StatisticCinemaDTO getStatisticCinema(StatisticCriteria statisticCriteria) {
        Condition condition = DSL.noCondition();

        if (statisticCriteria.getCreatedAtFrom() != null && statisticCriteria.getCreatedAtTo() != null) {
            condition = condition.and(Cinema.CINEMA.CREATED_AT.between(statisticCriteria.getCreatedAtFrom())
                    .and(statisticCriteria.getCreatedAtTo()));
        }

        if (statisticCriteria.getUpdatedAtFrom() != null && statisticCriteria.getUpdatedAtTo() != null) {
            condition = condition.and(Cinema.CINEMA.UPDATED_AT.between(statisticCriteria.getUpdatedAtFrom())
                    .and(statisticCriteria.getUpdatedAtTo()));
        }

        return this.dsl.select(
                        count().as("total")
                )
                .from(Cinema.CINEMA)
                .where(condition)
                .fetchInto(StatisticCinemaDTO.class)
                .getFirst();
    }

    private List<StatisticBranchDTO> getStatisticBranch(StatisticCriteria statisticCriteria) {
        Condition condition = DSL.noCondition();

        if (statisticCriteria.getCreatedAtFrom() != null && statisticCriteria.getCreatedAtTo() != null) {
            condition = condition.and(Branch.BRANCH.CREATED_AT.between(statisticCriteria.getCreatedAtFrom())
                    .and(statisticCriteria.getCreatedAtTo()));
        }

        if (statisticCriteria.getUpdatedAtFrom() != null && statisticCriteria.getUpdatedAtTo() != null) {
            condition = condition.and(Branch.BRANCH.UPDATED_AT.between(statisticCriteria.getUpdatedAtFrom())
                    .and(statisticCriteria.getUpdatedAtTo()));
        }

        Result<?> result = this.dsl.select(
                        count().as("total"),
                        Districts.DISTRICTS.NAME.as("name")
                )
                .from(Branch.BRANCH)
                .join(Districts.DISTRICTS)
                .on(Branch.BRANCH.DISTRICT_ID.eq(Districts.DISTRICTS.ID))
                .where(condition)
                .groupBy(Districts.DISTRICTS.ID)
                .fetch();

        return result.into(StatisticBranchDTO.class);
    }

    private List<StatisticScreeningDTO> getStatisticScreening(StatisticCriteria statisticCriteria) {
        Condition condition = DSL.noCondition();

        if (statisticCriteria.getCreatedAtFrom() != null && statisticCriteria.getCreatedAtTo() != null) {
            condition = condition.and(Screening.SCREENING.CREATED_AT.between(statisticCriteria.getCreatedAtFrom())
                    .and(statisticCriteria.getCreatedAtTo()));
        }

        if (statisticCriteria.getUpdatedAtFrom() != null && statisticCriteria.getUpdatedAtTo() != null) {
            condition = condition.and(Screening.SCREENING.UPDATED_AT.between(statisticCriteria.getUpdatedAtFrom())
                    .and(statisticCriteria.getUpdatedAtTo()));
        }

        Result<?> result = this.dsl.select(
                        count().as("total"),
                        sum(when(ScreeningSeat.SCREENING_SEAT.IS_BOOKED.eq(ScreeningSeatStatus.EMPTY.getStatus()), 1).otherwise(0)).as("total_seats_empty"),
                        sum(when(ScreeningSeat.SCREENING_SEAT.IS_BOOKED.eq(ScreeningSeatStatus.BOOK.getStatus()), 1).otherwise(0)).as("total_seats_booked"),
                        Movie.MOVIE.NAME.as("movieName")
                )
                .from(Screening.SCREENING)
                .join(Movie.MOVIE)
                .on(Screening.SCREENING.MOVIE_ID.eq(Movie.MOVIE.ID))
                .join(ScreeningSeat.SCREENING_SEAT)
                .on(Screening.SCREENING.ID.eq(ScreeningSeat.SCREENING_SEAT.SCREENING_ID))
                .where(condition)
                .groupBy(Movie.MOVIE.ID)
                .fetch();

        return result.into(StatisticScreeningDTO.class);
    }
}
