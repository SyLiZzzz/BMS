package com.demo.bms.service.impl;

import com.demo.bms.config.BaseModel;
import com.demo.bms.dao.AddressDao;
import com.demo.bms.pojo.Address;
import com.demo.bms.service.AddressService;
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
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressDao dao;
    @Autowired
    CacheManager cacheManager;

    @CachePut(cacheNames = "AddressInfo",key = "1")
    @Override
    public BaseModel AdminSelectAll(String name, Integer curr_page, Integer page_size, BaseModel baseModel) throws Exception {
        PageHelper.startPage(curr_page,page_size);
        List<Address> list=dao.AdminSelectAll(name);
        PageInfo pageInfo=new PageInfo(list,5);
        baseModel.setCode(0);
        baseModel.setMessage("查询成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    @Override
    public BaseModel AdminAddAddress(Address address, BaseModel baseModel) throws Exception {
        int result = dao.AdminAddAddress(address);
        if (result > 0) {
            baseModel.setCode(0);
            baseModel.setMessage("新增地址成功");
        } else {
            baseModel.setCode(-1);
            baseModel.setMessage("新增地址失败");
        }
        return baseModel;
    }

    @Cacheable(cacheNames = "AddressById" ,key = "#addressId")
    @Override
    public BaseModel AdminSelectAddressById(Integer addressId, BaseModel baseModel) throws Exception {
        Address address =dao.AdminSelectAddressById(addressId);
        if (address == null) {
            baseModel.setCode(-1);
            baseModel.setMessage("查询失败");
        }else{
            baseModel.setCode(0);
            baseModel.setMessage("查询成功");
            baseModel.setData(address);
        }
        return baseModel;
    }

    @CacheEvict(cacheNames = "AddressById",key = "#address.addressId")
    @Override
    public BaseModel AdminUpdateAddress(Address address, BaseModel baseModel) throws Exception {
        int result = dao.AdminUpdateAddress(address);
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
    public BaseModel AdminDeleteAddress(Integer addressId, Integer isDeleted, BaseModel baseModel) throws Exception {
        int result = dao.AdminDeleteAddress(addressId, isDeleted);
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
    public BaseModel AdminDeletedAllAddress(String ids, BaseModel baseModel) throws Exception {
        String[] idArry=ids.split(",");
        int result=dao.AdminDeletedAllAddress(idArry);
        if(result<=0){
            baseModel.setCode(-1);
            baseModel.setMessage("删除"+result+"个地址失败。");
        }else{
            baseModel.setCode(0);
            baseModel.setMessage("删除"+result+"个地址成功！");
        }
        return baseModel;
    }
}
