package hust.shixun.grouptravel.adminManagement.mapper;

import hust.shixun.grouptravel.adminManagement.entities.Admin;

import hust.shixun.grouptravel.entities.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
@Repository
public interface AdminMapper {
    @Select("select * from gt_admin where adminName=#{name} and adminPassword=#{pwd}")
    Admin login(String name,String pwd);


    @Select("select * from gt_admin ")
    List<Admin> queryAllAdmin();

    @Select("select * from gt_admin where adminId=#{adminId}")
    Admin queryAdminById(int id);



    @Insert("insert into gt_admin (adminId, adminName, adminPassword) values (#{adminId},#{adminName},#{adminPassword})")
    int addAdmin(Admin admin);

    @Update("update gt_admin set adminName=#{adminName}, adminPassword=#{adminPassword} where adminId=#{adminId}")
    int updateAdmin(Admin admin) ;

    @Delete("delete from gt_admin where adminId=#{adminId}")
    int deleteAdminById(int id);




}
