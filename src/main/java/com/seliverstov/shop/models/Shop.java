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

    public List getShopName() {
        return jdbcTemplate.queryForList("SELECT Name from shop");
    }
    public List getShopAddres() {
        return jdbcTemplate.queryForList("SELECT Address from shop");
    }
    public List getShopPhone() {
        return jdbcTemplate.queryForList("SELECT Phone from shop");
    }
    public List getShopUserId() {
        return jdbcTemplate.queryForList("SELECT User_idUser from shop");
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
}
