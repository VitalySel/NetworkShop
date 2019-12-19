package com.seliverstov.shop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import com.seliverstov.shop.models.User;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository<User> {

    private final JdbcOperations jdbcOperations;

    @Autowired
    public UserRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List findByUsername() {
        List usrname =  jdbcOperations.queryForList("Select Username from user");
        return usrname;
    }

    @Override
    public List findByEmail() {
        List eml = jdbcOperations.queryForList("Select Email from user");
        return eml;
    }

    @Override
    public List getRole() {
        List role = jdbcOperations.queryForList("SELECT User_idUser from role");
        return role;
    }
}
