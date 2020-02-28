package hust.shixun.grouptravel.itemsManagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import hust.shixun.grouptravel.entities.Notes;
import hust.shixun.grouptravel.entities.Product;
import hust.shixun.grouptravel.itemsManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.xml.crypto.Data;
import java.util.*;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping("/product/queryAllproducts")
    public String queryAllProducts(Model model){
        List<Product> products = productService.queryAllProducts();
        Map<Integer,String> themes = new HashMap<Integer, String>();
        Map<Integer,String> transportations = new HashMap<Integer, String>();
        Map<Integer,String> citys = new HashMap<Integer, String>();
        for(Product product:products){
            themes.put(product.getProductId(),queryProductThemeById(product.getThemeId()));
            transportations.put(product.getProductId(),queryTransportationNameById(product.getTransportationId()));
            citys.put(product.getProductId(),queryCityNameById(product.getCityId()));
        }
        model.addAttribute("products",products);
        model.addAttribute("themes",themes);
        model.addAttribute("transportations",transportations);
        model.addAttribute("citys",citys);
        return  "pages/productManage/productManagement";
    }

    @RequestMapping("/product/queryProductById")
<<<<<<< HEAD
    public String queryProductById(Integer id, Model model) {
        Product product = productService.queryProductById(id);
        model.addAttribute("product",product);
        String theme = queryProductThemeById(product.getThemeId());
        String  transportation = queryTransportationNameById(product.getTransportationId());
        String city = queryCityNameById(product.getCityId());

        model.addAttribute("theme",theme);
        model.addAttribute("transportation",transportation);
        model.addAttribute("city",city);

        return "/pages/productManage/ProductQuery";
=======
    public Product queryProductById(Integer id) {

        return productService.queryProductById(id);
>>>>>>> 5a4d00c365851b3481f0b7daed2d363d44a7588a
    }

    @RequestMapping("/product/queryProductByName")
    public List<Product> queryProductByName(String name) throws Exception {
        return productService.queryProductByName(name);
    }

    @RequestMapping("/product/addProduct")
    public String addProduct(Product product) {
        productService.addProduct(product);
        return "redirect:/product/queryAllproducts";
    }


    @GetMapping("/product/updateProduct{id}")
    public String updateProduct(@PathVariable Integer id, Model model) {
        Product product = productService.queryProductById(id);
        model.addAttribute("pro",product);
        return "/pages/productManage/ProductEdit";
    }

    @PostMapping("/product/updateProduct")
    public String updateProduct1(Product product){
        productService.updateProduct(product);
        return "redirect:/product/queryAllproducts";
    }



    @RequestMapping("/product/deleteProduct{id}")
    public String deleteProductById(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return "redirect:/product/queryAllproducts";

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
            products.add(productService.queryProductById(j));
        }
        return products;
    }

    @RequestMapping("/notes/queryNotesByProductId")
    public List<Notes> queryNotesByProductId(@RequestParam("id") int productId){
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




    public String queryProductThemeById(int id){
        return productService.queryProductThemeById(id);
    }

    public String queryTransportationNameById(int id) {
        return productService.queryTransportationNameById(id);
    }

    public String queryCityNameById(int id){
        return productService.queryCityNameById(id);
    }

//    返回折扣优惠最多的三个旅游产品
    @ResponseBody
    @RequestMapping("/product/maxdiscountPros")
    public List<Product> getMaxDiscountPro(){
        return productService.getMaxDiscountPro();
    }

}
