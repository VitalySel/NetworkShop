package com.seliverstov.shop.models;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.List;

public class Order {

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public Order(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public List getByOrderid(String orderid){
        return jdbcTemplate.queryForList("SELECT * FROM order WHERE idOrder="+orderid);
    }

    public Object getShopByOrderid(String orderid){
        return jdbcTemplate.queryForList("SELECT idShop FROM networkstore.order WHERE idOrder="+orderid).get(0).get("idShop");
    }

    public Object getSupplyInfoByOrderid(String orderid){
        return jdbcTemplate.queryForList("SELECT * FROM networkstore.supply s, networkstore.order o, networkstore.supplier su WHERE o.idOrder="+ orderid +"&& o.idSupply=s.idSupply && su.idSupplier = s.idSupplier");
    }

    public void create(List<String> productid, String ordertext, String supplierid, String shopid){
        jdbcTemplate.execute("INSERT INTO supply VALUES (null, null, null, " + supplierid + ");");
        BigInteger lastid = (BigInteger) jdbcTemplate.queryForList("select @@IDENTITY").get(0).get("@@IDENTITY");
        jdbcTemplate.execute("INSERT INTO logging VALUES (null, CONCAT('Added supply, supplyid="+lastid+"'), NOW());");
        jdbcTemplate.execute("INSERT INTO `networkstore`.`order` (`idOrder`, `Placement_Date`, `Order_Text`, `idShop`, `idSupply`) VALUES (NULL, CURRENT_DATE(), '" + ordertext + "', '" + shopid + "', '"+ lastid + "');");
        lastid = (BigInteger) jdbcTemplate.queryForList("select @@IDENTITY").get(0).get("@@IDENTITY");
        jdbcTemplate.execute("INSERT INTO logging VALUES (null, CONCAT('Added order, orderid="+lastid+"'), NOW());");

        for (int i = 0; i < productid.size(); i++) {
            jdbcTemplate.execute("INSERT INTO productlist VALUES (" + lastid + ", "+ productid.get(i) + ");");
            jdbcTemplate.execute("INSERT INTO logging VALUES (null, CONCAT('Added product, product="+productid.get(i)+" , orderid=" +lastid+ "'), NOW());");

        }
    }

}
