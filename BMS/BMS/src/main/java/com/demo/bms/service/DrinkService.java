package com.demo.bms.service;

import com.demo.bms.config.BaseModel;
import com.demo.bms.pojo.Comment;
import com.demo.bms.pojo.Drink;

public interface DrinkService {
    BaseModel AdminSelectDrinkInfo(String drink_name, Integer curr_page, Integer page_size, BaseModel baseModel)throws Exception;
    BaseModel AdminSelectById(Integer drinkId,BaseModel baseModel)throws Exception;
    BaseModel upload (Drink drink, BaseModel baseModel)throws Exception;
    BaseModel AdminAddDrink(Drink drink, BaseModel baseModel)throws  Exception;
    BaseModel AdminDeleteDrink(Integer drinkId,Integer isDeleted,BaseModel baseModel) throws Exception;
    BaseModel AdminDeletedAllDrink(String ids,BaseModel baseModel)throws Exception;
    BaseModel AdminAddScore(Comment comment, BaseModel baseModel)throws  Exception;
    BaseModel ChangeScore(Integer commentId,String score,BaseModel baseModel) throws Exception;
    BaseModel AdminUpdateDrink(Drink drink, BaseModel baseModel) throws Exception;
}
