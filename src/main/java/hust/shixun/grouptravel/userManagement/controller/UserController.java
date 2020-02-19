package hust.shixun.grouptravel.userManagement.controller;

import hust.shixun.grouptravel.entities.Notes;
import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.entities.Product;
import hust.shixun.grouptravel.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping("/user/addOrder")
    @ResponseBody
    public boolean addOrder(Date createTime, int productId, int userId, double orderPrice, Date payTime, int status, String PTid, int pNum, double currentDiscount, Date travelTime) {
        return userService.addOrder(new java.sql.Date(createTime.getTime()),productId,userId,orderPrice,payTime,0,null,0,0,new java.sql.Date(travelTime.getTime()));
    }

    @RequestMapping("/user/queryProductById")
    @ResponseBody
    public Product queryProductById(int productId) {
        return userService.queryProductById(productId);
    }

    @RequestMapping("/user/queryProductByName")
    @ResponseBody
    public List<Product> queryProductByName(String productName) {
        return userService.queryProductByName(productName);
    }

    @RequestMapping("/user/queryProductByProvince")
    @ResponseBody
    public List<Product> queryProductByProvince(String provinceName) {
        List<Product> products = new ArrayList<>();
        List<Integer> cityList = userService.getCityList(provinceName);
        for(int cityId:cityList)
            products.addAll(userService.queryProductByCity(cityId));
        return products;
    }

    @RequestMapping("/user/queryOrdersWithUnpay")
    @ResponseBody
    public List<Order> queryOrdersWithUnpay(int userId) {
        return userService.queryOrdersWithUnpay(userId);
    }

    @RequestMapping("/user/updateUnpayOrder")
    @ResponseBody
    public Boolean updateUnpayOrder(int orderId) {
        return userService.updateUnpayOrder(orderId);
    }

    @RequestMapping("/user/queryNotesByOrderId")
    @ResponseBody
    public List<Notes> queryNotesByOrderId(int orderId) {
        return userService.queryNotesByOrderId(orderId);
    }

    @RequestMapping("/user/addNotes")
    @ResponseBody
    public Boolean addNotes(String title, String content, Date writeTime) {
        return userService.addNotes(title,content,new java.sql.Date(writeTime.getTime()));
    }

    @RequestMapping("/user/queryOrderPrice")
    @ResponseBody
    public double queryOrderPrice(int orderId) {
        return userService.queryOrderPrice(orderId);
    }

    @RequestMapping("/user/updateOrderPrice")
    @ResponseBody
    public Boolean updateOrderPrice(int orderId, double orderPrice) {
        return userService.updateOrderPrice(orderId,orderPrice);
    }

    @RequestMapping("/user/queryOrder")
    @ResponseBody
    public List<Order> queryOrder(int orderId) {
        return userService.queryOrder(orderId);
    }
}
