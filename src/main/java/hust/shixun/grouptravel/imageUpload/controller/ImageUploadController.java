package hust.shixun.grouptravel.imageUpload.controller;

import hust.shixun.grouptravel.entities.Image;
import hust.shixun.grouptravel.imageUpload.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ImageUploadController {
    @Autowired
    public ImageService imageService;

    @RequestMapping("/imageTest")
    public String toUpload() {
        return "testImage";
    }

//    后台
    @RequestMapping(value = "/addProductImage", method = RequestMethod.POST)
    public String addProductImage(@RequestParam(value = "productImage") MultipartFile[] multipartFiles,
                                 @RequestParam(value = "productId") int productId) {
        List<String> imgList = imageService.uploadPic(multipartFiles);
        for(String imgUrl:imgList){
            Image image = new Image(imgUrl);
            imageService.saveImg(image);
            int newImgID = imageService.queryImageId(imgUrl);
            imageService.saveProductImg(newImgID,productId);
        }
        return  "redirect:/product/queryAllproducts";
    }

//  后台
    @RequestMapping(value = "/addNotesImage", method = RequestMethod.POST)
    public String addNotesImage(@RequestParam(value = "notesImage") MultipartFile[] multipartFiles,
                                 @RequestParam(value = "notesId") int notesId) {
        List<String> imgList = imageService.uploadPic(multipartFiles);
        for(String imgUrl:imgList){
            Image image = new Image(imgUrl);
            imageService.saveImg(image);
            int newImgID = imageService.queryImageId(imgUrl);
            imageService.saveNotesImg(newImgID,notesId);
        }
        return "redirect:/notes/queryAllNotes";
    }

    @RequestMapping(value = "/setCityImage", method = RequestMethod.POST)
    public void setCityImage(@RequestParam(value = "cityImage") MultipartFile[] multipartFiles,
                              @RequestParam(value = "cityId") int notesId) {
        List<String> imgList = imageService.uploadPic(multipartFiles);
        for(String imgUrl:imgList){
            Image image = new Image(imgUrl);
            imageService.saveImg(image);
            int newImgID = imageService.queryImageId(imgUrl);
            imageService.setCityImg(newImgID,notesId);
        }
    }

//  后台
    @RequestMapping(value = "/queryProductImages{productId}", method = RequestMethod.POST)
    public String queryProductImages(@PathVariable Integer productId, Model model) {
        List<String> strings = imageService.queryProductImages(productId);
        model.addAttribute("urls",strings);
        return "/pages/productManage/productImage";
    }

//    前端
    @RequestMapping(value = "/queryProductImages")
    @ResponseBody
    public List<String> queryProductImages1(@RequestParam("productId") Integer productId) {
        List<String> strings = imageService.queryProductImages(productId);
        return strings;
    }

//  后台
    @RequestMapping(value = "/queryNotesImages{notesId}", method = RequestMethod.POST)
    public String queryNotesImages(@PathVariable Integer notesId,Model model) {
        List<String> strings = imageService.queryNotesImages(notesId);
        model.addAttribute("urls",strings);
        return "/pages/youjiManage/youjiImage";
    }

//    前端
    @RequestMapping(value = "/queryNotesImages")
    @ResponseBody
    public List<String> queryNotesImages1(@RequestParam("notesId") Integer notesId) {
        List<String> strings = imageService.queryNotesImages(notesId);
        return strings;
    }

    @RequestMapping(value = "/deleteImages", method = RequestMethod.POST)
    public boolean deleteImages(@RequestParam(value = "imageId") int imageId) {
        return imageService.deleteImages(imageId);
    }

//    后台/deleteProductImagesByUrl
    @RequestMapping(value = "/deleteProductImagesByUrl")
    public String deleteProductImagesByUrl(@RequestParam(value = "imageUrl") String imageUrl){
        imageService.deleteImagesByUrl(imageUrl);
        return "redirect:/product/queryAllproducts";
    }

//    后台/deleteNotesImagesByUrl
    @RequestMapping(value = "/deleteNotesImagesByUrl")
    public String deleteNotesImagesByUrl(@RequestParam(value = "imageUrl") String imageUrl){
        imageService.deleteImagesByUrl(imageUrl);
        return "redirect:/notes/queryAllNotes";
    }
}
