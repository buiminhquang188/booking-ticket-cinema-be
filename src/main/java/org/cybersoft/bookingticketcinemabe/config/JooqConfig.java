package org.cybersoft.bookingticketcinemabe.config;

import lombok.AllArgsConstructor;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class JooqConfig {
    private final DataSource dataSource;

    @Bean
    public DefaultConfiguration jooqConfiguration() {
        DefaultConfiguration configuration = new DefaultConfiguration();
        configuration.set(SQLDialect.MYSQL);
        configuration.set(new DefaultExecuteListenerProvider(new SqlLoggingListener()));
        configuration.set(dataSource);
        return configuration;
    }
}