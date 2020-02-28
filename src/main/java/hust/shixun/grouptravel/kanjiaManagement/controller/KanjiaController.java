package hust.shixun.grouptravel.kanjiaManagement.controller;


import hust.shixun.grouptravel.entities.Kanjia;
import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.entities.Product;
import hust.shixun.grouptravel.kanjiaManagement.kanjiaRule.PingDuoDuoReduceRule;
import hust.shixun.grouptravel.kanjiaManagement.mapper.KanjiaMapper;
import hust.shixun.grouptravel.orderManagerment.service.OrderService;
import hust.shixun.grouptravel.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class KanjiaController {
    @Autowired
    public KanjiaMapper mapper;
    @Autowired
    public UserService userService;
    @Autowired
    public OrderService orderService;

//    @param totalReduce      总共可砍价的金额
//    @param totalReduceTimes 总共可砍价的次数
//    @param percentOfFirstNReduce 前N个人可砍掉的百分比，取值范围(0, 100]  ：暂定为30‘

//    @param firstNReduce          前N个砍价的人  :暂定为总砍价次数的一半
//    返回该ptid对应的uuid
    @ResponseBody
    @PostMapping("/kanjia/creat")
    public String creatKanjiaList(int productId){
        Product params=mapper.getKanjiaParam(productId);
        BigDecimal totalReduce=BigDecimal.valueOf(params.getMaxDiscount());
        int totalReduceTimes=params.getMaxNum();

        int percentOfFirstNReduce=30;
        int firstNReduce =totalReduceTimes/2;
        PingDuoDuoReduceRule rule=new PingDuoDuoReduceRule(percentOfFirstNReduce,firstNReduce);
        List<BigDecimal> list= rule.getReduceList(totalReduce, totalReduceTimes);

        System.out.println(list);
        String list1=String.valueOf(list);
        System.out.println(list1);

        UUID uuid=UUID.randomUUID();
        boolean flag=mapper.creatKanjiaList(productId, list1,String.valueOf(uuid));

        return String.valueOf(uuid);
    }

    //第一个用户创建订单后，点击分享砍价生成uuid，ptid放入订单并生成砍价金额列表放入砍价表中
//    返回uuid唯一标识
    @ResponseBody
    @RequestMapping("/kanjia/creatGroup")
    public String creatGroup(int orderId){
        Order order=orderService.queryOrderById(orderId);
        int productId=order.getProductId();

        String uuid=creatKanjiaList(productId);

        if(uuid!=null){
            int PTid= mapper.getPTidByuuid(uuid);
            mapper.setOrderPTid(PTid,orderId);
            return uuid;
        }
        return "";
    }
//暂时不用
    @ResponseBody
    @PostMapping("/kanjia/getList")
    public List<Double> getKanjiaList(String uuid){
        int PTid= mapper.getPTidByuuid(uuid);
        String list=mapper.getKanjiaList(PTid);
        list=list.substring(1, list.length()-1);
        String[] prices=list.split(",");
        List<String> priceList=Arrays.asList(prices);
        List<Double> priceList_1=new ArrayList<>();
        for (int index=0;index<priceList.size();index++){
            priceList_1.add(Double.valueOf(priceList.get(index)));
        }

        System.out.println(priceList_1);
        return priceList_1;
    }


        //创建一个新的参与砍价者的订单,用uuid来成为邀请码,必须先判断砍价用户有没有到达上限
    @ResponseBody
    @RequestMapping("/kanjia/addNewOne")
    public Boolean creatKanjiaOrder(int userId,String uuid){
        int PTid = mapper.getPTidByuuid(uuid);
           // 找出订单中ptid相同的数目(其中包括组团创始人)
           int num=mapper.getSamePTid(PTid);
//        查出该产品所能接受的最大砍价人数
           int maxNum=mapper.getmaxNum(PTid);
           if(maxNum>(num-1)){
            //还可以邀请砍价用户
//         首先用uuid拿出该用户能砍的金额
           Double reducePrice=getKanjiaPrice(uuid);
//         查询所有相关联的订单
           List<Order> orders=userService.queryOrder(PTid);
//         修改当前拼团人数，当前折扣，当前价格
//         拿出第一个订单
            Order order=orders.get(0);
            int orderId=order.getOrderId();
            userService.updateOrderPrice(orderId, reducePrice);

//           写入订单创建时间、复制创始人的旅游时间

//        待完成

        }

        return false;
    }


/*从数据库中查找当前加入拼团的有多少人，人数-1即为参与砍价人数，新加入的砍价用户取出砍价金额即为砍价金额列表中的参与砍价人数所对应的金额*/
    @ResponseBody
    @PostMapping("/kanjia/getKanjiaPrice")
    public Double getKanjiaPrice(String uuid){
        int PTid = mapper.getPTidByuuid(uuid);
        String list=mapper.getKanjiaList(PTid);
        list=list.substring(1, list.length()-1);
        String[] prices=list.split(",");
        List<String> priceList=Arrays.asList(prices);

//      找出订单中ptid相同的数目(其中包括组团创始人)
        int num=mapper.getSamePTid(PTid);
        String reducePrice=priceList.get(num-1);
        return Double.valueOf(reducePrice);
    }

////    更新金额
//    @ResponseBody
//    @PostMapping("/kanjia/updatePrice")
//    public Boolean updateOrders(int orderId){
//        int PTid=mapper.getPTid(orderId);
//        Double reducePrice=getKanjiaPrice(PTid);
////        传入订单id和要砍的价格
//       Boolean flag= userService.updateOrderPrice(orderId,reducePrice);
//       return flag;
//    }



}
