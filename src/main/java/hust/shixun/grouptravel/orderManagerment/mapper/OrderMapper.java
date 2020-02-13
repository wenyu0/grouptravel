package hust.shixun.grouptravel.orderManagerment.mapper;

import hust.shixun.grouptravel.adminManagement.entities.Admin;
import hust.shixun.grouptravel.entities.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderMapper {
//    查询所有的订单
    @Select("select * from gt_order")
    List<Order> queryAllOrderList();

//查询当前时间（即当前日期天数）的所有的订单并且返回一个订单的集合
    @Select("SELECT * FROM gt_order WHERE date_format(payTime,'%y-%m-%d')=date_format(#{currentTime},'%y-%m-%d')")
    List<Order> queryCurrentOrderListAmount(Date currentTime);

//查询date1与date2之间所有的订单，并返回一个订单的集合(对要写的日期格式进行规定)
    @Select("SELECT * FROM gt_order WHERE payTime>#{date_1} AND payTime<#{date_2}")
    List<Order> querrydateToDate(Date date_1, Date date_2);

//通过订单的Integer类型的id来查询到订单，定返回该订单。
    @Select("SELECT * FROM gt_order WHERE orderId=#{id}")
    Order queryOrderById(Integer id);
}
