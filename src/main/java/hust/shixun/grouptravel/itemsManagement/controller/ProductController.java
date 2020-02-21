package hust.shixun.grouptravel.itemsManagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import hust.shixun.grouptravel.entities.Notes;
import hust.shixun.grouptravel.entities.Product;
import hust.shixun.grouptravel.itemsManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/product/queryAllproducts")
    public List<Product> queryAllProducts() throws JsonProcessingException {

        return  productService.queryAllProducts();
    }

    @RequestMapping("/product/queryProductById")
    public Product queryProductById(Integer id) {
        return productService.queryProductById(id);
    }

    @RequestMapping("/product/queryProductByName")
    public List<Product> queryProductByName(String name) throws Exception {
        return productService.queryProductByName(name);
    }

    @RequestMapping("/product/addProduct")
    public Boolean addProduct(Product product) {
        return productService.addProduct(product);
    }


    @RequestMapping("/product/updateProduct")
    public Boolean updateProduct(Product product) {
        return productService.updateProduct(product);
    }


    @RequestMapping("/product/deleteProductById")
    public Boolean deleteProductById(int id) {
        return productService.deleteProductById(id);

    }

    @RequestMapping("/product/queryHotProductId")
    public List<Integer> queryHotProductId(String date_1,String date_2) {
        HashMap<String, String> map = new HashMap<String,String>();
        map.put("date_1",date_1);
        map.put("date_2",date_2);
        System.out.println(map);
        List<Integer> integers = productService.queryHotProductId(map);
        System.out.println(integers);
        return integers;
    }


    @RequestMapping("/product/queryHotProduct")
    public List<Product> queryHotProduct(String date_1,String date_2){
        List<Integer> i = queryHotProductId(date_1,date_2);
        List<Product> products = new ArrayList<Product>();
        for(int j:i){
            products.add(queryProductById(j));
        }
        return products;
    }

    @RequestMapping("/notes/queryNotesByProductId")
    public List<Notes> queryNotesByProductId(int productId){
        List<Notes> notes = productService.queryNoteByProductId (productId);
        return notes;
    }

    @RequestMapping("/notes/queryHotNotes")
    public List<Notes> queryHotNotes(String date_1,String date_2){
        List<Integer> i = queryHotProductId(date_1, date_2);
        List<Notes> notes = new ArrayList<>();
        for(int j:i){
            notes.addAll(queryNotesByProductId(j));
        }
        return notes;
    }


}
