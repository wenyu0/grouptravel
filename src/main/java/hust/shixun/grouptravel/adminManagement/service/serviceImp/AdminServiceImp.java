package hust.shixun.grouptravel.adminManagement.service.serviceImp;

import hust.shixun.grouptravel.adminManagement.mapper.AdminMapper;
import hust.shixun.grouptravel.adminManagement.entities.Admin;
import hust.shixun.grouptravel.adminManagement.service.AdminService;
import hust.shixun.grouptravel.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService {
    @Autowired
    public AdminMapper adminMapper;

    public Admin login(String name, String pwd){

        return  adminMapper.login(name, pwd);
    }

    @Override
    public List<Admin> queryAllAdmin() {
        return adminMapper.queryAllAdmin();
    }

    @Override
    public Boolean addAdmin(Admin admin) {
        int i = 0;
        try {
            i = adminMapper.addAdmin(admin);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        if (i>0)
            return true;
        else
            return false;

    }

    @Override
    public Boolean updateAdmin(Admin admin) {
        int i = 0;
        try {
            i = adminMapper.updateAdmin(admin);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        if (i>0)
            return true;
        else
            return false;
    }

    @Override
    public Boolean deleteAdminById(int id) {
        int i = 0;
        try {
            i = adminMapper.deleteAdminById(id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        if (i>0)
            return true;
        else
            return false;
    }

    @Override
    public Admin queryAdminById(int id) {
        return adminMapper.queryAdminById(id);
    }
}


