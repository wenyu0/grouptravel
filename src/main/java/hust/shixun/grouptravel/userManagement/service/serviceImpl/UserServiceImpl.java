package hust.shixun.grouptravel.userManagement.service.serviceImpl;

import hust.shixun.grouptravel.entities.Notes;
import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.entities.Product;
import hust.shixun.grouptravel.orderManagerment.mapper.OrderMapper;
import hust.shixun.grouptravel.userManagement.mapper.UserMapper;
import hust.shixun.grouptravel.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

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

    @Override
    public boolean addOrder(Date createTime, int productId, int userId, double orderPrice, Date payTime, int status, String PTid, int pNum, double currentDiscount, Date travelTime) {
        return userMapper.addOrder(new java.sql.Date(createTime.getTime()),productId,userId,orderPrice,payTime,0,null,0,0,new java.sql.Date(travelTime.getTime()));
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
        return queryOrdersWithUnpay(userId);
    }

    @Override
    public Boolean updateUnpayOrder(int orderId) {
        return userMapper.updateUnpayOrder(orderId);
    }

    @Override
    public List<Notes> queryNotesByOrderId(int orderId) {
        return userMapper.queryNotesByOrderId(orderId);
    }

    @Override
    public Boolean addNotes(String title, String content, Date writeTime) {
        return userMapper.addNotes(title,content,new java.sql.Date(writeTime.getTime()));
    }

    @Override
    public double queryOrderPrice(int orderId) {
        return userMapper.queryOrderPrice(orderId);
    }

    @Override
    public Boolean updateOrderPrice(int orderId, double orderPrice) {
        return userMapper.updateOrderPrice(orderId,orderPrice);
    }

    @Override
    public List<Order> queryOrder(int orderId) {
        return userMapper.queryOrder(orderId);
    }
}
