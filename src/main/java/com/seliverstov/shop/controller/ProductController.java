package com.seliverstov.shop.controller;

import com.seliverstov.shop.repository.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.seliverstov.shop.models.Product;

import javax.sql.DataSource;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    DataSource dataSource;
    @Autowired
    ProductRepositoryImpl productRepository;

    @RequestMapping(value = {"/createProduct"}, method = RequestMethod.GET)
    public String showCreateProduct() {
        return "createProduct";
    }

    @RequestMapping(value = {"/createProduct" }, method = RequestMethod.POST)
    public String createProduct(@RequestParam String productname, @RequestParam String desc) {
        List nameProduct = productRepository.findByNameProduct();
        if (productname == "" || nameProduct.contains(productname)){
            return "406";
        }
        Product product = new Product(dataSource);
        product.create(productname,desc);
        return "mysuppliers";
    }

    @RequestMapping(value = {"/addproduct"}, method = RequestMethod.GET)
    public String showAddProduct(@RequestParam String supplierid, Model model) {
        model.addAttribute("supplier",supplierid);
        Product product = new Product(dataSource);
        model.addAttribute("products",product.getProducts());
        return "addproduct";
    }

    @RequestMapping(value = {"/addproduct" }, method = RequestMethod.POST)
    public String addProduct(@RequestParam String supplierid, @RequestParam String productid,@RequestParam String price) {
        double parsePrice = Double.parseDouble(price);
        if (parsePrice < 0){
            return "406";
        }
        Product product = new Product(dataSource);
        product.add(supplierid,productid,price);
        return "redirect:/mysuppliers";
    }
}
