package com.seliverstov.shop.models;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class Shop {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public Shop(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }


    public void create(String name, String address, String phone, int userId){
        jdbcTemplate.execute("INSERT INTO shop VALUES (null, '" +name+ "','"+address+"','"+phone+"',"+ userId + ");");
        jdbcTemplate.execute("INSERT INTO logging VALUES (null, CONCAT('Added shop, name="+name+"'), NOW());");
    }

    public List getUserShops(int userid){
        return jdbcTemplate.queryForList("SELECT * FROM shop WHERE User_idUser="+userid);
    }

    public List getShopById(String shopid){
        return jdbcTemplate.queryForList("SELECT * FROM shop WHERE idShop="+shopid);
    }

    public List getShopOrders(String shopid){
        return jdbcTemplate.queryForList("SELECT o.idOrder, o.Order_Text, (SELECT COUNT(*) FROM productlist p WHERE p.Order_idOrder = o.idOrder) count FROM networkstore.order o WHERE o.idShop="+shopid+";");
    }
    public List getCountOrders(String shopid) {
        return jdbcTemplate.queryForList("SELECT count(*) ord FROM networkstore.order o WHERE o.idShop ="+shopid);
    }
    public List getConsiderationOrders(String shopid) {
        return jdbcTemplate.queryForList("SELECT count(*) ord FROM networkstore.order o, supply s WHERE o.idShop ="+shopid +" && s.idSupply = o.idSupply && s.Data_Start IS NULL");
    }
    public List getStartOrders(String shopid) {
        return jdbcTemplate.queryForList("SELECT count(*) ord FROM networkstore.order o, supply s WHERE o.idShop ="+shopid +" && s.idSupply = o.idSupply && s.Data_End IS NULL");
    }
    public List getEndOrders(String shopid) {
        return jdbcTemplate.queryForList("SELECT count(*) ord FROM networkstore.order o, supply s WHERE o.idShop ="+shopid +" && s.idSupply = o.idSupply && s.Data_Start IS NOT NULL && s.Data_End IS NOT NULL");
    }
    public List getDataStartOrders(String shopid){
        return jdbcTemplate.queryForList("SELECT s.Data_Start, count(s.Data_Start) cdata FROM networkstore.order o, supply s WHERE o.idShop ="+shopid +" && s.idSupply = o.idSupply GROUP BY s.Data_Start");
    }
    public List getDataEndOrders(String shopid){
        return jdbcTemplate.queryForList("SELECT s.Data_End, count(s.Data_End) cdata FROM networkstore.order o, supply s WHERE o.idShop ="+shopid +" && s.idSupply = o.idSupply GROUP BY s.Data_End");
    }
    public List getTotalPrice(String shopid) {
        return jdbcTemplate.queryForList("SELECT SUM(sl.price) sum FROM networkstore.order o, productlist pl, product p, supplierproducts sl WHERE o.idShop = "+shopid+" && pl.Order_idOrder = o.idOrder && p.idProduct = pl.Product_idProduct && sl.idProduct = pl.Product_idProduct");
    }
}
