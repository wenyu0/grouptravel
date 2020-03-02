package hust.shixun.grouptravel.config;

import hust.shixun.grouptravel.entities.City;
import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.userManagement.service.UserService;
import hust.shixun.grouptravel.util.SpringUtil;
import org.springframework.context.ApplicationContext;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimeConfig {

/*超过2天未付款，取消所有关联订单,并将已支付的订单退款*/
    public static void orderClose(int PTid){
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
           UserService userService= SpringUtil.getBean(UserService.class);
            @Override
            public void run() {
                try {
                    //     查询所有PTid一样的订单
                    List<Order> orders =userService.queryOrderByPTid(PTid);
                    for(Order order:orders){
                        userService.updateOrder6(order.getOrderId());
                    }
                    System.out.println("已修改所有关联订单");
                }catch (Exception e){
                        e.printStackTrace();
                }
            //中断线程
                timer.cancel();
            }
        },2*24*60*60*1000);
    }
}
