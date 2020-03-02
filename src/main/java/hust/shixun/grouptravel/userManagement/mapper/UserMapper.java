package hust.shixun.grouptravel.userManagement.mapper;

import hust.shixun.grouptravel.entities.City;
import hust.shixun.grouptravel.entities.Notes;
import hust.shixun.grouptravel.entities.NotesComments;
import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.entities.Product;
import hust.shixun.grouptravel.entities.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
public interface UserMapper {
    //传入市的id得到省的id
    @Select("select provinceId from gt_city where cityId = #{cityId}")
    int getProvinceId(int cityId);

    //通过省的名字得到市的集合
    @Select("SELECT cityId from gt_city where provinceId in(SELECT provinceId from gt_province where provinceName = #{provinceName})")
    List<Integer> getCityList(String provinceName);

//    添加订单
    @Insert("INSERT into gt_order(createTime,productId,userId,orderPrice,payTime,status,PTid,pNum,currentDiscount,travelTime,notesId)" +
            " VALUES(#{createTime},#{productId},#{userId},#{orderPrice},#{payTime},#{status},#{PTid},#{PNum},#{currentDiscount},#{travelTime},#{notesId})")
    boolean addOrder(Order order);

    //通过旅游产品id查询旅游产品，并返回该旅游产品。
    @Select("SELECT * FROM gt_product where productId=#{productId}")
    Product queryProductById(int productId);

    //通过旅游产品name查询旅游产品，并返回该旅游产品。
    @Select("SELECT * FROM gt_product where productName like '%#{productName}%'")
    List<Product> queryProductByName(String productName);

    //通过旅游产品所在地cityId查询旅游产品，并返回属于同一个市的所有旅游产品的集合。
    @Select("SELECT * FROM gt_product WHERE cityId = #{cityId}")
    List<Product> queryProductByCity(int cityId);


//    //通过名字查询相应的旅游项目中的所有已完成的订单号（唯一主键），通过此查询其游记评论表中的信息并返回其一个集合。   （待改）
//    @Select("SELECT * FROM gt_notes,gt_order WHERE gt_notes.notesId=gt_order.notesId AND gt_order.orderId=#{orderId}")
//    List<Notes> queryNotesByOrderId(int orderId);

    //查询用户的游记
    @Select("SELECT * FROM gt_notes,gt_order WHERE gt_notes.notesId=gt_order.notesId AND gt_order.userId=#{userId}")
    List<Notes> queryNotesByUserId(int userId);

    //通过notesId查询评论
    @Select("SELECT * FROM gt_notes,gt_notescomment WHERE gt_notes.notesId=gt_notescomment.notesId AND gt_notes.notesId=#{notesId}")
    List<NotesComments> queryNotesCommentsByNotesId(int notesId);


    @Select("select * from gt_notes ")
    List<Notes> queryAllNotes();

    @Select("select * from gt_notes where notesId=#{notesId}")
    Notes queryNotesById(int id);

    @Update("update gt_notes (title,content,writeTime,rate) values(#{title},#{content},#{writeTime},#{rate})")
    Boolean updateNotes(Notes notes);


    @Delete("delect from gt_notes where notesId=#{notesId}")
    int deleteNotesById(int id);


    //添加游记
    @Insert("INSERT INTO gt_notes(title,content,writeTime,rate,productId) VALUES(#{title},#{content},#{writeTime},#{rate},#{productId})")
    Boolean addNotes(Notes notes);


    //查询当前订单的价格
    @Select("SELECT orderPrice FROM gt_order WHERE orderId = #{orderId}")
    double queryOrderPrice(int orderId);

//    修改订单价格的前一步，查出当前订单信息
    @Select("select * from gt_order where orderId= #{orderId}")
    Order selectOrderByOrderId(int orderId);
    //修改与该订单相关联的（由于拼团）所有订单的价格、当前拼团人数、当前折扣
    @Update("UPDATE gt_order SET orderPrice = #{orderPrice} , pNum=#{pNum} ,currentDiscount=#{currentDiscount} WHERE PTid in (SELECT PTid from(SELECT PTid from gt_order WHERE orderId = #{orderId})as A) ")
    Boolean updateOrderPrice(int orderId, double orderPrice,int pNum, double currentDiscount);


    //查询与当前订单所关联的所有订单
    @Select("Select * from gt_order WHERE PTid in (SELECT PTid from gt_order WHERE orderId = #{orderId})")
    List<Order> queryOrder(int orderId);


    //通过拼团id查询所有相关联的订单
    @Select("Select * from gt_order where PTid=#{PTid}")
    List<Order> queryOrderByPTid(int pTid);

    //对产品评分
    @Update("UPDATE gt_notes set rate = #{rate} where notesId in(SELECT notesId from gt_order where orderId = #{orderId})")
    Boolean addRates(double rate, int orderId);

    //点赞游记
    @Insert("INSERT INTO gt_noteslike(userId,notesId) values(#{userId},#{notesId})")
    Boolean likeNotes(int userId, int notesId);

    //评论游记
    @Insert("INSERT INTO gt_notescomment(userId,notesId,commentContent) VALUES(#{userId},#{notesId},#{commentContent})")
    Boolean commentNotes(int userId, int notesId, String commentContent);

    @Delete("delete from gtgt_notescomment where commentId=#{commentId}")
    int deleteNotesCommentById(int commentId);

    //查看点赞的游记
    @Select("SELECT * from gt_notes where notesId in (SELECT notesId from gt_noteslike where userId = #{userId})")
    List<Notes> queryLikeNotes(int userId);

    @Select("select * from gt_user")
    List<User> queryAllUser();

    @Select("select * from gt_user where userId=#{userId}")
    User queryUserById(int id);

    @Delete("delete from gt_user where userId=#{userId}")
    int deleteUserById(int id);

    @Insert("insert into gt_user (userId,openId,createTime,lastVisitTime,city,province,country,avatarUrl,gender,nickname,phoneNum) " +
            "values (#{userId},#{openId},#{createTime},#{lastVisitTime},#{city},#{province},#{country},#{avatarUrl},#{gender},#{nickname},#{phoneNum})")
    int addUser(User user);

    @Update("update gt_user set openId=#{openId},createTime=#{createTime},lastVisitTime=#{lastVisitTime},city=#{city},province=#{province},country=#{country},avatarUrl=#{avatarUrl},gender=#{gender},nickname=#{nickname},phoneNum=#{phoneNum} where userId=#{userId}")
    int updateUser(User user);

    @Select("select * from gt_notes where productId=#{productId}")
    List<Notes> queryNotesByProductId(int productId);





//    根据城市id查找图片
    @Select("select imageUrl from gt_image where imageId in (select imageId from gt_city where cityId= #{cityId})")
    String getimgByCity(int cityId);

//    返回所有城市名称
    @Select("select  cityId,cityName from gt_city ORDER BY cityName DESC")
    List<City> getAllCitys();

    //城市id查询城市游记
    @Select("Select * from gt_notes where notesId in " +
            "(Select notesId from gt_order where productId in " +
            "(Select productId from gt_product where cityId = #{cityId}))")
    List<Notes> queryCityNotes(int cityId);

    //通过用户id查找对游记的评论
    @Select("Select * from gt_notescomment where userId = #{userId}")
    List<NotesComments> queryNotesCommentsByUserId(int userId);

    /*
  订单状态 0 下单未支付
  1 下单已支付（未出行）
  2 出行完成（未点评）
  3 订单完成
  4 退款中
  5 退款成功
  6、订单取消
 */
    //通过所有未被支付的订单，并返回所有未被支付的订单的集合
    @Select("SELECT * FROM gt_order WHERE status=0 AND userId=#{userId}")
    List<Order> queryOrdersWithUnpay(int userId);

    //用户付款将状态更新为已支付
    @Update("UPDATE gt_order SET status=1 WHERE orderId=#{orderId}")
    Boolean updateUnpayOrder(int orderId);

    //查询用户未出行订单
    @Select("SELECT * FROM gt_order WHERE status=1 AND userId=#{userId}")
    List<Order> queryOrdersWith1(int userId);

    //用户出行后将状态更新为已出行但未评价
    @Update("UPDATE gt_order SET status=2 WHERE orderId=#{orderId}")
    Boolean updateOrder2(int orderId);

    //查询用户未评价订单
    @Select("SELECT * FROM gt_order WHERE status=2 AND userId=#{userId}")
    List<Order> queryOrdersWith2(int userId);

    //用户评价后将状态更新为已完成
    @Update("UPDATE gt_order SET status=3 WHERE orderId=#{orderId}")
    Boolean updateOrder3(int orderId);

    //查询用户已完成订单
    @Select("SELECT * FROM gt_order WHERE status=3 AND userId=#{userId}")
    List<Order> queryOrdersWith3(int userId);

    //用户申请退款
    @Update("UPDATE gt_order SET status=4 WHERE orderId=#{orderId}")
    Boolean updateOrder4(int orderId);

    //查询用户退款中订单
    @Select("SELECT * FROM gt_order WHERE status=4 AND userId=#{userId}")
    List<Order> queryOrdersWith4(int userId);

    //同意退款申请 退款成功
    @Update("UPDATE gt_order SET status=5 WHERE orderId=#{orderId}")
    Boolean updateOrder5(int orderId);

    //查询用户退款完成订单
    @Select("SELECT * FROM gt_order WHERE status=5 AND userId=#{userId}")
    List<Order> queryOrdersWith5(int userId);
//根据城市名称查询城市图片
    @Select("select imageUrl from gt_city ,gt_image where cityName=#{name} and gt_image.imageId=gt_city.imageId")
    String getimgByCityName(String name);

//    取消订单
    @Update("UPDATE gt_order SET status=6 WHERE orderId=#{orderId}")
    Boolean updateOrder6(int orderId);

    @Select("select cityId from gt_city where cityName=#{cityName}")
    int queryCityIdByName(String cityName);

//通过游记id查找游记图片
    @Select("select imageUrl from gt_image where imageId in (select imageId from gt_notesimage where notesId=#{notesId})")
    List<String> queryImgBynoteId(int notesId);

//    通过城市id查找城市游记
    @Select("select * from gt_notes where productId in (select productId from gt_product where cityId=#{cityId})")
    List<Notes> queryCityNotesByCityId(int cityId);
}
