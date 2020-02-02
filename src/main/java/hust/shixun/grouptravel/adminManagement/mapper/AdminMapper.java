package hust.shixun.grouptravel.adminManagement.mapper;

import hust.shixun.grouptravel.adminManagement.entities.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface AdminMapper {
    @Select("select * from gt_admin where adminName=#{name} and adminPassword=#{pwd}")
    Admin login(String name,String pwd);
}
