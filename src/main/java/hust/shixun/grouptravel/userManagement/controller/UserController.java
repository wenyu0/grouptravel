package hust.shixun.grouptravel.userManagement.controller;


import com.sun.org.apache.xpath.internal.operations.Or;
import hust.shixun.grouptravel.adminManagement.entities.Admin;

import hust.shixun.grouptravel.entities.*;

import hust.shixun.grouptravel.imageUpload.service.ImageService;
import hust.shixun.grouptravel.itemsManagement.service.ProductService;
import hust.shixun.grouptravel.orderManagerment.service.OrderService;
import hust.shixun.grouptravel.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.soap.Node;
import java.util.*;

@Controller
public class UserController {
    @Autowired
    public UserService userService;

    @Autowired
    public ProductService productService;

    @Autowired
    public ImageService imageService;


    @RequestMapping("/user/addOrder")
    @ResponseBody
    public boolean addOrder(Order order) {
        int productId=order.getProductId();
        Product product=productService.queryProductById(productId);
        order.setOrderPrice(product.getPrice());
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
        List<Order> list=userService.queryOrdersWithUnpay(userId);
        return list;
    }

    @RequestMapping("/user/updateUnpayOrder")
    @ResponseBody
    public Boolean updateUnpayOrder(@RequestParam(value = "orderId") int orderId) {
        userService.setPayTimeByOrderId(orderId);
        return userService.updateUnpayOrder(orderId);
    }

    @RequestMapping("/user/queryNotesByUserId")
    @ResponseBody
    public List<Notes> queryNotesByUserId(int userId) {
        return userService.queryNotesByUserId(userId);
    }

//    前台
    @RequestMapping("/user/addNotes")
    @ResponseBody
    public Boolean addNotes(weixinUpLoad weixinUpLoad,@RequestParam(value = "notesImage") MultipartFile[] multipartFiles) {
        Notes notes=new Notes();
        notes.setTitle(weixinUpLoad.getTitle());
        notes.setContent(weixinUpLoad.getContent());
        notes.setWriteTime(weixinUpLoad.getWriteTime());
        notes.setProductId(weixinUpLoad.getProductId());
        notes.setRate(weixinUpLoad.getRate());
        boolean flag1= userService.addNotes(notes);
        int notesId=userService.queryNotesIdByNotes(notes);
        if (flag1){
            List<String> imgList = imageService.uploadPic(multipartFiles);
            for(String imgUrl:imgList){
                Image image = new Image(imgUrl);
                imageService.saveImg(image);
                int newImgID = imageService.queryImageId(imgUrl);
                imageService.saveNotesImg(newImgID,notesId);
//                在订单中添加游记关联
//                需要订单id
                userService.setOrderNotesId(weixinUpLoad.getOrderId(),notesId);
            }
            return true;
        }
        return false;
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

    //添加评论
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


// 后台
    @RequestMapping("/user/queryAllUser")
    public String queryAllUser(Model model){
        List<User> users = userService.queryAllUser();
        model.addAttribute("users",users);
        return "pages/userManage/userManagement";

    }

//后台
    @RequestMapping("/user/deleteUser{id}")
    public String deleteAdmin(@PathVariable Integer id){
        userService.deleteUserById(id);
        return "redirect:/user/queryAllUser";
    }

//    后台
    @PostMapping("/user/updateUser")
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/user/queryAllUser";
    }


    //后台
    @GetMapping("/user/updateUser{id}")
    public String updateUser(@PathVariable Integer id, Model model) {
        User user = userService.queryUserById(id);
        model.addAttribute("user",user);
        return "pages/userManage/userEdit";
    }

    //后台
    @RequestMapping("/user/addUser")
    public String addUser(User user){
        userService.addUser(user);
        return "redirect:/user/queryAllUser";
    }

    //后台
    @RequestMapping("/notes/queryNotesCommentsByNotesId{notesId}")
    public String queryNotesCommentsByNotesId(@PathVariable Integer notesId,Model model) {
        List<NotesComments> notesComments = userService.queryNotesCommentsByNotesId(notesId);
        model.addAttribute("notesComments",notesComments);
        return "pages/youjiManage/youjiAndComment";

    }

//    前端
    @RequestMapping("/notes/queryNotesCommentsByNotesId")
    @ResponseBody
    public Map<String, Object> queryNotesCommentsByNotesId1(Integer notesId) {
        Map<String,Object> map=new HashMap<>();
        List<NotesComments> notesComments = userService.queryNotesCommentsByNotesId(notesId);
        for (NotesComments comment:notesComments){
            int userId=comment.getUserId();
            User user=userService.queryUserById(userId);
            map.put(""+userId, user);
        }
        map.put("notesComments",notesComments);
        return map;

    }

    //后台
    @RequestMapping("/notes/queryAllNotes")
    public String queryAllNotes(Model model) {
        List<Notes> notes = userService.queryAllNotes();
        model.addAttribute("notes",notes);
        return "pages/youjiManage/youjiManagement";
    }


    public Notes queryNotesById(int id) {
        return userService.queryNotesById(id);
    }

    //后台
    @PostMapping("/notes/updateNotes")
    public String updateNotes(Notes notes) {
        userService.updateNotes(notes);
        return "redirect:/notes/queryAllNotes";
    }

    //后台
    @GetMapping("/notes/updateNotes{id}")
    public String updateNotes(@PathVariable Integer id,Model model) {
        Notes note = userService.queryNotesById(id);
        model.addAttribute("note",note);
        return "pages/youjiManage/youjiEdit";
    }

    //后台
    @RequestMapping("/notes/deleteNotes{id}")
    public String deleteNotesById(@PathVariable Integer id) {
        userService.deleteNotesById(id);
        return "redirect:/notes/queryAllNotes";
    }

//后台
    @RequestMapping("/notes/addNotes")
    public String addNotes1(Notes notes){
        userService.addNotes(notes);
        return "redirect:/notes/queryAllNotes";
    }

//后台调用
    @RequestMapping("/notes/queryNotesByProductId_")
    public String queryNotesByProductId(int productId,Model model){
        List<Notes> notes = userService.queryNotesByProductId(productId);
        model.addAttribute("notes",notes);
        return "/pages/youjiManage/youjiQuery";
    }

    //前台调用
    @RequestMapping("/notes/queryNotesByProductId")
    @ResponseBody
    public Map<String,Object> queryNotesByProductId1(int productId){
        Map<String,Object> map=new HashMap<>();
        List<Notes> notes = userService.queryNotesByProductId(productId);
//       用游记id查图片
        for(Notes note: notes){
            int notesId=note.getNotesId();
            List<String> list=userService.queryImgBynoteId(notesId);
            map.put(""+notesId, list);
        }
        map.put("notes",notes);
        return map;
    }

    //后台调用
    @RequestMapping("/notes/queryNotesByCityId")
    public String queryNotesByCityId(int cityId,Model model){
        List<Notes> notes = userService.queryCityNotes(cityId);
        model.addAttribute("notes",notes);
        return "/pages/youjiManage/youjiQuery";
    }
//前端通过城市名称调用城市游记
    @RequestMapping("/notes/queryNotesByName")
    @ResponseBody
    public Map<String,Object> queryNotesByCityId1(String cityName){
        Map<String,Object> map=new HashMap<>();
        int cityId=userService.queryCityIdByName(cityName);
        List<Notes> notes = userService.queryCityNotes(cityId);
        for (Notes note:notes){
            int noteId=note.getNotesId();
            List<String> urls=userService.queryImgBynoteId(noteId);
           if(urls!=null&&urls.size()!=0){
               map.put(String.valueOf(noteId), urls);
           }

        }
        map.put("notes",notes);
        return map;
    }

    //后台调用
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

    //    根据城市名称返回城市对应的图片路径
    @RequestMapping("/user/getimgByCityName")
    @ResponseBody
    public String getimgByCity(String name){
        return userService.getimgByCityName(name);
    }

    //    返回所有城市名称以及id
    @RequestMapping("/user/getAllCitys")
    @ResponseBody
    public List<City> getAllCitys(){
        return userService.getAllCitys();
    }

    /*
  订单状态
  0 下单未支付
  1 下单已支付（未出行）
  2 出行完成（未点评）
  3 订单完成
  4 退款中
  5 退款成功
  6、订单取消
 */

    @RequestMapping("/user/queryOrdersWith1")
    @ResponseBody
    public  List<Order> queryOrdersWith1(int userId) {
            List<Order> orders=userService.queryOrdersWith1(userId);
        return orders;
    }

    @RequestMapping("/user/updateOrder2")
    @ResponseBody
    public Boolean updateOrder2(int orderId) {
        return userService.updateOrder2(orderId);
    }

    @RequestMapping("/user/queryOrdersWith2")
    @ResponseBody
    public  List<Order> queryOrdersWith2(int userId) {
        return userService.queryOrdersWith2(userId);
    }

    @RequestMapping("/user/updateOrder3")
    @ResponseBody
    public Boolean updateOrder3(int orderId) {
        return userService.updateOrder3(orderId);
    }

    @RequestMapping("/user/queryOrdersWith3")
    @ResponseBody
    public  List<Order> queryOrdersWith3(int userId) {
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
    public  List<Order> queryOrdersWith5(int userId) {
        return userService.queryOrdersWith5(userId);
    }

    //订单超时取消
    @RequestMapping("/user/updateOrder6")
    @ResponseBody
    public Boolean updateOrder6(int orderId) {
        return userService.updateOrder6(orderId);
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



    //后台/addNotesImage{notesId}
    @RequestMapping("/addNotesImage{notesId}")
    public String addProductImage(@PathVariable Integer notesId,Model model){
        model.addAttribute("notesId",notesId);
        return "/pages/youjiManage/ad" +
                "dNoteImage";

    }

    //后台/deleteNotesCommentById{commentId}
    @RequestMapping("/deleteNotesCommentById{commentId}")
    public String deleteNotesCommentById(@PathVariable Integer commentId){
        userService.deleteNotesCommentById(commentId);
        return "pages/youjiManage/youjiAndComment";
    }

    @RequestMapping("/user/updateUnpayOrder_{orderId}")
    public String updateUnpayOrder_(@PathVariable Integer orderId){
        userService.updateOrder5(orderId);
        return "redirect:/order/queryAllOrders";
    }
}
