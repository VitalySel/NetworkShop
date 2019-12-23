package com.seliverstov.shop.models;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class Supply {

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public Supply(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public List getSuppliesBySupplierId(String supplierid){
        return jdbcTemplate.queryForList("SELECT * FROM networkstore.supply s, networkstore.order o WHERE s.idSupplier="+supplierid+ "&& o.idSupply = s.idSupply");
    }

    public List getSupplyStartDate(){
        return jdbcTemplate.queryForList("SELECT s.Data_Start, count(s.Data_Start) cdata FROM networkstore.supply s GROUP BY s.Data_Start");
    }

    public List getSupplyCount(){
        return jdbcTemplate.queryForList("SELECT count(*) cdata FROM networkstore.supply s");
    }

    public List getSupplyDataStartN(){
        return jdbcTemplate.queryForList("SELECT count(*) cdata FROM networkstore.supply s WHERE s.Data_End IS NULL");
    }

    public List getSupplyEndStartN(){
        return jdbcTemplate.queryForList("SELECT count(*) cdata FROM networkstore.supply s WHERE s.Data_Start IS NOT NULL AND s.Data_End IS NOT NULL");
    }

    public List getSupplyConsideration() {
        return jdbcTemplate.queryForList("SELECT count(*) cdata FROM networkstore.supply s WHERE s.Data_Start IS NULL");
    }

    public void startSupply(String supplyid){
        jdbcTemplate.execute("UPDATE networkstore.supply s SET s.Data_Start=CURRENT_DATE() WHERE s.idSupply="+supplyid);
        jdbcTemplate.execute("INSERT INTO logging VALUES (null, CONCAT('Started suppling, supplyid="+supplyid+"'), NOW());");

    }

    public void endSupply(String supplyid){
        jdbcTemplate.execute("UPDATE networkstore.supply s SET s.Data_End=CURRENT_DATE() WHERE s.idSupply="+supplyid);
        jdbcTemplate.execute("INSERT INTO logging VALUES (null, CONCAT('Ended suppling, supplyid="+supplyid+"'), NOW());");
    }
}
