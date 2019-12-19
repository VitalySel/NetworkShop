package com.seliverstov.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.seliverstov.shop.models.Order;
import com.seliverstov.shop.models.Product;
import com.seliverstov.shop.models.Shop;
import com.seliverstov.shop.models.Supplier;

import javax.sql.DataSource;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = {"/makeorder"}, method = RequestMethod.GET)
    public String showMakeOrder(@RequestParam String supplierid, @RequestParam String shopid, Model model) {
        Supplier supplier = new Supplier(dataSource);
        model.addAttribute("products",supplier.getSupplierProducts(Integer.parseInt(supplierid)));
        model.addAttribute("supplier",supplierid);
        model.addAttribute("shop",shopid);
        return "makeorder";
    }

    @RequestMapping(value = {"/makeorder"}, method = RequestMethod.POST)
    public String makeOrder(@RequestParam(name = "productid[]") List<String> productid, @RequestParam String ordertext, @RequestParam String supplierid, @RequestParam String shopid, Model model) {
        Order order = new Order(dataSource);
        order.create(productid,ordertext,supplierid,shopid);
        return "myshops";
    }

    @RequestMapping(value = {"/order"}, method = RequestMethod.GET)
    public String showMakeOrder(@RequestParam String orderid, Model model) {
        Order order = new Order(dataSource);
        Shop shop = new Shop(dataSource);
        model.addAttribute("shop", shop.getShopById(String.valueOf(order.getShopByOrderid(orderid))));
        model.addAttribute("supply", order.getSupplyInfoByOrderid(orderid));
        Product product = new Product(dataSource);
        model.addAttribute("products", product.getProductsByOrderId(orderid));
        model.addAttribute("res",product.sumByOrderid(orderid));
        return "order";
    }
}
