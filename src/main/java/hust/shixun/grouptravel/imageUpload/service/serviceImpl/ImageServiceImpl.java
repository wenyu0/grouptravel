package hust.shixun.grouptravel.imageUpload.service.serviceImpl;

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
@MapperScan("com.hust.shixun.grouptravel.imageUpload.mapper")
public class ImageServiceImpl implements ImageService {

    @Autowired
    public ImageMapper imageMapper;

    @Value("${upload-path}")
    private String uploadPath;


    @Override
    public List<String> uploadPic(MultipartFile[] multipartFiles) {
        if (multipartFiles == null)
            return null;
        List<String> urlList = new ArrayList<String>();
        for (MultipartFile multipartFile : multipartFiles) {
            try {
                if (!multipartFile.getOriginalFilename().equals("")) {
                    String filePath = new String(uploadPath);
                    System.out.println(filePath);
                    String filename = UUID.randomUUID().toString().replaceAll("-", "") + multipartFile.getOriginalFilename();
                    if(!new File(filePath).exists()){
                        new File(filePath).mkdirs();
                    }
                    multipartFile.transferTo(new File(filePath+filename));
                    urlList.add("/upload" + filename);
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
    public boolean saveProductImg(Image image, int productId) {
        return imageMapper.saveProductImg(image, productId);
    }

    @Override
    public boolean saveNotesImg(Image image, int notesId) {
        return imageMapper.saveNotesImg(image, notesId);
    }
}
