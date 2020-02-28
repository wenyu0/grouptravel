package hust.shixun.grouptravel.imageUpload.controller;

import hust.shixun.grouptravel.entities.Image;
import hust.shixun.grouptravel.imageUpload.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        return  "success";
    }
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
        return "sucess";
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

    @RequestMapping(value = "/queryProductImages", method = RequestMethod.POST)
    public List<Image> queryProductImages(@RequestParam(value = "productId") int productId) {
        return imageService.queryProductImages(productId);
    }
    @RequestMapping(value = "/queryNotesImages", method = RequestMethod.POST)
    public List<Image> queryNotesImages(@RequestParam(value = "notesId") int notesId) {
        return imageService.queryNotesImages(notesId);
    }
    @RequestMapping(value = "/deleteImages", method = RequestMethod.POST)
    public boolean deleteImages(@RequestParam(value = "imageId") int imageId) {
        return imageService.deleteImages(imageId);
    }
}
