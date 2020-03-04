package hust.shixun.grouptravel.itemsManagement.mapper;

import hust.shixun.grouptravel.entities.City;
import hust.shixun.grouptravel.entities.Notes;
import hust.shixun.grouptravel.entities.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ProductMapper {


    //查询所有的旅游产品。
     List<Product> queryAllProducts();

    //通过旅游产品id查询旅游产品，并返回该旅游产品。
     Product queryProductById(int id);

    //通过旅游产品的name查询旅游产品，并返回包含该name的所有旅游产品。
     List<Product> queryProductByName(String name);

    //添加一个旅游项目，通过传入一个Product类型的旅游项目
     int addProduct(Product product) ;

    //更新一个旅游产品id为旅游项目(包括折扣等信息)
     int updateProduct(Product product) ;

    //删除一个旅游产品id为旅游项目
     int deleteProductById(int id);

     //查询出最热门的三个旅游项目
     List<Integer> queryHotProductId(Map<String, String> map);

     //通过最热门的产品id来查询出相应的游记
     List<Notes> queryNoteByProductId(int id);


     String queryProductThemeById(int id);

    String  queryTransportationNameById(int id);

    String  queryCityNameById(int id);

    List<City> queryAllCitys();




//  找出折扣优惠最大的三个旅游产品
    List<Product> getMaxDiscountPro();

//查找该产品的评分
    Integer queryRateById(int productId);

}



