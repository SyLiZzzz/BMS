package com.demo.bms.service.impl;

import com.demo.bms.config.BaseModel;
import com.demo.bms.dao.DrinkDao;
import com.demo.bms.pojo.Comment;
import com.demo.bms.pojo.Drink;
import com.demo.bms.service.DrinkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService {
    @Autowired
    DrinkDao dao;
    @Autowired
    DrinkService service;
    @Autowired
    CacheManager cahce;

    @CachePut(cacheNames = "DrinkInf",key = "1")
    @Override
    public BaseModel AdminSelectDrinkInfo(String drink_name, Integer curr_page, Integer page_size, BaseModel baseModel) throws Exception {
        PageHelper.startPage(curr_page,page_size);
        List<Drink> list=dao.AdminSelectDrinkInfo(drink_name);
        PageInfo pageInfo=new PageInfo(list,5);
        baseModel.setCode(0);
        baseModel.setMessage("查询成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    @Cacheable(cacheNames = "DrinkById" ,key = "#drinkId")
    @Override
    public BaseModel AdminSelectById(Integer drinkId, BaseModel baseModel) throws Exception {
        Drink info =dao.AdminSelectInfoById(drinkId);
        if (info ==null) {
            baseModel.setCode(-1);
            baseModel.setMessage("饮品信息查询失败");
        }else{
            baseModel.setCode(0);
            baseModel.setMessage("饮品信息查询成功");
            baseModel.setData(info);
        }
        return baseModel;
    }

    @Override
    public BaseModel upload(Drink drink, BaseModel baseModel) throws Exception {
        Integer result =dao.upload(drink);
        if (result > 0) {
            baseModel.setCode(-1);
            baseModel.setMessage("新增图片失败");
        } else {
            baseModel.setCode(0);
            baseModel.setMessage("新增图片成功");
        }
        return  baseModel;
    }

    @Override
    public BaseModel AdminAddDrink(Drink drink, BaseModel baseModel) throws Exception {
        int result = dao.AdminAddDrink(drink);
        if (result > 0) {
            Comment com =new Comment();
            com.setCreateTime(new Date());
            com.setIsDeleted(0);
            com.setVersion(0);
            com.setScore(5);
            service.AdminAddScore(com,baseModel);
            baseModel.setCode(0);
            baseModel.setMessage("新增饮品成功");
        } else {
            baseModel.setCode(-1);
            baseModel.setMessage("新增饮品失败");
        }
        return baseModel;
    }

    @Override
    public BaseModel AdminDeleteDrink(Integer drinkId, Integer isDeleted, BaseModel baseModel) throws Exception {
        int result = dao.AdminDeleteDrink(drinkId, isDeleted);
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
    public BaseModel AdminDeletedAllDrink(String ids, BaseModel baseModel) throws Exception {
        String[] idArry=ids.split(",");
        int result=dao.AdminDeletedAllDrink(idArry);
        if(result<=0){
            baseModel.setCode(-1);
            baseModel.setMessage("删除"+result+"个饮品失败。");
        }else{
            baseModel.setCode(0);
            baseModel.setMessage("删除"+result+"个饮品成功！");
        }
        return baseModel;
    }

    @Override
    public BaseModel AdminAddScore(Comment comment, BaseModel baseModel) throws Exception {
        int result = dao.AdminAddScore(comment);
        if (result > 0) {
            baseModel.setCode(0);
        } else {
            baseModel.setCode(-1);
        }
        return baseModel;
    }

    @Override
    public BaseModel ChangeScore(Integer commentId, String score, BaseModel baseModel) throws Exception {
        int result = dao.ChangeScore(commentId, score);
        if (result > 0) {
            baseModel.setCode(0);
            baseModel.setMessage("修改评分成功");
        }else{
            baseModel.setCode(-1);
            baseModel.setMessage("修改评分失败");
        }
        return baseModel;
    }

    @CacheEvict(cacheNames = "DrinkById",key = "#drink.drinkId")
    @Override
    public BaseModel AdminUpdateDrink(Drink drink, BaseModel baseModel) throws Exception {
        int result = dao.AdminUpdateDrink(drink);
        if (result > 0) {
            baseModel.setCode(0);
            baseModel.setMessage("修改信息成功");
        }else{
            baseModel.setCode(-1);
            baseModel.setMessage("修改信息失败");
        }
        return baseModel;
    }
}
