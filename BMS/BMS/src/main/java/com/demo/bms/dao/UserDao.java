package com.demo.bms.dao;

import com.demo.bms.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
//将POJO实例化进Spring容器中
@Component
public interface UserDao {
    //登录
    User login(@Param("phone") String phone,@Param("password") String password);
    //查询全部信息
    List<User> selectPageInfo(@Param("account")String account);
    //添加用户
    Integer AdminAddUser(User user);
    //数据回显
    User AdminSelectUserById(@Param("userId")Integer userId);
    //修改用户
    Integer AdminUpdateUser(User user);
    //删除用户
    Integer AdminDeleteUser(@Param("userId") Integer userId,@Param("isDeleted")Integer isDeleted);
    //多项删除
    Integer AdminDeletedAllUser(@Param("ids") String[] ids);
    // 检查账户与手机号
    User checkAP(@Param("account") String account,@Param("phone") String phone);
}
