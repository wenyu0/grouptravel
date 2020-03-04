package hust.shixun.grouptravel.itemsManagement.controller;

import hust.shixun.grouptravel.entities.Notes;
import hust.shixun.grouptravel.entities.Product;
import hust.shixun.grouptravel.itemsManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

//后台
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
//返回给微信前端
    @RequestMapping("/product/queryAllproducts_1")
    @ResponseBody
    public Map<String,Object> queryAllProducts_1(){
        List<Product> products = productService.queryAllProducts();
       return addParam(products);
    }

//    返回一个产品的评分
    @RequestMapping("/product/queryRateById")
    @ResponseBody
    private double queryRateById(int productId) {
       return productService.queryRateById(productId);
    }

//后台
    @RequestMapping("/product/queryProductById")
    public String queryProductById(Integer id, Model model) {
        Product product = productService.queryProductById(id);
        model.addAttribute("product", product);
        String theme = queryProductThemeById(product.getThemeId());
        String transportation = queryTransportationNameById(product.getTransportationId());
        String city = queryCityNameById(product.getCityId());

        model.addAttribute("theme", theme);
        model.addAttribute("transportation", transportation);
        model.addAttribute("city", city);

        return "/pages/productManage/ProductQuery";
    }
//前台调用
    @RequestMapping("/product/queryProductById1")
    @ResponseBody
    public Map<String,Object> queryProductById1(Integer id) {
        Map<String, Object> map=new HashMap<>();
        Product product = productService.queryProductById(id);
        String theme = queryProductThemeById(product.getThemeId());
        String transportation = queryTransportationNameById(product.getTransportationId());
        String city = queryCityNameById(product.getCityId());
        Double rate=queryRateById(id);

        map.put("product", product);
        map.put("theme", theme);
        map.put("transportation", transportation);
        map.put("city", city);
        map.put("rate", rate);
        return map;
    }

    public Product queryProductById(Integer id) {

        return productService.queryProductById(id);

    }

    @RequestMapping("/product/queryProductByName")
    @ResponseBody
    public List<Product> queryProductByName(String name) throws Exception {
        return productService.queryProductByName(name);
    }

//    后台
    @RequestMapping("/product/addProduct")
    public String addProduct(Product product) {
        productService.addProduct(product);
        return "redirect:/product/queryAllproducts";
    }

//  后台
    @GetMapping("/product/updateProduct{id}")
    public String updateProduct(@PathVariable Integer id, Model model) {
        Product product = productService.queryProductById(id);
        String cityName = productService.queryCityNameById(id);
        model.addAttribute("pro",product);
        model.addAttribute("cities",productService.queryAllCitys());
        model.addAttribute("cities",productService.queryAllCitys());
        model.addAttribute("cityName",cityName);
        return "/pages/productManage/ProductEdit";
    }

//    后台
    @PostMapping("/product/updateProduct")
    public String updateProduct1(Product product){
        productService.updateProduct(product);
        return "redirect:/product/queryAllproducts";
    }


    //后台
    @RequestMapping("/product/deleteProduct{id}")
    public String deleteProductById(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return "redirect:/product/queryAllproducts";

    }


/*
    @RequestMapping("/queryAllCituys")
    public String queryAllCituys(Model model){

        List<City> cities = productService.queryAllCitys();
        model.addAttribute("citys",cities);
    }
*/

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
    @ResponseBody
    public Map<String,Object> queryHotProduct(String date_1,String date_2){
        List<Integer> i = queryHotProductId(date_1,date_2);
        List<Product> products = new ArrayList<Product>();
        for(int j:i){
            products.add(productService.queryProductById(j));
        }
        if(products==null&&products.size()==0){
            List<Product> all=productService.queryAllProducts();
            products.add(all.get(0));
            products.add(all.get(1));
            products.add(all.get(2));
        }
        return addParam(products);
    }

    public List<Notes> queryNotesByProductId(@RequestParam("id") int productId){
        List<Notes> notes = productService.queryNoteByProductId (productId);
        return notes;
    }

    @RequestMapping("/notes/queryHotNotes")
    @ResponseBody
    public List<Notes> queryHotNotes(String date_1, String date_2){
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
    public Map<String,Object> getMaxDiscountPro(){
        List<Product> products=productService.getMaxDiscountPro();
        if(products==null&&products.size()==0){
            List<Product> all=productService.queryAllProducts();
            products.add(all.get(0));
            products.add(all.get(1));
            products.add(all.get(2));
        }
        return addParam(products);
    }

//    给返回全部产品、查询最热门产品、折扣最大产品这三个方法添加参数：rate、主题、城市、出行方式、
    public Map<String ,Object> addParam(List<Product> products){
        if(products!=null&&products.size()!=0){
            Map<String,Object> map=new HashMap<>();
            Map<Integer,String> themes = new HashMap<Integer, String>();
            Map<Integer,String> transportations = new HashMap<Integer, String>();
            Map<Integer,String> citys = new HashMap<Integer, String>();
            Map<Integer,Integer> rates = new HashMap<Integer, Integer>();
            Map<Integer,Object> productsMap = new HashMap<Integer, Object>();
            for(Product product:products){
                int productId=product.getProductId();
                themes.put(productId,queryProductThemeById(product.getThemeId()));
                transportations.put(productId,queryTransportationNameById(product.getTransportationId()));
                citys.put(productId,queryCityNameById(product.getCityId()));
                rates.put(productId,productService.queryRateById(productId));
                productsMap.put(productId, product);
            }
            map.put("products",productsMap);
            map.put("themes",themes);
            map.put("transportations",transportations);
            map.put("citys",citys);
            map.put("rates",rates);
            return  map;
        }
        return null;
    }

//    后台/productAdd
        @RequestMapping("/productAdd")
        public String xxx(Model model){
        model.addAttribute("cities",productService.queryAllCitys());

        return "pages/productManage/productAdd";

        }



//    后台/addProductImage{productId}
    @RequestMapping("/addProductImage{productId}")
    public String addProductImage(@PathVariable Integer productId,Model model){
        model.addAttribute("productId",productId);
        return "/pages/productManage/addProductImage";
    }
}
