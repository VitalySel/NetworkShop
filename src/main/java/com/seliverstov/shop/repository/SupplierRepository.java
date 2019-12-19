package com.seliverstov.shop.repository;

import com.seliverstov.shop.models.Supplier;

import java.util.List;

public interface SupplierRepository <P extends Supplier> {

    List findByNameSupplier();
    List findByAddressSupplier();
    List findByPhoneSupplier();
}
