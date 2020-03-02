package hust.shixun.grouptravel.orderManagerment.controller;

import java.util.Timer;
import java.util.TimerTask;

public class test {
/*设置定时器*/
    public static  void close(){
        final Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("10s后执行此方法");
                timer.cancel();
            }
        },5*1000);//1s等于1000毫秒
    }

    public static void main(String[] args) {
        System.out.println("start");
        close();
    }
}
