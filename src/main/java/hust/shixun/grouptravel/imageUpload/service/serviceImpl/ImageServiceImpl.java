package hust.shixun.grouptravel.imageUpload.service.serviceImpl;

import hust.shixun.grouptravel.config.AppConstant;
import hust.shixun.grouptravel.entities.Image;
import hust.shixun.grouptravel.imageUpload.mapper.ImageMapper;
import hust.shixun.grouptravel.imageUpload.service.ImageService;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    public ImageMapper imageMapper;


    @Override
    public List<String> uploadPic(MultipartFile[] multipartFiles) {
        if (multipartFiles == null)
            return null;
        List<String> urlList = new ArrayList<String>();
        // 文件实际存放路径
        String filePath = AppConstant.FILE_PATH;
        for (MultipartFile multipartFile : multipartFiles) {
            try {
                if (!multipartFile.isEmpty()) {
                    System.out.println(filePath);
                    String filename = UUID.randomUUID().toString().replaceAll("-", "") + multipartFile.getOriginalFilename();
                    if(!new File(filePath).exists()){
                        new File(filePath).mkdirs();
                    }
                    String fileUrl = AppConstant.FILE_URL + filename;
                    multipartFile.transferTo(new File(filePath+filename));
                    urlList.add(fileUrl);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return urlList;
    }

    @Override
    public boolean saveImg(Image image) {
        return imageMapper.saveImg(image);
    }

    @Override
    public boolean saveProductImg(int imageId, int productId) {
        return imageMapper.saveProductImg(imageId, productId);
    }

    @Override
    public boolean saveNotesImg(int imageId, int notesId) {
        return imageMapper.saveNotesImg(imageId, notesId);
    }

    @Override
    public int queryImageId(String imageUrl) {
        return imageMapper.queryImageId(imageUrl);
    }

    @Override
    public List<Image> queryProductImages(int productId) {
        return imageMapper.queryProductImages(productId);
    }

    @Override
    public List<Image> queryNotesImages(int notesId) {
        return imageMapper.queryNotesImages(notesId);
    }

    @Override
    public boolean deleteImages(int imageId) {
        return imageMapper.deleteImages(imageId);
    }
}
