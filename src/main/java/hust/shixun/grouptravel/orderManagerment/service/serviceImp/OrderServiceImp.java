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
    public List<Order> queryDatePayOrder(Date currentTime) {

        return orderMapper.queryDatePayOrder(new java.sql.Date(currentTime.getTime()));
    }

    @Override
    public List<Order> queryDateAll(Date currentTime){
        return orderMapper.queryDateAll(currentTime);
    }
    @Override
    public List<Order> querrydateToDate(Date date_1, Date date_2) {
        return orderMapper.querrydateToDate(new java.sql.Date(date_1.getTime()),new java.sql.Date(date_2.getTime()));
    }

    @Override
    public Order queryOrderById(Integer id) {
        return orderMapper.queryOrderById(id);
    }

    @Override
    public Boolean deleteOrderById(int id) {
        int i = 0;
        try {
            i = orderMapper.deleteOrderById(id);
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
    public Boolean addOrder(Order order) {
        int i = 0;
        try {
            i = orderMapper.addOrder(order);
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
    public Boolean updateOrder(Order order) {
        int i = 0;
        try {
            i = orderMapper.updateOrder(order);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        if (i>0)
            return true;
        else
            return false;
    }
}
