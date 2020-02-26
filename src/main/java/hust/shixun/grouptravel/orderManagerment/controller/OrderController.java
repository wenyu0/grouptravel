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
//查询当前时间（即当前日期天数）的所有的订单并且返回一个订单的集合
    @RequestMapping ("/order/today")
    @ResponseBody
    public List<Order> querryTodayOrders(){
        List<Order> orders= orderService.queryCurrentOrderListAmount(new Date());
        return orders;
    }
//    查询String类型date1（之后进行强转成Date类型）与String类型date2（之后进行强转成Date类型）
//    之间所有的订单，并返回一个订单的集合(对要写的日期格式进行规定'yyyy-MM-dd')
    @RequestMapping ("/order/dateToDate")
    @ResponseBody
    public List<Order> querrydateToDate(String date1, String date2) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date date_1 = ft.parse(date1);
        System.out.println(date_1);
        Date date_2 = ft.parse(date2);
        System.out.println(date_2);
        return orderService.querrydateToDate(date_1,date_2);
    }

    //通过订单的Integer类型的id来查询到订单，定返回该订单。
    @RequestMapping ("/order/queryOrderById")
    @ResponseBody
    public Order queryOrderById(Integer id){
        return  orderService.queryOrderById(id);
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
