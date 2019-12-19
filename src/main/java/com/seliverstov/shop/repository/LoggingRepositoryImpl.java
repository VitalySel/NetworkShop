package com.seliverstov.shop.repository;

import com.seliverstov.shop.models.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LoggingRepositoryImpl implements LoggingRepository<Logging> {

    private final JdbcOperations jdbcOperations;

    @Autowired
    public LoggingRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List getLogging() {
        List logg = jdbcOperations.queryForList("Select * from logging");
        return logg;
    }
}
