package com.seliverstov.shop.controller;

import com.seliverstov.shop.repository.ShopRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.seliverstov.shop.models.Shop;
import com.seliverstov.shop.models.User;

import javax.sql.DataSource;
import java.util.List;

@Controller
public class ShopController {

    @Autowired
    DataSource dataSource;
    @Autowired
    ShopRepositoryImpl shopRepository;

    @RequestMapping(value="/myshops")
    public String myShops(Model model){
        User user = new User(dataSource);
        int id = user.getId();
        Shop myshops = new Shop(dataSource);
        model.addAttribute("shops", myshops.getUserShops(id));
        return "myshops";
    }

    @RequestMapping(value = {"/addShop"}, method = RequestMethod.GET)
    public String showAddShop() {
        return "addShop";
    }

    @RequestMapping(value = {"/addShop" }, method = RequestMethod.POST)
    public String addShop(@RequestParam String shopname, @RequestParam String phone, @RequestParam String address) {
        List name = shopRepository.findByName();
        List phoneShop = shopRepository.findByPhone();
        List shopAddress = shopRepository.findByAddress();
        if (shopname == "" || phone == "" || address == "" || name.contains(shopname) || phoneShop.contains(phone) || shopAddress.contains(address)){
            return "406";
        }
        if (!ServiceController.checkPunct(shopname) || !ServiceController.checkPhone(phone)){
            return "404";
        }
        User user = new User(dataSource);
        int id = user.getId();
        Shop myshops = new Shop(dataSource);
        myshops.create(shopname,address,phone,id);
        return "redirect:/myshops";
    }

    @RequestMapping(value = {"/shop"}, method = RequestMethod.GET)
    public String showShopOrders(@RequestParam String shopid, Model model) {
        Shop shop = new Shop(dataSource);
        model.addAttribute("orders",shop.getShopOrders(shopid));
        model.addAttribute("shop",shopid);
        return "shop";
    }
}
