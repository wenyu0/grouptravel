package hust.shixun.grouptravel.orderManagerment.controller;

import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.orderManagerment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
//查询商家所有的订单，返回一个订单的一个集合
    @RequestMapping ("/order/querryAll")
    @ResponseBody
    public List<Order> querryAllOrders(){
        List<Order> orders= orderService.queryAllOrderList();
        return orders;
    }
//查询当前时间（即当前日期天数）的当天已支付的订单并且返回一个订单的集合
    @RequestMapping ("/order/todayPay")
    @ResponseBody
    public List<Order> querryTodayPayOrders(Date date) throws ParseException {
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
//        Date date1=ft.parse(date);
        List<Order> orders= orderService.queryDatePayOrder(date);
        return orders;
    }

    //查询当前时间（即当前日期天数）的当天下订单但是未支付订单并且返回一个订单的集合
    @RequestMapping ("/order/todayUnPay")
    @ResponseBody
    public List<Order> querryTodayUnpayOrders(Date date) throws ParseException {
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
//        Date date1=ft.parse(date);
//        先查询当天下单的总单
        List<Order> orders= orderService.queryDateAll(date);
//        在查询当天支付的单
        List<Order> payOrder=querryTodayPayOrders(date);
        orders.removeAll(payOrder);

        return orders;
    }

//    查询String类型date1（之后进行强转成Date类型）与String类型date2（之后进行强转成Date类型）
//    之间所有的订单(暂时先定为往后推7天的数据(查询一周的已支付单数))，并返回这7天订单数集合(对要写的日期格式进行规定'yyyy-MM-dd')
    @RequestMapping ("/order/dateToDatePayOrder")
    @ResponseBody
    public List<Integer> querrydateToDatePayOrder(@RequestParam(name="date1",defaultValue = "2020-2-1") String date1) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        List<Integer> orders=new ArrayList<>();
        Date date_1 = ft.parse(date1);
        for(int i=0;i<7;i++){
            Date date_2 = addAndSubtractDaysByCalendar(date_1,i);
            List<Order> list=querryTodayPayOrders(date_2);
            int nums=list.size();
            orders.add(nums);
        }
        return orders;
    }

    //    查询String类型date1（之后进行强转成Date类型）与String类型date2（之后进行强转成Date类型）
//    之间所有的订单(暂时先定为往后推7天的数据(查询一周的未支付单数))，并返回这7天订单数集合(对要写的日期格式进行规定'yyyy-MM-dd')
    @RequestMapping ("/order/dateToDateUnPayOrder")
    @ResponseBody
    public List<Integer> querrydateToDateUnpayOrder(@RequestParam(name="date1",defaultValue = "2020-2-1") String date1) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        List<Integer> orders=new ArrayList<>();
        Date date_1 = ft.parse(date1);
        for(int i=0;i<7;i++){
            Date date_2 = addAndSubtractDaysByCalendar(date_1,i);
            List<Order> list=querryTodayUnpayOrders(date_2);
            int nums=list.size();
            orders.add(nums);
        }
        return orders;
    }

//    将一周里的已支付订单和待支付订单传给网页
    @RequestMapping("/order/queryPayAndUnPayOrder")
    public String queryPayAndUnPayOrder(@RequestParam(name="date1",defaultValue = "2020-2-1") String date1,Model model) throws ParseException {
        List<Integer> PayList=querrydateToDatePayOrder(date1);
        List<Integer> UnpayList=querrydateToDateUnpayOrder(date1);
        //生成一个日期数组
        List<String> dateList=createDates(date1);

        int[] pays=new int[PayList.size()];
        int[] unpays=new int[UnpayList.size()];
        String[] dates=new String[dateList.size()];
        for(int i=0;i<PayList.size();i++){
            pays[i]=PayList.get(i);
            unpays[i]=UnpayList.get(i);
            dates[i]=dateList.get(i);
        }
        model.addAttribute("payList", pays);
        model.addAttribute("unpayList",unpays);
        model.addAttribute("dateList",dates);
        return "pages/dataManage/dataManagement";
    }

//    返回当前日期的下单且已支付的订单金额
        @RequestMapping("/order/datePayPrice")
        @ResponseBody
        public double datePayPrice(Date date) throws ParseException {
            List<Order> payOders=querryTodayPayOrders(date);
            Double prices=0.0;
            for(int i=0;i<payOders.size();i++){
                prices+=payOders.get(i).getOrderPrice();
            }
            return prices;
        }

    //    返回当前日期的下单且待支付的订单金额
    @RequestMapping("/order/dateUnPayPrice")
    @ResponseBody
    public double dateUnPayPrice(Date date) throws ParseException {
        List<Order> unPayOders=querryTodayUnpayOrders(date);
        Double prices=0.0;
        for(int i=0;i<unPayOders.size();i++){
            prices+=unPayOders.get(i).getOrderPrice();
        }
        return prices;
    }
//    返回给页面，当前日期已支付、未支付金额
    @RequestMapping("/order/datePayUnPayPrice")
    public String datePayUnPayPrice(String date,Model model) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date date1=ft.parse(date);
        Double pay=datePayPrice(date1);
        Double unpay=dateUnPayPrice(date1);
        model.addAttribute("pay",pay);
        model.addAttribute("unpay",unpay);
        model.addAttribute("date",date);
        return "pages/dataManage/dataManagement1";
    }
    private List<String> createDates(String date) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        List<String> list=new ArrayList<>();
        Date date1=ft.parse(date);
        for(int i=0;i<7;i++){
            Date date2=addAndSubtractDaysByCalendar(date1, i);
            String format=ft.format(date2);
            list.add(format);
        }
        return list;
    }

    //通过订单的Integer类型的id来查询到订单，定返回该订单。
    @RequestMapping ("/order/queryOrderById")
    @ResponseBody
    public Order queryOrderById(Integer id){
        return  orderService.queryOrderById(id);
    }

    public  Date addAndSubtractDaysByCalendar(Date dateTime/*待处理的日期*/,int n/*加减天数*/){

        //日期格式
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        java.util.Calendar calstart = java.util.Calendar.getInstance();
        calstart.setTime(dateTime);

        calstart.add(java.util.Calendar.DAY_OF_WEEK, n);

        System.out.println(df.format(calstart.getTime()));
        //System.out.println(dd.format(calstart.getTime()));
        return calstart.getTime();
    }





    @RequestMapping("/order/queryAllOrders")
    public String queryAllOrders(Model model){
        List<Order> orders = orderService.queryAllOrderList();
        model.addAttribute("orders",orders);
        return "pages/orderManage/orderManagement";
    }



   @RequestMapping("/order/deleteOrderById{id}")
    public String deleteOrderById(@PathVariable Integer id){
        orderService.deleteOrderById(id);
        return "redirect:/order/queryAllOrder";
   }

   @RequestMapping("/order/addOrder")
    public String addOrder(Order order){
        orderService.addOrder(order);
        return "redirect:/order/queryAllOrder";
   }


   @GetMapping("/order/updateOrder{id}")
    public String updateOrder(@PathVariable Integer id,Model model){
       Order order = orderService.queryOrderById(id);
       model.addAttribute("order",order);
       return "pages/orderManage/orderEdit";
   }


   @PostMapping("/order/updateOrder")
    public String updateOrder(Order order){
        orderService.updateOrder(order);
        return "redirect:/order/queryAllOrder";

   }



































}
