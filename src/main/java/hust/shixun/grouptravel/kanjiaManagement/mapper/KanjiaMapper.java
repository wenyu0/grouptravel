package hust.shixun.grouptravel.kanjiaManagement.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface KanjiaMapper {

    @Insert("insert into gt_kanjia (discountList,productId) values (#{discountList},#{productId})")
    Boolean creatKanjiaList(int productId, String discountList) ;

    @Select("select discountList from gt_kanjia where PTid=#{PTid}")
    String getKanjiaList(int PTid);
}
