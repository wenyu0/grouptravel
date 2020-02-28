package hust.shixun.grouptravel.orderManagerment.mapper;

import hust.shixun.grouptravel.adminManagement.entities.Admin;
import hust.shixun.grouptravel.entities.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderMapper {
//    查询所有的订单
    @Select("select * from gt_order")
    List<Order> queryAllOrderList();

    //通过订单的Integer类型的id来查询到订单，定返回该订单。
    @Select("SELECT * FROM gt_order WHERE orderId=#{id}")
    Order queryOrderById(Integer id);

    @Delete("delete from gt_order where orederId=#{id}")
    int deleteOrderById(int id);

    @Insert("insert intio gt_order (orderId, createTime, productId, userId, orderPrice, payTime, status, PTid, pNum, currentDiscount, travelTime, notesId) values (#{orderId}, #{createTime}, #{productId}, #{userId}, #{orderPrice}, #{payTime}, #{status}, #{PTid}, #{pNum}, #{currentDiscount}, #{travelTime}, #{notesId})")
    int addOrder(Order order);

    @Update("update gt_order set orderPrice=#{orderPrice},payTime=#{payTime},status=#{status},pNum=#{pNum},currentDiscount=#{currentDiscount},travelTime=#{travelTime} where orderId=#{orderId}")
    int updateOrder(Order order);

    //查询当前时间（即当前日期天数）的当天已付款的订单并且返回一个订单的集合
    @Select("SELECT * FROM gt_order WHERE date_format(payTime,'%y-%m-%d')=date_format(#{currentTime},'%y-%m-%d')")
    List<Order> queryDatePayOrder(Date currentTime);

    //查询当前时间（即当前日期天数）的当天下单的总单
    @Select("SELECT * FROM gt_order WHERE date_format(createTime,'%y-%m-%d')=date_format(#{currentTime},'%y-%m-%d')")
    List<Order> queryDateAll(Date currentTime);

//查询date1与date2之间所有的订单，并返回一个订单的集合(对要写的日期格式进行规定)
    @Select("SELECT * FROM gt_order WHERE payTime>#{date_1} AND payTime<#{date_2}")
    List<Order> querrydateToDate(Date date_1, Date date_2);


}
