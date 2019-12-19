package com.seliverstov.shop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Controller;
import com.seliverstov.shop.models.Product;

import java.util.List;

@Controller
public class ProductRepositoryImpl implements ProductRepository<Product>{

    private final JdbcOperations jdbcOperations;

    @Autowired
    public ProductRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }
    @Override
    public List findByNameProduct() {
        List productName = jdbcOperations.queryForList("Select Product_Name from product");
        return productName;
    }
}
