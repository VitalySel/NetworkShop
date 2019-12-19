package com.seliverstov.shop.repository;

import com.seliverstov.shop.models.Shop;

import java.util.List;

public interface ShopRepository <P extends Shop> {

    List findByName();
    List findByPhone();
    List findByAddress();

}
