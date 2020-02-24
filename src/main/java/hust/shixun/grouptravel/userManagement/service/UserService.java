package hust.shixun.grouptravel.userManagement.service;

import hust.shixun.grouptravel.entities.Notes;
import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.entities.Product;

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

    //通过名字查询相应的旅游项目中的所有已完成的订单号（唯一主键），通过此查询其游记评论表中的信息并返回其一个集合。
    List<Notes> queryNotesByOrderId(int orderId);

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
}
