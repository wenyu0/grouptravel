package hust.shixun.grouptravel.userManagement.service.serviceImpl;


import hust.shixun.grouptravel.config.TimeConfig;
import hust.shixun.grouptravel.entities.City;
import hust.shixun.grouptravel.entities.Notes;
import hust.shixun.grouptravel.entities.NotesComments;
import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.entities.Product;
import hust.shixun.grouptravel.entities.User;
import hust.shixun.grouptravel.userManagement.mapper.UserMapper;
import hust.shixun.grouptravel.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserMapper userMapper;

    @Override
    public int getProvinceId(int cityId) {
        return userMapper.getProvinceId(cityId);
    }

    @Override
    public List<Integer> getCityList(String provinceName) {
        return userMapper.getCityList(provinceName);
    }

//    后台下订单，加入计时器，2天未付款取消订单
    @Override
    public boolean addOrder(Order order) {
        TimeConfig.orderClose(order.getPTid());
        return userMapper.addOrder(order);
    }

    @Override
    public Product queryProductById(int productId) {
        return userMapper.queryProductById(productId);
    }

    @Override
    public List<Product> queryProductByName(String productName) {
        return userMapper.queryProductByName(productName);
    }

    @Override
    public List<Product> queryProductByCity(int cityId) {
        return userMapper.queryProductByCity(cityId);
    }

    @Override
    public List<Order> queryOrdersWithUnpay(int userId) {
        return userMapper.queryOrdersWithUnpay(userId);
    }

    @Override
    public Boolean updateUnpayOrder(int orderId) {
        return userMapper.updateUnpayOrder(orderId);
    }

    @Override
    public List<Notes> queryNotesByUserId(int orderId) {
        return userMapper.queryNotesByUserId(orderId);
    }

    @Override
    public Boolean addNotes(Notes notes) {
        return userMapper.addNotes(notes);
    }

    @Override
    public double queryOrderPrice(int orderId) {
        return userMapper.queryOrderPrice(orderId);
    }
//修改价格，拼团人数、折扣
    @Override
    public Boolean updateOrderPrice(int orderId, double orderPrice) {
        Order order =userMapper.selectOrderByOrderId(orderId);
        Double price=order.getOrderPrice();
        int pNum=order.getPNum();
        Double currentDiscount=order.getCurrentDiscount();
//       砍价金额超出原有金额
        if(orderPrice>=price){
            return false;
        }
        // 修改后的价格
        price-=orderPrice;
        pNum+=1;
        currentDiscount+=orderPrice;
        return userMapper.updateOrderPrice(orderId,price,pNum,currentDiscount);
    }

    @Override
    public List<Order> queryOrder(int orderId) {
        return userMapper.queryOrder(orderId);
    }

    @Override
    public List<Order> queryOrderByPTid(int PTid) {
        return userMapper.queryOrderByPTid(PTid);
    }

    @Override
    public Boolean addRates(double rate, int orderId) {
        return userMapper.addRates(rate, orderId);
    }

    @Override
    public Boolean likeNotes(int userId, int notesId) {
        return userMapper.likeNotes(userId,notesId);
    }

    @Override
    public Boolean commentNotes(int userId, int notesId, String commentContent) {
        return userMapper.commentNotes(userId,notesId,commentContent);
    }

    @Override
    public List<Notes> queryLikeNotes(int userId) {
        return userMapper.queryLikeNotes(userId);
    }

    @Override
    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

    @Override
    public User queryUserById(int id) {
        return userMapper.queryUserById(id);
    }

    @Override
    public Boolean deleteUserById(int id) {
        int i = 0;
        try {
            i = userMapper.deleteUserById(id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        if (i>0)
            return true;
        else
            return false;
    }

    @Override
    public Boolean addUser(User user) {
        int i = 0;
        try {
            i = userMapper.addUser(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        if (i>0)
            return true;
        else
            return false;
    }

    @Override
    public Boolean updateUser(User user) {
        int i = 0;
        try {
            i = userMapper.updateUser(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        if (i>0)
            return true;
        else
            return false;
    }

    @Override
    public List<NotesComments> queryNotesCommentsByNotesId(int notesId) {
        return userMapper.queryNotesCommentsByNotesId(notesId);
    }

    @Override
    public List<Notes> queryAllNotes() {
        return userMapper.queryAllNotes();
    }

    @Override
    public Notes queryNotesById(int id) {
        return userMapper.queryNotesById(id);
    }

    @Override
    public Boolean updateNotes(Notes notes) {
        return userMapper.updateNotes(notes);
    }

    @Override
    public Boolean deleteNotesById(int id) {
        return deleteNotesById(id);
    }

    @Override
    public List<Notes> queryNotesByProductId(int productId) {
        return userMapper.queryNotesByProductId(productId);
    }

    @Override
    public Boolean deleteNotesCommentById(int commentId) {
        int i = 0;
        try {
            i = userMapper.deleteNotesCommentById(commentId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        if (i>0)
            return true;
        else
            return false;
    }


    public String getimgByCity(int cityId) {
        return userMapper.getimgByCity(cityId);
    }

    @Override
    public String getimgByCityName(String name) {
        return userMapper.getimgByCityName(name);
    }

    @Override
    public List<City> getAllCitys() {
        return userMapper.getAllCitys();
    }

    public List<Order> queryOrdersWith1(int userId) {
        return userMapper.queryOrdersWith1(userId);
    }

    @Override
    public Boolean updateOrder2(int orderId) {
        return userMapper.updateOrder2(orderId);
    }

    @Override
    public List<Order> queryOrdersWith2(int userId) {
        return userMapper.queryOrdersWith2(userId);
    }

    @Override
    public Boolean updateOrder3(int orderId) {
        return userMapper.updateOrder3(orderId);
    }

    @Override
    public List<Order> queryOrdersWith3(int userId) {
        return userMapper.queryOrdersWith3(userId);
    }

    @Override
    public Boolean updateOrder4(int orderId) {
        return userMapper.updateOrder4(orderId);
    }

    @Override
    public List<Order> queryOrdersWith4(int userId) {
        return userMapper.queryOrdersWith4(userId);
    }

    @Override
    public Boolean updateOrder5(int orderId) {
        return userMapper.updateOrder5(orderId);
    }

    @Override
    public List<Order> queryOrdersWith5(int userId) {
        return userMapper.queryOrdersWith5(userId);
    }

    @Override
    public Boolean updateOrder6(int orderId) {
        return userMapper.updateOrder6(orderId);
    }

    @Override
    public List<Notes> queryCityNotes(int cityId) {
        return userMapper.queryCityNotesByCityId(cityId);
    }

    @Override
    public List<NotesComments> queryNotesCommentsByUserId(int userId) {
        return userMapper.queryNotesCommentsByUserId(userId);
    }

    @Override
    public int queryCityIdByName(String cityName) {
        return  userMapper.queryCityIdByName(cityName);
    }

    @Override
    public List<String> queryImgBynoteId(int noteId) {
        return userMapper.queryImgBynoteId(noteId);
    }

    @Override
    public int queryNotesIdByNotes(Notes notes) {
        return userMapper.queryNotesIdByNotes(notes);
    }

    @Override
    public boolean setOrderNotesId(int orderId, int notesId) {
        return userMapper.setOrderNotesId(orderId,notesId);
    }

}
