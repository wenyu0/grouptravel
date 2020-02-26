package hust.shixun.grouptravel.imageUpload.mapper;

import hust.shixun.grouptravel.entities.Image;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {

    @Insert("INSERT INTO gt_image(imageUrl) VALUES(#{imageUrl})")
    boolean saveImg(Image image);

    @Insert("INSERT INTO gt_productimage(productId,imageId) VALUES(#{productId},#{imageId})")
    boolean saveProductImg(Image image,int productId);

    @Insert("INSERT INTO gt_notesimage(notesId,imageId) VALUES(#{notesId},#{imageId})")
    boolean saveNotesImg(Image image,int notesId);

}
