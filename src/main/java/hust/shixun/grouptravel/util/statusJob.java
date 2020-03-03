package hust.shixun.grouptravel.util;

import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.userManagement.service.UserService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class statusJob implements Job {

    @Autowired
    private UserService userService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Checking status...");
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");
        Date date = new Date();// 获取当前时间
        List<Order> orderList = userService.queryAllNoTravelOrders(sdf.format(date));
        if (!orderList.isEmpty()) {
            for (Order order : orderList) {
                userService.updateOrder2(order.getOrderId());
            }
        }
    }
}
