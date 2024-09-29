package org.cybersoft.bookingticketcinemabe.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.cybersoft.bookingticketcinemabe.query.CriteriaApiHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CriteriaApiHelperConfiguration {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public CriteriaApiHelper criteriaApiHelper() {
        return new CriteriaApiHelper(entityManager);
    }
}
