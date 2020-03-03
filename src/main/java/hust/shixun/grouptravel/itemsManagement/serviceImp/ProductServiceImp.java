package hust.shixun.grouptravel.itemsManagement.serviceImp;

import hust.shixun.grouptravel.entities.Notes;
import hust.shixun.grouptravel.entities.Product;
import hust.shixun.grouptravel.itemsManagement.mapper.ProductMapper;
import hust.shixun.grouptravel.itemsManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    public ProductMapper productMapper;


    @Override
    public List<Product> queryAllProducts(){
        List<Product> products = productMapper.queryAllProducts();
        return products;
    }

    @Override
    public Product queryProductById(Integer id){
        Product product = productMapper.queryProductById(id);
        return product;
    }

    @Override
    public List<Product> queryProductByName(String name)  {
        List<Product> products = productMapper.queryProductByName(name);
        return products;
    }

    @Override
    public Boolean addProduct(Product product){
        int i=0;
        try {
            i = productMapper.addProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (i>0)
            return true;
        else
            return false;

    }


    @Override
    public Boolean updateProduct(Product product){
        int i=0;
        try {
            i = productMapper.updateProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (i>0)
            return true;
        else
            return false;

    }


    @Override
    public Boolean deleteProductById(int id){
        int i=0;
        try {
            i = productMapper.deleteProductById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (i>0)
            return true;
        else
            return false;

    }

    @Override
    public List<Integer> queryHotProductId(Map<String, String> map) {
        System.out.println(map);
        map.size();
        List<Integer> list=productMapper.queryHotProductId(map);
        System.out.println(list);
        return list;
    }

    @Override
    public String queryProductThemeById(int id) {
        return productMapper.queryProductThemeById(id);
    }

    @Override
    public String queryTransportationNameById(int id) {
        return productMapper.queryTransportationNameById(id);
    }

    @Override
    public String queryCityNameById(int id) {
        return productMapper.queryCityNameById(id);
    }

    @Override
    public List<Notes> queryNoteByProductId (int id) {
        return productMapper.queryNoteByProductId (id);
    }

    @Override
    public List<Product> getMaxDiscountPro() {
        return productMapper.getMaxDiscountPro();
    }

    @Override
    public int queryRateById(int productId) {
         return productMapper.queryRateById(productId);
    }


}
