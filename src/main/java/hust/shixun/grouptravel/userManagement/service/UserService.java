package hust.shixun.grouptravel.userManagement.service;

import hust.shixun.grouptravel.entities.City;
import hust.shixun.grouptravel.entities.Notes;
import hust.shixun.grouptravel.entities.NotesComments;
import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.entities.Product;
import hust.shixun.grouptravel.entities.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserService {
    //传入市的id得到省的id
    int getProvinceId(int cityId);

    //通过省的名字得到市的集合
    List<Integer> getCityList(String provinceName);

    boolean addOrder(Order order);

    //通过旅游产品id查询旅游产品，并返回该旅游产品。
    Product queryProductById(int productId);

    //通过旅游产品name查询旅游产品，并返回该旅游产品。
    List<Product> queryProductByName(String productName);

    //通过旅游产品所在地cityId查询旅游产品，并返回属于同一个市的所有旅游产品的集合。
    List<Product> queryProductByCity(int cityId);

    //通过所有未被支付的订单，并返回所有未被支付的订单的集合
    List<Order> queryOrdersWithUnpay(int userId);

    //更新未被支付的订单，如更新成功则返回ture否则为false。(用户付款将状态更新为已支付)
    Boolean updateUnpayOrder(int orderId);

    //查询用户的游记
    List<Notes> queryNotesByUserId(int userId);

    //添加游记
    Boolean addNotes(Notes notes);


    //查询当前订单的价格
    double queryOrderPrice(int orderId);

    //修改与该订单相关联的（由于拼团）所有订单的价格
    Boolean updateOrderPrice(int orderId, double orderPrice);

    //查询与当前订单所关联的所有订单
    List<Order> queryOrder(int orderId);

    //对产品评分
    Boolean addRates(double rate,int orderId);

    //点赞游记
    Boolean likeNotes(int userId,int notesId);

    //评论游记
    Boolean commentNotes(int userId,int notesId,String commentContent);

    //查看点赞的游记
    List<Notes> queryLikeNotes(int userId);



    List<User> queryAllUser();


    User queryUserById(int id);


    Boolean deleteUserById(int id);


   Boolean addUser(User user);

    Boolean updateUser(User user);



//    根据城市id查找城市图片
    String getimgByCity(int cityId);

//    返回所有城市名称
    List<City> getAllCitys();

    //查询用户未出行订单
    List<Order> queryOrdersWith1(int userId);

    //用户出行后将状态更新为已出行但未评价
    Boolean updateOrder2(int orderId);

    //查询用户未评价订单
    List<Order> queryOrdersWith2(int userId);

    //用户评价后将状态更新为已完成
    Boolean updateOrder3(int orderId);

    //查询用户已完成订单
    List<Order> queryOrdersWith3(int userId);

    //用户申请退款
    Boolean updateOrder4(int orderId);

    //查询用户退款中订单
    List<Order> queryOrdersWith4(int userId);

    //同意退款申请 退款成功
    Boolean updateOrder5(int orderId);

    //查询用户退款完成订单
    List<Order> queryOrdersWith5(int userId);

    //城市id查询城市游记
    List<Notes> queryCityNotes(int cityId);

    //通过用户id查找对游记的评论
    List<NotesComments> queryNotesCommentsByUserId(int userId);
}
