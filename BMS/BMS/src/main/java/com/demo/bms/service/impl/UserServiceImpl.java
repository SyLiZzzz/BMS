package com.demo.bms.service.impl;

import com.demo.bms.config.BaseModel;
import com.demo.bms.dao.UserDao;
import com.demo.bms.pojo.User;
import com.demo.bms.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao dao;
    //注入缓存控制器
    @Autowired
    CacheManager cahce;

    @Override
    public BaseModel login(String phone, String password, BaseModel baseModel) throws Exception {
        User result = dao.login(phone, password);
        if (result == null) {
            baseModel.setCode(-1);
            baseModel.setMessage("登录失败，账号或密码错误");
        } else {
            baseModel.setCode(0);
            baseModel.setMessage("登录成功");
            baseModel.setData(result);
        }
        return baseModel;
    }

    //因为分页+模糊 所以希望保证方法被调用，又希望结果被缓存
    @CachePut(cacheNames = "pageInfo",key = "1")
    @Override
    public BaseModel selectPageUser(String account, Integer curr_page, Integer page_size, BaseModel baseModel) throws Exception {
        PageHelper.startPage(curr_page,page_size);
        List<User> list=dao.selectPageInfo(account);
        PageInfo pageInfo=new PageInfo(list,5);
        baseModel.setCode(0);
        baseModel.setMessage("查询成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    @Override
    public BaseModel AdminAddUser(User user, BaseModel baseModel) throws Exception {
        int result = dao.AdminAddUser(user);
        if (result > 0) {
            baseModel.setCode(0);
            baseModel.setMessage("新增用户成功");
        } else {
            baseModel.setCode(-1);
            baseModel.setMessage("新增用户失败");
        }
        return baseModel;
    }

    // 名为SelectById ，key为传入的参数id    主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
    @Cacheable(cacheNames = "SelectById" ,key = "#userId")
    @Override
    public BaseModel AdminSelectById(Integer userId, BaseModel baseModel) throws Exception {
        User user =dao.AdminSelectUserById(userId);
        if (user == null) {
            baseModel.setCode(-1);
            baseModel.setMessage("查询失败");
        }else{
            baseModel.setCode(0);
            baseModel.setMessage("查询成功");
            baseModel.setData(user);
        }
        return baseModel;
    }
    
    //清空 名为SelectById ，key为user.userId的缓存
    @CacheEvict(cacheNames = "SelectById",key = "#user.userId")
    @Override
    public BaseModel AdminUpdateUser(User user, BaseModel baseModel) throws Exception {
        int result = dao.AdminUpdateUser(user);
        if (result > 0) {
            baseModel.setCode(0);
            baseModel.setMessage("修改信息成功");
        }else{
            baseModel.setCode(-1);
            baseModel.setMessage("修改信息失败");
        }
        return baseModel;
    }

    @Override
    public BaseModel AdminDeleteUser(Integer userId, Integer isDeleted,BaseModel baseModel) throws Exception {
        int result = dao.AdminDeleteUser(userId, isDeleted);
        if (result > 0) {
            baseModel.setCode(0);
            baseModel.setMessage("改变成功");
        }else{
            baseModel.setCode(-1);
            baseModel.setMessage("改变失败");
        }
        return baseModel;
    }

    @Override
    public BaseModel AdminDeletedAllUser(String ids, BaseModel baseModel) throws Exception {
        String[] idArry=ids.split(",");
        int result=dao.AdminDeletedAllUser(idArry);
        if(result<=0){
            baseModel.setCode(-1);
            baseModel.setMessage("删除"+result+"个用户失败。");
        }else{
            baseModel.setCode(0);
            baseModel.setMessage("删除"+result+"个用户成功！");
        }
        return baseModel;
    }
    
    @Override
    public BaseModel checkAP(String account, String phone, BaseModel baseModel) throws Exception {
        User user =dao.checkAP(account,phone);
        if (user !=null) {
            baseModel.setCode(-1);
            baseModel.setMessage("已有相同的信息");
        } else {
            baseModel.setCode(0);
            baseModel.setMessage("信息通过");
            baseModel.setData(user);
        }
        return baseModel;
    }
}
