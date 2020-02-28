package hust.shixun.grouptravel.imageUpload.mapper;

import hust.shixun.grouptravel.entities.Image;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImageMapper {

    @Insert("INSERT INTO gt_image(imageUrl) VALUES(#{imageUrl})")
    boolean saveImg(Image image);

    @Insert("INSERT INTO gt_productimage(productId,imageId) VALUES(#{productId},#{imageId})")
    boolean saveProductImg(int imageId,int productId);

    @Insert("INSERT INTO gt_notesimage(notesId,imageId) VALUES(#{notesId},#{imageId})")
    boolean saveNotesImg(int imageId,int notesId);

    @Select("SELECT imageId from gt_image where imageUrl=#{imageUrl}")
    int queryImageId(String imageUrl);

    @Select("SELECT * FROM gt_productimage,gt_image where productId=#{productId} AND gt_image.imageId = gt_productimage.imageId")
    List<Image> queryProductImages(int productId);

    @Select("SELECT * FROM gt_notesimage,gt_image where notesId=#{notesId} AND gt_image.imageId = gt_notesimage.imageId")
    List<Image> queryNotesImages(int notesId);

    @Delete("DELETE gt_productimage,gt_notesimage FROM gt_productimage,gt_notesimage where gt_productimage.imageId = #{imageId},gt_notesimage.imageId =#{imageId}")
    boolean deleteImages(int imageId);

}
