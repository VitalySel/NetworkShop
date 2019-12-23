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
    public List getCurrentSupplyCount(int supplierId) {
        return jdbcTemplate.queryForList("SELECT count(*) cdata FROM networkstore.supply s WHERE s.idSupplier="+supplierId);
    }
    public List getCurrentSupplyDataStart(int supplierId) {
        return jdbcTemplate.queryForList("SELECT count(*) cdata FROM networkstore.supply s WHERE s.Data_End IS NULL && s.idSupplier="+supplierId);
    }
    public List getCurrentSupplyDataEnd(int supplierId) {
        return jdbcTemplate.queryForList("SELECT count(*) cdata FROM networkstore.supply s WHERE s.Data_Start IS NOT NULL AND s.Data_End IS NOT NULL && s.idSupplier=" +supplierId);
    }
    public List getCurrentSupplyStartEnd(int supplierId) {
        return jdbcTemplate.queryForList("SELECT s.Data_Start, count(s.Data_Start) cdata FROM networkstore.supply s WHERE s.idSupplier="+supplierId+ " GROUP BY s.Data_Start");
    }
    public List getCurrentSupplyEnd(int supplierId) {
        return jdbcTemplate.queryForList("SELECT s.Data_End, count(s.Data_End) cdata FROM networkstore.supply s WHERE s.idSupplier="+supplierId+ " GROUP BY s.Data_End");
    }
    public List getCurrentSupplyConsideration(int supplierId) {
        return jdbcTemplate.queryForList("SELECT count(*) cdata FROM networkstore.supply s WHERE s.Data_Start IS NULL && s.idSupplier=" +supplierId);
    }
    public List getFilterStartData(int supplierId, int filter) {
        return jdbcTemplate.queryForList("SELECT s.Data_Start, count(S.Data_Start) cdata FROM networkstore.supply s WHERE s.idSupplier = "+supplierId+" && month(s.Data_Start)="+filter);
    }
    public List getFilterEndData(int supplierId, int filter) {
        return jdbcTemplate.queryForList("SELECT s.Data_End, count(S.Data_End) cdata FROM networkstore.supply s WHERE s.idSupplier = "+supplierId+" && month(s.Data_End)="+filter);
    }
}
