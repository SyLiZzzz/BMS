package com.demo.bms.service.impl;

import com.demo.bms.config.BaseModel;
import com.demo.bms.dao.OrderDao;
import com.demo.bms.pojo.Order;
import com.demo.bms.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao dao;
    @Autowired
    CacheManager cacheManager;

    @CachePut(cacheNames = "OrderInfo",key = "1")
    @Override
    public BaseModel AdminSelectAll(String user_name, Integer curr_page, Integer page_size, BaseModel baseModel) throws Exception {
        PageHelper.startPage(curr_page,page_size);
        List<Order> list=dao.AdminSelectAll(user_name);
        PageInfo pageInfo=new PageInfo(list,5);
        baseModel.setCode(0);
        baseModel.setMessage("查询成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    @Override
    public BaseModel AdminChangeState(Integer order_id, Integer state, BaseModel baseModel) throws Exception {
        int result = dao.AdminChangeState(order_id, state);
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
    public BaseModel AdminChangDeleted(Integer order_id, Integer is_deleted, BaseModel baseModel) throws Exception {
        int result = dao.AdminChangDeleted(order_id, is_deleted);
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
    public BaseModel AdmindeleteAll(String ids, BaseModel baseModel) throws Exception {
        String[] idArry=ids.split(",");
        int result=dao.AdmindeleteAll(idArry);
        if(result<=0){
            baseModel.setCode(-1);
            baseModel.setMessage("删除"+result+"个订单失败。");
        }else{
            baseModel.setCode(0);
            baseModel.setMessage("删除"+result+"个订单成功！");
        }
        return baseModel;
    }

    @Override
    public BaseModel AdminChangeStateAll(String ids, BaseModel baseModel) throws Exception {
        String[] idArry=ids.split(",");
        int result=dao.ChangeStateAll(idArry);
        if(result<=0){
            baseModel.setCode(-1);
            baseModel.setMessage("改变"+result+"个订单失败。");
        }else{
            baseModel.setCode(0);
            baseModel.setMessage("改变"+result+"个订单成功！");
        }
        return baseModel;
    }
}
