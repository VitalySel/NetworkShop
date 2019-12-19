package com.seliverstov.shop.models;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class Product {

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public Product(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public void create(String name, String desc){
        jdbcTemplate.execute("INSERT INTO product VALUES (null, '" +name+"','"+desc+ "');");
        jdbcTemplate.execute("INSERT INTO logging VALUES (null, CONCAT('Added product name="+name+ "'), NOW());");
    }

    public void add(String sellerId, String productId, String price){
        jdbcTemplate.execute("INSERT INTO supplierproducts VALUES ("+sellerId+", " +productId+","+price+ ");");
        jdbcTemplate.execute("INSERT INTO logging VALUES (null, CONCAT('Added supplierproducts sellerid="+sellerId+", productid=" +productId+", price="+price+ "'), NOW());");
    }

    public List getSupplierProducts(int supplierId){
        return jdbcTemplate.queryForList("SELECT * FROM supplierproducts WHERE idSupplier="+supplierId);
    }

    public List getProductsByOrderId(String orderid){
        return jdbcTemplate.queryForList("SELECT p.Product_Name name, s.price FROM productlist pl, product p, supplierproducts s WHERE pl.Order_idOrder="+orderid+ "&& p.idProduct = pl.Product_idProduct && s.idProduct = pl.Product_idProduct");
    }

    public List sumByOrderid(String orderid){
        return jdbcTemplate.queryForList("SELECT SUM(s.price) sum FROM productlist pl, product p, supplierproducts s WHERE pl.Order_idOrder="+orderid+ "&& p.idProduct = pl.Product_idProduct && s.idProduct = pl.Product_idProduct");
    }

    public List getProducts(){
        return jdbcTemplate.queryForList("SELECT * FROM product");
    }

}
