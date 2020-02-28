package hust.shixun.grouptravel.itemsManagement.service;

import hust.shixun.grouptravel.entities.Notes;
import hust.shixun.grouptravel.entities.Product;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ProductService {

    public List<Product> queryAllProducts();

    public Product queryProductById(Integer id);

    public List<Product> queryProductByName(String name);

    public Boolean addProduct(Product product);

    public Boolean updateProduct(Product product);

    public Boolean deleteProductById(int id);

    public List<Integer> queryHotProductId(Map<String, String> map);

    List<Notes> queryNoteByProductId(int id);


    public  String queryProductThemeById(int id);

    String  queryTransportationNameById(int id);

    String  queryCityNameById(int id);

    List<Product> getMaxDiscountPro();
}
