package hust.shixun.grouptravel.userManagement.controller;


import hust.shixun.grouptravel.adminManagement.entities.Admin;

import hust.shixun.grouptravel.entities.City;

import hust.shixun.grouptravel.entities.Notes;
import hust.shixun.grouptravel.entities.NotesComments;
import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.entities.Product;
import hust.shixun.grouptravel.entities.User;
import hust.shixun.grouptravel.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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



    @RequestMapping("/user/queryAllUser")
    public String queryAllUser(Model model){
        List<User> users = userService.queryAllUser();
        model.addAttribute("users",users);
        return "pages/userManage/userManagement";

    }


    @RequestMapping("/user/deleteUser{id}")
    public String deleteAdmin(@PathVariable Integer id){
        userService.deleteUserById(id);
        return "redirect:/user/queryAllUser";
    }

    @PostMapping("/user/updateUser")
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/user/queryAllUser";
    }



    @GetMapping("/user/updateUser{id}")
    public String updateUser(@PathVariable Integer id, Model model) {
        User user = userService.queryUserById(id);
        model.addAttribute("user",user);
        return "pages/userManage/userEdit";
    }

    @RequestMapping("/user/addUser")
    public String addUser(User user){
        userService.addUser(user);
        return "redirect:/user/queryAllUser";
    }

    @RequestMapping("/notes/queryNotesCommentsByNotesId")
    public String queryNotesCommentsByNotesId(@PathVariable Integer notesId) {
         userService.queryNotesCommentsByNotesId(notesId);
         return null;

    }

    @RequestMapping("/notes/queryAllNotes")
    public String queryAllNotes(Model model) {
        List<Notes> notes = userService.queryAllNotes();
        model.addAttribute("notes",notes);
        return "pages/youjiManage/youjiManagement";
    }


    public Notes queryNotesById(int id) {
        return userService.queryNotesById(id);
    }

    @PostMapping("/notes/updateNotes")
    public String updateNotes(Notes notes) {
        userService.updateNotes(notes);
        return "redirect:/notes/queryAllNotes";
    }

    @GetMapping("/notes/updateNotes{id}")
    public String updateNotes(@PathVariable Integer id,Model model) {
        Notes note = userService.queryNotesById(id);
        model.addAttribute("note",note);
        return "pages/youjiManage/youjiEdit";
    }

    @RequestMapping("/notes/deleteNotes{id}")
    public String deleteNotesById(@PathVariable Integer id) {
        userService.deleteNotesById(id);
        return "redirect:/notes/queryAllNotes";
    }


    @RequestMapping("/notes/addNotes")
    public String addNotes1(Notes notes){
        userService.addNotes(notes);
        return "redirect:/notes/queryAllNotes";
    }

    @RequestMapping("/notes/queryNotesByProductId_")
    public String queryNotesByProductId(int productId,Model model){
        List<Notes> notes = userService.queryNotesByProductId(productId);
        model.addAttribute("notes",notes);
        return "/pages/youjiManage/youjiQuery";
    }

    @RequestMapping("/notes/queryNotesByCityId")
    public String queryNotesByCityId(int cityId,Model model){
        List<Notes> notes = userService.queryCityNotes(cityId);
        model.addAttribute("notes",notes);
        return "/pages/youjiManage/youjiQuery";
    }


    @RequestMapping("/user/queryNotesByUserId_")
    public String queryNotesByUserId(int userId,Model model) {
        List<Notes> notes = userService.queryNotesByUserId(userId);
        model.addAttribute("notes",notes);
        return "/pages/youjiManage/youjiQuery";
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


    @RequestMapping("/user/queryOrdersWith1")
    @ResponseBody
    public List<Order> queryOrdersWith1(int userId) {
        return userService.queryOrdersWith1(userId);
    }

    @RequestMapping("/user/updateOrder2")
    @ResponseBody
    public Boolean updateOrder2(int orderId) {
        return userService.updateOrder2(orderId);
    }

    @RequestMapping("/user/queryOrdersWith2")
    @ResponseBody
    public List<Order> queryOrdersWith2(int userId) {
        return userService.queryOrdersWith2(userId);
    }

    @RequestMapping("/user/updateOrder3")
    @ResponseBody
    public Boolean updateOrder3(int orderId) {
        return userService.updateOrder3(orderId);
    }

    @RequestMapping("/user/queryOrdersWith3")
    @ResponseBody
    public List<Order> queryOrdersWith3(int userId) {
        return userService.queryOrdersWith3(userId);
    }

    @RequestMapping("/user/updateOrder4")
    @ResponseBody
    public Boolean updateOrder4(int orderId) {
        return userService.updateOrder4(orderId);
    }

    @RequestMapping("/user/queryOrdersWith4")
    @ResponseBody
    public List<Order> queryOrdersWith4(int userId) {
        return userService.queryOrdersWith4(userId);
    }

    @RequestMapping("/user/updateOrder5")
    @ResponseBody
    public Boolean updateOrder5(int orderId) {
        return userService.updateOrder5(orderId);
    }

    @RequestMapping("/user/queryOrdersWith5")
    @ResponseBody
    public List<Order> queryOrdersWith5(int userId) {
        return userService.queryOrdersWith5(userId);
    }

    @RequestMapping("/user/queryCityNotes")
    @ResponseBody
    public List<Notes> queryCityNotes(int cityId) {
        return userService.queryCityNotes(cityId);
    }

    @RequestMapping("/user/queryNotesCommentsByUserId")
    @ResponseBody
    public List<NotesComments> queryNotesCommentsByUserId(int userId) {
        return userService.queryNotesCommentsByUserId(userId);
    }


}
