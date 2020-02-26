package hust.shixun.grouptravel.imageUpload.service;

import hust.shixun.grouptravel.entities.Image;
import org.apache.ibatis.annotations.Insert;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    List<String> uploadPic(MultipartFile[] multipartFiles);

    boolean saveImg(Image image);

    boolean saveProductImg(Image image,int productId);

    boolean saveNotesImg(Image image,int notesId);
}
