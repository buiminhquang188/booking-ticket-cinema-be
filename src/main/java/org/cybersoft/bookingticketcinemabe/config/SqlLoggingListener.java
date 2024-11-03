package org.cybersoft.bookingticketcinemabe.config;

import org.jooq.ExecuteContext;
import org.jooq.ExecuteListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlLoggingListener implements ExecuteListener {
    private static final Logger logger = LoggerFactory.getLogger(SqlLoggingListener.class);

    @Override
    public void executeStart(ExecuteContext ctx) {
        logger.info("Executing SQL: {}", ctx.sql());
    }
}