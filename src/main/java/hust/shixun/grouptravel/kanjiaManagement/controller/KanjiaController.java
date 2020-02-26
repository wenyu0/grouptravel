package hust.shixun.grouptravel.kanjiaManagement.controller;

import com.sun.org.apache.xpath.internal.operations.Or;
import hust.shixun.grouptravel.entities.Kanjia;
import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.entities.Product;
import hust.shixun.grouptravel.itemsManagement.service.ProductService;
import hust.shixun.grouptravel.kanjiaManagement.kanjiaRule.PingDuoDuoReduceRule;
import hust.shixun.grouptravel.kanjiaManagement.mapper.KanjiaMapper;
import hust.shixun.grouptravel.orderManagerment.service.OrderService;
import hust.shixun.grouptravel.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

@Controller
public class KanjiaController {
    @Autowired
    public KanjiaMapper kanjiaMapper;
    @Autowired
    public UserService userService;
    @Autowired
    public OrderService orderService;
    @Autowired
    public ProductService productService;


//    @param totalReduce      总共可砍价的金额
//    @param totalReduceTimes 总共可砍价的次数
//    @param percentOfFirstNReduce 前N个人可砍掉的百分比，取值范围(0, 100]  ：暂定为30‘

//    @param firstNReduce          前N个砍价的人  :暂定为总砍价次数的一半
//    返回该ptid对应的uuid
    @ResponseBody
    @PostMapping("/kanjia/creat")
    public String creatKanjiaList(int productId){
        Product params= kanjiaMapper.getKanjiaParam(productId);
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
        boolean flag= kanjiaMapper.creatKanjiaList(productId, list1,String.valueOf(uuid));

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
            int PTid= kanjiaMapper.getPTidByuuid(uuid);
            kanjiaMapper.setOrderPTid(PTid,orderId);
            return uuid;
        }
        return "";
    }
//暂时不用
    @ResponseBody
    @PostMapping("/kanjia/getList")
    public List<Double> getKanjiaList(String uuid){
        int PTid= kanjiaMapper.getPTidByuuid(uuid);
        String list= kanjiaMapper.getKanjiaList(PTid);
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
//    1、被邀请用户输入邀请码，显示商品信息(特殊页面，下面有砍价按钮，或者在这里传入创始人订单包括了出行日期)
//    2、商品信息页面点击帮忙砍价，进入订单页面(根据创始人订单数据：ptid，pnum，currentdiscount，prices，出发时间)直接显示在页面
//    3、下订单，通过userid和uuid，后台生成新用户订单，并显示砍价金额

//        通过接受邀请码，以及用户id返回商品信息以及创始人的订单
    @RequestMapping("/kanjia/invited")
    @ResponseBody
    public Map<String, Object> invited(String uuid){
        Map<String ,Object> info=new HashMap<>();
        int PTid = kanjiaMapper.getPTidByuuid(uuid);
//        查询所有相关联的订单
        List<Order> orders=userService.queryOrderByPTid(PTid);
//          拿出第一个订单，不管是不是创始人，通用信息是一样的
       if(orders!=null&&orders.size()!=0){
           Order order=orders.get(0);
           info.put("order", order);
           //查询该订单对应的产品
           int productId=order.getProductId();
           Product product=productService.queryProductById(productId);

           info.put("product", product);
           return info;
       }
       return null;
    }


        //创建一个新的参与砍价者的订单,用uuid来成为邀请码,必须先判断砍价用户有没有到达上限
        //并传回pnum、currentDiscount、price等参数(从返回的order中取)
    @ResponseBody
    @RequestMapping("/kanjia/addNewOne")
    public Order creatKanjiaOrder(int userId, String uuid){
        int PTid = kanjiaMapper.getPTidByuuid(uuid);
           // 找出订单中ptid相同的数目(其中包括组团创始人)
           int num= kanjiaMapper.getSamePTid(PTid);
//        查出该产品所能接受的最大砍价人数
           int maxNum= kanjiaMapper.getmaxNum(PTid);
           if(maxNum>(num-1)){
            //还可以邀请砍价用户
//         首先用uuid拿出该用户能砍的金额
           Double reducePrice=getKanjiaPrice(uuid);
//         查询所有相关联的订单
           List<Order> orders=userService.queryOrderByPTid(PTid);
           if(orders==null||orders.size()==0){
               return  null;
           }
//          拿出第一个订单
            Order order=orders.get(0);
            int orderId=order.getOrderId();


//           插入自己的订单，订单状态默认为0，写入订单创建时间、复制创始人的旅游时间、价格、参团人数、折扣,后续得考虑关联订单有取消的情况
            Order newOrder=new Order();
            newOrder.setPTid(PTid);
            newOrder.setCreateTime(new Date());
            newOrder.setPNum(order.getPNum()+1);
            newOrder.setCurrentDiscount(order.getCurrentDiscount()+reducePrice);
            newOrder.setOrderPrice(order.getOrderPrice()-reducePrice);
            newOrder.setProductId(order.getProductId());
            newOrder.setUserId(userId);
            newOrder.setTravelTime(order.getTravelTime());
            Boolean flag=userService.addOrder(newOrder);

               //  修改关联订单当前拼团人数，当前折扣，当前价格
               if(flag) {
                   Boolean flag2=userService.updateOrderPrice(orderId, reducePrice);
                   if(flag&&flag2)
                       return newOrder;
               }

        }

        return null;

    }


/*从数据库中查找当前加入拼团的有多少人，人数-1即为参与砍价人数，新加入的砍价用户取出砍价金额即为砍价金额列表中的参与砍价人数所对应的金额*/
    @ResponseBody
    @PostMapping("/kanjia/getKanjiaPrice")
    public Double getKanjiaPrice(String uuid){
        int PTid = kanjiaMapper.getPTidByuuid(uuid);
        String list= kanjiaMapper.getKanjiaList(PTid);
        list=list.substring(1, list.length()-1);
        String[] prices=list.split(",");
        List<String> priceList=Arrays.asList(prices);

//      找出订单中ptid相同的数目(其中包括组团创始人)
        int num= kanjiaMapper.getSamePTid(PTid);
        String reducePrice=priceList.get(num-1);
        return Double.valueOf(reducePrice);
    }


//    返回砍价表中的所有数据
    @RequestMapping("/kanjia/getall")
    public String queryAllKanjiaList(Model model){
        List<Kanjia> lists = kanjiaMapper.queryAllKanjia();
        model.addAttribute("Kanjias",lists);
        return  "pages/kanjiaManage/KanjiaManagement";
    }
}
