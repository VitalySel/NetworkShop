package com.seliverstov.shop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Controller;
import com.seliverstov.shop.models.Supplier;

import java.util.List;

@Controller
public class SupplierRepositoryImpl implements SupplierRepository<Supplier> {

    private final JdbcOperations jdbcOperations;

    @Autowired
    public SupplierRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }
    @Override
    public List findByNameSupplier() {
        List nameSupp = jdbcOperations.queryForList("Select Name from supplier");
        return nameSupp;
    }

    @Override
    public List findByAddressSupplier() {
        List addressSupp = jdbcOperations.queryForList("Select Address from supplier");
        return addressSupp;
    }

    @Override
    public List findByPhoneSupplier() {
        List phoneSupp = jdbcOperations.queryForList("Select Phone from supplier");
        return phoneSupp;
    }
}
