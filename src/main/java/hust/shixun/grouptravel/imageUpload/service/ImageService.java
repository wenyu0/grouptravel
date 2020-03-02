package hust.shixun.grouptravel.imageUpload.service;

import hust.shixun.grouptravel.entities.Image;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    List<String> uploadPic(MultipartFile[] multipartFiles);

    boolean saveImg(Image image);

    boolean saveProductImg(int imageId,int productId);

    boolean saveNotesImg(int imageId,int notesId);
    boolean setCityImg(int imageId,int cityId);
    int queryImageId(String imageUrl);
    List<String> queryProductImages(int productId);


    List<String> queryNotesImages(int notesId);

    boolean deleteImages(int imageId);

    boolean deleteImagesByUrl(String imageUrl);
}
