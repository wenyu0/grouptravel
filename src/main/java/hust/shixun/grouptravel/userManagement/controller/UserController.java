package hust.shixun.grouptravel.userManagement.controller;

import hust.shixun.grouptravel.entities.City;
import hust.shixun.grouptravel.entities.Notes;
import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.entities.Product;
import hust.shixun.grouptravel.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
@Controller
public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping("/user/addOrder")
    @ResponseBody
    public boolean addOrder(Order order) {
        return userService.addOrder(order);
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

//通过省份名查找产品
    @RequestMapping("/user/queryProductByProvince")
    @ResponseBody
    public List<Product> queryProductByProvince(@RequestParam(value = "provinceName") String provinceName) {
        List<Product> products = new ArrayList<>();
        List<Integer> cityList = userService.getCityList(provinceName);
        for(int cityId:cityList)
            products.addAll(userService.queryProductByCity(cityId));
        return products;
    }
    //通过省份名查找所属城市
    @RequestMapping("/user/querycitys")
    @ResponseBody
    public  List<Integer>  getCityList(String provinceName){
        return userService.getCityList(provinceName);
    }

//    通过城市id查找产品
    @RequestMapping("/user/queryProductByCity")
    @ResponseBody
    public  List<Product>  queryProductByCity(int cityId){
        return userService.queryProductByCity(cityId);
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

    @RequestMapping("/user/queryNotesByUserId")
    @ResponseBody
    public List<Notes> queryNotesByUserId(int userId) {
        return userService.queryNotesByUserId(userId);
    }

    @RequestMapping("/user/addNotes")
    @ResponseBody
    public Boolean addNotes(Notes notes) {
        return userService.addNotes(notes);
    }

    @RequestMapping("/user/queryOrderPrice")
    @ResponseBody
    public double queryOrderPrice(@RequestParam(value = "orderId") int orderId) {
        return userService.queryOrderPrice(orderId);
    }
///*更新ptid一样人的订单  orderPrice传入的是要砍的金额*/
//    @RequestMapping("/user/updateOrderPrice")
//    @ResponseBody
//    public Boolean updateOrderPrice(@RequestParam(value = "orderId") int orderId,
//                                    @RequestParam(value = "orderPrice") double orderPrice) {
//        return userService.updateOrderPrice(orderId,orderPrice);
//    }

    @RequestMapping("/user/queryOrder")
    @ResponseBody
    public List<Order> queryOrder(@RequestParam(value = "orderId") int orderId) {
        return userService.queryOrder(orderId);
    }

    @RequestMapping("/user/addRates")
    @ResponseBody
    public Boolean addRates(double rate, int orderId){
        return  userService.addRates(rate,orderId);
    }

    @RequestMapping("/user/likeNotes")
    @ResponseBody
    public Boolean likeNotes(int userId,int notesId){
        return userService.likeNotes(userId,notesId);
    }

    @RequestMapping("/user/commentNotes")
    @ResponseBody
    public Boolean commentNotes(int userId,int notesId,String commentContent){
        return userService.commentNotes(userId,notesId,commentContent);
    }

    @RequestMapping("/user/queryLikeNotes")
    @ResponseBody
    public List<Notes> queryLikeNotes(int userId){
        return userService.queryLikeNotes(userId);
    }

//    根据城市id返回城市对应的图片路径
    @RequestMapping("/user/getimgByCity")
    @ResponseBody
    public String getimgByCity(int cityId){
        return userService.getimgByCity(cityId);
    }

//    返回所有城市名称以及id
    @RequestMapping("/user/getAllCitys")
    @ResponseBody
    public List<City> getAllCitys(){
        return userService.getAllCitys();
    }

}
