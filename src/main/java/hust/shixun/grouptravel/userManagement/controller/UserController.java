package hust.shixun.grouptravel.userManagement.controller;

import hust.shixun.grouptravel.entities.Notes;
import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.entities.Product;
import hust.shixun.grouptravel.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping("/user/addOrder")
    @ResponseBody
    public boolean addOrder(@RequestParam(value = "createTime")Date createTime,
                            @RequestParam(value = "productId") int productId,
                            @RequestParam(value = "userId") int userId,
                            @RequestParam(value = "orderPrice") double orderPrice,
                            @RequestParam(value = "payTime") Date payTime,
                            @RequestParam(value = "status") int status,
                            @RequestParam(value = "PTid") String PTid,
                            @RequestParam(value = "pNum") int pNum,
                            @RequestParam(value = "currentDiscount") double currentDiscount,
                            @RequestParam(value = "travelTime") Date travelTime) {
        return userService.addOrder(new java.sql.Date(createTime.getTime()),productId,userId,orderPrice,payTime,0,null,0,0,new java.sql.Date(travelTime.getTime()));
    }

    @RequestMapping("/user/queryProductById")
    @ResponseBody
    public Product queryProductById(@RequestParam(value = "productId") int productId) {
        return userService.queryProductById(productId);
    }

    @RequestMapping("/user/queryProductByName")
    @ResponseBody
    public List<Product> queryProductByName(@RequestParam(value = "productName") String productName) {
        return userService.queryProductByName(productName);
    }

    @RequestMapping("/user/queryProductByProvince")
    @ResponseBody
    public List<Product> queryProductByProvince(@RequestParam(value = "provinceName") String provinceName) {
        List<Product> products = new ArrayList<>();
        List<Integer> cityList = userService.getCityList(provinceName);
        for(int cityId:cityList)
            products.addAll(userService.queryProductByCity(cityId));
        return products;
    }

    @RequestMapping("/user/queryOrdersWithUnpay")
    @ResponseBody
    public List<Order> queryOrdersWithUnpay(@RequestParam(value = "userId") int userId) {
        return userService.queryOrdersWithUnpay(userId);
    }

    @RequestMapping("/user/updateUnpayOrder")
    @ResponseBody
    public Boolean updateUnpayOrder(@RequestParam(value = "orderId") int orderId) {
        return userService.updateUnpayOrder(orderId);
    }

    @RequestMapping("/user/queryNotesByOrderId")
    @ResponseBody
    public List<Notes> queryNotesByOrderId(int orderId) {
        return userService.queryNotesByOrderId(orderId);
    }

    @RequestMapping("/user/addNotes")
    @ResponseBody
    public Boolean addNotes(@RequestParam(value = "title") String title,
                            @RequestParam(value = "content") String content,
                            @RequestParam(value = "writeTime") Date writeTime) {
        return userService.addNotes(title,content,new java.sql.Date(writeTime.getTime()));
    }

    @RequestMapping("/user/queryOrderPrice")
    @ResponseBody
    public double queryOrderPrice(@RequestParam(value = "orderId") int orderId) {
        return userService.queryOrderPrice(orderId);
    }

    @RequestMapping("/user/updateOrderPrice")
    @ResponseBody
    public Boolean updateOrderPrice(@RequestParam(value = "orderId") int orderId,
                                    @RequestParam(value = "orderPrice") double orderPrice) {
        return userService.updateOrderPrice(orderId,orderPrice);
    }

    @RequestMapping("/user/queryOrder")
    @ResponseBody
    public List<Order> queryOrder(@RequestParam(value = "orderId") int orderId) {
        return userService.queryOrder(orderId);
    }
}
