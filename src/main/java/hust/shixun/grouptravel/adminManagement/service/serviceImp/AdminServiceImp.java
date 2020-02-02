package hust.shixun.grouptravel.adminManagement.service.serviceImp;

import hust.shixun.grouptravel.adminManagement.mapper.AdminMapper;
import hust.shixun.grouptravel.adminManagement.entities.Admin;
import hust.shixun.grouptravel.adminManagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService {
    @Autowired
    public AdminMapper adminMapper;

    public Admin login(String name, String pwd){

        return  adminMapper.login(name, pwd);
    }
}
