package hust.shixun.grouptravel.userManagement.mapper;

import hust.shixun.grouptravel.entities.City;
import hust.shixun.grouptravel.entities.Notes;
import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.entities.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
            " VALUES(#{createTime},#{productId},#{userId},#{orderPrice},#{payTime},#{status},#{PTid},#{pNum},#{currentDiscount},#{travelTime},#{notesId})")
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

    //通过所有未被支付的订单，并返回所有未被支付的订单的集合
    @Select("SELECT * FROM gt_order WHERE status=0 AND userId=#{userId}")
    List<Order> queryOrdersWithUnpay(int userId);

    //更新未被支付的订单，如更新成功则返回ture否则为false。(用户付款将状态更新为已支付)
    @Select("UPDATE gt_order SET status=1 WHERE orderId=#{orderId}")
    Boolean updateUnpayOrder(int orderId);

//    //通过名字查询相应的旅游项目中的所有已完成的订单号（唯一主键），通过此查询其游记评论表中的信息并返回其一个集合。   （待改）
//    @Select("SELECT * FROM gt_notes,gt_order WHERE gt_notes.notesId=gt_order.notesId AND gt_order.orderId=#{orderId}")
//    List<Notes> queryNotesByOrderId(int orderId);

    //查询用户的游记
    @Select("SELECT * FROM gt_notes,gt_order WHERE gt_notes.notesId=gt_order.notesId AND gt_order.userId=#{userId}")
    List<Notes> queryNotesByUserId(int userId);

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

    //对产品评分
    @Update("UPDATE gt_notes set rate = #{rate} where notesId in(SELECT notesId from gt_order where orderId = #{orderId})")
    Boolean addRates(double rate,int orderId);

    //点赞游记
    @Insert("INSERT INTO gt_noteslike(userId,notesId) values(#{userId},#{notesId})")
    Boolean likeNotes(int userId,int notesId);

    //评论游记
    @Insert("INSERT INTO gt_notescomment(userId,notesId,commentContent) VALUES(#{userId},#{notesId},#{commentContent})")
    Boolean commentNotes(int userId,int notesId,String commentContent);

    //查看点赞的游记
    @Select("SELECT * from gt_notes where notesId in (SELECT notesId from gt_noteslike where userId = #{userId})")
    List<Notes> queryLikeNotes(int userId);

//    根据城市id查找图片
    @Select("select imageUrl from gt_image where imageId in (select imageId from gt_city where cityId= #{cityId})")
    String getimgByCity(int cityId);

//    返回所有城市名称
    @Select("select  cityId,cityName from gt_city ORDER BY cityName DESC")
    List<City> getAllCitys();
}
