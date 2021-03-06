package hust.shixun.grouptravel.imageUpload.mapper;

import hust.shixun.grouptravel.entities.Image;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImageMapper {

    @Insert("INSERT INTO gt_image(imageUrl) VALUES(#{imageUrl})")
    boolean saveImg(Image image);

    @Insert("INSERT INTO gt_productimage(productId,imageId) VALUES(#{productId},#{imageId})")
    boolean saveProductImg(int imageId,int productId);

    @Insert("INSERT INTO gt_notesimage(notesId,imageId) VALUES(#{notesId},#{imageId})")
    boolean saveNotesImg(int imageId,int notesId);

    @Update("UPDATE gt_city set imageId = #{imageId} where cityId = #{cityId}")
    boolean setCityImg(int imageId,int cityId);

    @Select("SELECT imageId from gt_image where imageUrl=#{imageUrl}")
    int queryImageId(String imageUrl);

    @Select("SELECT imageUrl FROM gt_productimage,gt_image where productId=#{productId} AND gt_image.imageId = gt_productimage.imageId")
    List<String> queryProductImages(int productId);

    @Select("SELECT imageUrl FROM gt_notesimage,gt_image where notesId=#{notesId} AND gt_image.imageId = gt_notesimage.imageId")
    List<String> queryNotesImages(int notesId);

    @Delete("DELETE gt_productimage,gt_notesimage FROM gt_productimage,gt_notesimage where gt_productimage.imageId = #{imageId},gt_notesimage.imageId =#{imageId}")
    boolean deleteImages(int imageId);

    @Delete("delete from gt_image where imageUrl=#{imageUrl}")
    boolean deleteImagesByUrl(String imageUrl);


    @Delete("delete from gt_productimage where imageId=#{imageId}")
    boolean deleteProductImage(Integer imageId);

    @Delete("delete from gt_notesimage where imageId=#{imageId}")
    boolean deleteNotesImage(Integer imageId);

    @Select("select imageId from gt_image where imageUrl=#{imageUrl}")
    int queryImageIdByImageUrl(String imageUrl);
}
