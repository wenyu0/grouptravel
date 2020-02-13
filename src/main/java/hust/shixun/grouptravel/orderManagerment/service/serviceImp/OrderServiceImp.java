package hust.shixun.grouptravel.orderManagerment.service.serviceImp;

import hust.shixun.grouptravel.adminManagement.entities.Admin;
import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.orderManagerment.mapper.OrderMapper;
import hust.shixun.grouptravel.orderManagerment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    public OrderMapper orderMapper;


    @Override
    public List<Order> queryAllOrderList() {
        return orderMapper.queryAllOrderList();
    }

    @Override
    public List<Order> queryCurrentOrderListAmount(Date currentTime) {

        return orderMapper.queryCurrentOrderListAmount(new java.sql.Date(currentTime.getTime()));
    }

    @Override
    public List<Order> querrydateToDate(Date date_1, Date date_2) {
        return orderMapper.querrydateToDate(new java.sql.Date(date_1.getTime()),new java.sql.Date(date_2.getTime()));
    }

    @Override
    public Order queryOrderById(Integer id) {
        return orderMapper.queryOrderById(id);
    }
}
