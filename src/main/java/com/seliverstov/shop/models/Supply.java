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

    public void startSupply(String supplyid){
        jdbcTemplate.execute("UPDATE networkstore.supply s SET s.Data_Start=CURRENT_DATE() WHERE s.idSupply="+supplyid);
        jdbcTemplate.execute("INSERT INTO logging VALUES (null, CONCAT('Started suppling, supplyid="+supplyid+"'), NOW());");

    }

    public void endSupply(String supplyid){
        jdbcTemplate.execute("UPDATE networkstore.supply s SET s.Data_End=CURRENT_DATE() WHERE s.idSupply="+supplyid);
        jdbcTemplate.execute("INSERT INTO logging VALUES (null, CONCAT('Ended suppling, supplyid="+supplyid+"'), NOW());");

    }
}
