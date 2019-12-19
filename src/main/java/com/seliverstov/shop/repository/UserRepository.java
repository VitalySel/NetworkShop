package com.seliverstov.shop.repository;

import com.seliverstov.shop.models.User;

import java.util.List;

public interface UserRepository <P extends User>{
    List findByUsername(String username);
    List findByEmail(String email);
}
