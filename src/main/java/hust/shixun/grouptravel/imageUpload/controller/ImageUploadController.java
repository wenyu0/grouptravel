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

    @RequestMapping(value = "/addProductImage", method = RequestMethod.POST)
    public void addProductImage(@RequestParam(value = "productImage") MultipartFile[] multipartFiles,
                                 @RequestParam(value = "productId") int productId) {
        List<String> imgList = imageService.uploadPic(multipartFiles);
        for(String imgUrl:imgList){
            Image image = new Image(imgUrl);
            imageService.saveImg(image);
            imageService.saveProductImg(image,productId);
        }
    }
    @RequestMapping(value = "/addNotesImage", method = RequestMethod.POST)
    public void addNotesImage(@RequestParam(value = "notesImage") MultipartFile[] multipartFiles,
                                 @RequestParam(value = "notesId") int notesId) {
        List<String> imgList = imageService.uploadPic(multipartFiles);
        for(String imgUrl:imgList){
            Image image = new Image(imgUrl);
            imageService.saveImg(image);
            imageService.saveNotesImg(image,notesId);
        }
    }
}
