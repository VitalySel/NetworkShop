package com.seliverstov.shop.models;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class Supplier {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public Supplier(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public void create(String name, String address, String phone, int userId){
        jdbcTemplate.execute("INSERT INTO supplier VALUES (null, '" +name+ "','"+address+"','"+phone+"',"+ userId + ");");
        jdbcTemplate.execute("INSERT INTO logging VALUES (null, CONCAT('Added supplier, name="+name+"'), NOW());");

    }

    public List getUserSuppliers(int userid){
        return jdbcTemplate.queryForList("SELECT * FROM supplier WHERE User_idUser="+userid);
    }

    public List getAllSuppliers(){
        return jdbcTemplate.queryForList("SELECT * FROM supplier");
    }

    public List getSupplierProducts(int supplierid){
        return jdbcTemplate.queryForList("SELECT p.idProduct, p.Product_Name, p.Product_Description, sp.price FROM supplierproducts sp JOIN product p ON p.idProduct = sp.idProduct WHERE idSupplier="+supplierid);
    }
}
