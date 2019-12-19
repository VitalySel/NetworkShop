package com.seliverstov.shop.repository;

import com.seliverstov.shop.models.Product;

import java.util.List;

public interface ProductRepository <P extends Product> {
    List findByNameProduct();
}
