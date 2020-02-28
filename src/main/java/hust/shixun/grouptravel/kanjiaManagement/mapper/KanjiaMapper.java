package hust.shixun.grouptravel.kanjiaManagement.mapper;

import hust.shixun.grouptravel.entities.Kanjia;
import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.entities.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Mapper
@Repository
public interface KanjiaMapper {

    @Insert("insert into gt_kanjia (discountList,productId,uuid) values (#{discountList},#{productId},#{uuid})")
    boolean creatKanjiaList(int productId, String discountList, String uuid);

    @Select("select discountList from gt_kanjia where PTid=#{PTid}")
    String getKanjiaList(int PTid);

    @Select("select count(*) from gt_order where PTid=#{PTid}")
    int getSamePTid(int PTid);

    @Select("select maxDiscount ,maxNum from gt_product where productId=#{productId}")
    Product getKanjiaParam(int productId);

    @Select("select PTid from gt_order where orderId=#{orderId}")
    int getPTid(int orderId);

    @Select("select * from gt_order where PTid=#{PTid}")
    Order getOrderByPTid(int PTid);

    @Select("select PTid from gt_kanjia where uuid= #{uuid}")
    int  getPTidByuuid(String uuid);

    @Update("update gt_order set PTid =#{PTid} where orderId= #{orderId} ")
    boolean setOrderPTid(int PTid,int orderId);

    @Select("select maxNum from gt_product where productId in (select productId from gt_order where PTid= #{PTid})")
    int getmaxNum(int PTid);

    @Select("select * from gt_kanjia")
    List<Kanjia> queryAllKanjia();
}
