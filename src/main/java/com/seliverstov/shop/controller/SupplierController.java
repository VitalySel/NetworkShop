package com.seliverstov.shop.controller;

import com.seliverstov.shop.repository.SupplierRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.seliverstov.shop.models.Supplier;
import com.seliverstov.shop.models.Supply;
import com.seliverstov.shop.models.User;

import javax.sql.DataSource;
import java.util.List;

@Controller
public class SupplierController {

    @Autowired
    DataSource dataSource;
    @Autowired
    SupplierRepositoryImpl supplierRepository;

    @RequestMapping(value="/mysuppliers")
    public String mySuppliers(Model model){
        User user = new User(dataSource);
        int id = user.getId();
        Supplier mysuppliers = new Supplier(dataSource);
        model.addAttribute("suppliers", mysuppliers.getUserSuppliers(id));
        return "mysuppliers";
    }

    @RequestMapping(value = {"/addSupplier"}, method = RequestMethod.GET)
    public String showAddSupplier() {
        return "addSupplier";
    }

    @RequestMapping(value = {"/addSupplier" }, method = RequestMethod.POST)
    public String addSupplier(@RequestParam String suppliername, @RequestParam String phone, @RequestParam String address) {

        List suppName = supplierRepository.findByNameSupplier();
        List suppPhone = supplierRepository.findByPhoneSupplier();
        List suppAddress = supplierRepository.findByAddressSupplier();

        if (suppliername =="" || phone == "" || address == "" || suppName.contains(suppliername) || suppPhone.contains(phone) || suppAddress.contains(address)){
            return "406";
        }
        User user = new User(dataSource);
        int id = user.getId();
        Supplier mysuppliers = new Supplier(dataSource);
        mysuppliers.create(suppliername,address,phone,id);
        return "redirect:/mysuppliers";
    }

    @RequestMapping(value = {"/supplier"}, method = RequestMethod.GET)
    public String showSupplierProducts(@RequestParam String supplierid, Model model) {
        Supplier supplier = new Supplier(dataSource);
        model.addAttribute("products",supplier.getSupplierProducts(Integer.parseInt(supplierid)));
        model.addAttribute("supplier",supplierid);
        Supply supply = new Supply(dataSource);
        model.addAttribute("supplies",supply.getSuppliesBySupplierId(supplierid));
        return "supplier";
    }

    @RequestMapping(value = {"/startsupply" }, method = RequestMethod.POST)
    public String startSupply(@RequestParam String supplyid) {
        Supply supply = new Supply(dataSource);
        supply.startSupply(supplyid);
        return "redirect:/mysuppliers";
    }

    @RequestMapping(value = {"/endsupply" }, method = RequestMethod.POST)
    public String endSupply(@RequestParam String supplyid) {
        Supply supply = new Supply(dataSource);
        supply.endSupply(supplyid);
        return "redirect:/mysuppliers";
    }

    @RequestMapping(value = {"/choosesupplier"}, method = RequestMethod.GET)
    public String showChooseSupplier(@RequestParam String shopid, Model model) {
        Supplier supplier = new Supplier(dataSource);
        model.addAttribute("suppliers",supplier.getAllSuppliers());
        model.addAttribute("shopid",shopid);
        return "choosesupplier";
    }

    @RequestMapping(value = {"/choosesupplier"}, method = RequestMethod.POST)
    public String chooseSupplier(@RequestParam String shop, @RequestParam String supplier) {
        return "redirect:/makeorder?supplierid="+supplier+"&shopid="+shop;
    }
}
