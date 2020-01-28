package com.demo.bms.service;

import com.demo.bms.config.BaseModel;
import com.demo.bms.pojo.User;

public interface UserService {
    BaseModel login(String phone,String password,BaseModel baseModel)throws Exception;
    BaseModel selectPageUser(String account, Integer curr_page,Integer page_size,BaseModel baseModel)throws Exception;
    BaseModel AdminAddUser(User user, BaseModel baseModel) throws Exception;
    BaseModel AdminSelectById(Integer userId, BaseModel baseModel) throws  Exception;
    BaseModel AdminUpdateUser(User user, BaseModel baseModel) throws Exception;
    BaseModel AdminDeleteUser(Integer userId,Integer isDeleted,BaseModel baseModel) throws Exception;
    BaseModel AdminDeletedAllUser(String ids,BaseModel baseModel)throws Exception;
    BaseModel checkAP(String account, String phone, BaseModel baseModel) throws  Exception;
}
