package com.demo.bms.controller;

import com.demo.bms.config.BaseModel;
import com.demo.bms.pojo.User;
import com.demo.bms.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService service;

    //登录接口
    @PostMapping("/loginAdmin")
    public BaseModel login(@Param("phone") String phone, @Param("password") String password, BaseModel baseModel)throws Exception {
        return service.login(phone, password, baseModel);
    }

    //分页+模糊查询数据接口
    @GetMapping("/selectPageUser")
    public BaseModel selectPageUser(String account,int curr_page,int page_size, BaseModel baseModel) throws Exception {
        return service.selectPageUser(account, curr_page, page_size, baseModel);
    }

    //添加用户接口
    @PostMapping("/AdminAddUser")
    public BaseModel AdminAddUser(User user, BaseModel baseModel) throws Exception {
        user.setCreateTime(new Date());
        user.setVersion(0);
        user.setIsDeleted(0);
        return service.AdminAddUser(user, baseModel);
    }

    //数据回显接口
    @GetMapping(value = "/AdminSelectById")
    public BaseModel AdminSelectUserById(@Param("userId") Integer userId, BaseModel baseModel) throws Exception {
        return service.AdminSelectById(userId, baseModel);
    }

    //修改用户接口
    @PostMapping(value = "/AdminUpdateUser")
    public BaseModel AdminUpdateUser(User user, BaseModel baseModel) throws Exception {
        user.setUpdateTime(new Date());
        user.setIsDeleted(0);
        return service.AdminUpdateUser(user, baseModel);
    }

    //删除用户接口
    @PostMapping("/AdminDeleteUser")
    public BaseModel AdminDeleteUser(Integer userId, Integer isDeleted, BaseModel baseModel) throws Exception {
        User user = new User();
        if (isDeleted.equals(0)) {
            user.setIsDeleted(1);
        }
        if (isDeleted.equals(1)) {
            user.setIsDeleted(0);
        }
        return service.AdminDeleteUser(userId, isDeleted, baseModel);
    }

    //多项删除接口
    @PostMapping("/deleteUser/{ids}")
    public BaseModel deleteAllUser(@PathVariable(value = "ids") String ids,BaseModel baseModel) throws Exception {
        return service.AdminDeletedAllUser(ids, baseModel);
    }

    //检查已有信息接口
    @PostMapping("/checkByAP")
    public BaseModel  checkByAP(String account, String phone, BaseModel baseModel) throws Exception {
        return service.checkAP(account, phone, baseModel);
    }
}
