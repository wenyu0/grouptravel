package hust.shixun.grouptravel.adminManagement.service;

import hust.shixun.grouptravel.adminManagement.entities.Admin;
import hust.shixun.grouptravel.entities.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface AdminService {

    public Admin login(String name, String pwd);

    public List<Admin> queryAllAdmin();


    public Boolean addAdmin(Admin admin);

    public Boolean updateAdmin(Admin admin) ;

    public Boolean deleteAdminById(int id);

    public Admin queryAdminById(int id);





}
