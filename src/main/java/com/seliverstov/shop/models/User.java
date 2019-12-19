package com.seliverstov.shop.models;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.sql.DataSource;
import java.util.List;

public class User {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public User(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public void create(String name, String pass, String email){
        jdbcTemplate.execute("CALL addUser('"+name+"','"+email+"','"+pass+"');");
    }

    public String getUsername(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Object obj = auth.getPrincipal();
        String username = "";

        if (obj instanceof UserDetails) {
            username = ((UserDetails) obj).getUsername();
        } else {
            username = obj.toString();
        }
        return username;
    }

    public List getEmail(){
       return jdbcTemplate.queryForList("SELECT Email from user");
    }
    public List getuserName(){
        return jdbcTemplate.queryForList("SELECT Username from user");
    }

    public int getId(){
        return getIdByUsername(getUsername());
    }

    public int getIdByUsername(String username){
        int id = (int) jdbcTemplate.queryForList("SELECT idUser from user WHERE Username='"+username+"'").get(0).get("idUser");
        return id;
    }

    public List getAllUsers(){
        return jdbcTemplate.queryForList("SELECT * FROM user");
    }

    public List getRole(){
        return jdbcTemplate.queryForList("SELECT User_idUser from role");
    }

    public void makeAdmin(String userid){
        jdbcTemplate.execute("INSERT INTO role VALUES(null,'ROLE_ADMIN'," + userid + ");");
        jdbcTemplate.execute("INSERT INTO logging VALUES (null, CONCAT('Userid="+userid+" added to admins'), NOW());");
    }
}
