package hust.shixun.grouptravel.kanjiaManagement.controller;

import hust.shixun.grouptravel.kanjiaManagement.kanjiaRule.PingDuoDuoReduceRule;
import hust.shixun.grouptravel.kanjiaManagement.mapper.KanjiaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
public class KanjiaController {
    @Autowired
    public KanjiaMapper mapper;

//    @param totalReduce      总共可砍价的金额
//    @param totalReduceTimes 总共可砍价的次数
//    @param percentOfFirstNReduce 前N个人可砍掉的百分比，取值范围(0, 100]
//    @param firstNReduce          前N个砍价的人
    @ResponseBody
    @PostMapping("/kanjia/creat")
    public List<BigDecimal> creatKanjiaList(BigDecimal totalReduce, int totalReduceTimes, int percentOfFirstNReduce, int firstNReduce){
        PingDuoDuoReduceRule rule=new PingDuoDuoReduceRule(percentOfFirstNReduce,firstNReduce);
        List<BigDecimal> list= rule.getReduceList(totalReduce, totalReduceTimes);
        int productId=1;
        System.out.println(list);
        String list1=String.valueOf(list);
        System.out.println(list1);
        mapper.creatKanjiaList(productId, list1);
        return list;
    }

    @ResponseBody
    @PostMapping("/kanjia/getList")
    public List<String> getKanjiaList(int PTid){
        String list=mapper.getKanjiaList(PTid);
        list=list.substring(1, list.length()-1);
        String[] prices=list.split(",");
        List<String> priceList=Arrays.asList(prices);
        for (int index=0;index<priceList.size();index++){
            System.out.println(priceList.get(index));
        }
        return priceList;
    }

}
