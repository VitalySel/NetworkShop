package com.seliverstov.shop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import com.seliverstov.shop.models.Shop;

import java.util.List;

@Repository
public class ShopRepositoryImpl implements ShopRepository<Shop> {

    private final JdbcOperations jdbcOperations;

    @Autowired
    public ShopRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }
    @Override
    public List findByName() {
        List shopName =jdbcOperations.queryForList("Select Name from shop");
        return shopName;
    }

    @Override
    public List findByPhone() {
        List shopPhone =jdbcOperations.queryForList("Select Phone from shop");
        return shopPhone;
    }

    @Override
    public List findByAddress() {
        List shopAddress =jdbcOperations.queryForList("Select Address from shop");
        return shopAddress;
    }
}
