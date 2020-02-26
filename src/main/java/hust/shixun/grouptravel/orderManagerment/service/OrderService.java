package hust.shixun.grouptravel.orderManagerment.service;

import hust.shixun.grouptravel.adminManagement.entities.Admin;
import hust.shixun.grouptravel.entities.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.Date;
import java.util.List;


public interface OrderService {
//查询所有的订单
    List<Order> queryAllOrderList();

    Order queryOrderById(Integer id);

    Boolean deleteOrderById(int id);

    Boolean addOrder(Order order);

    Boolean updateOrder(Order order);



//  查询当前时间（即当前日期天数）的所有的订单并且返回一个订单的集合
    List<Order> queryCurrentOrderListAmount(Date currentTime);

    List<Order> querrydateToDate(Date date_1, Date date_2);


}
