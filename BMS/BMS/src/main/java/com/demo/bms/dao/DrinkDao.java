package com.demo.bms.dao;

import com.demo.bms.pojo.Comment;
import com.demo.bms.pojo.Drink;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DrinkDao {
    // 查询所有饮品信息
    List<Drink> AdminSelectDrinkInfo(@Param("drink_name")String drink_name);
    Drink AdminSelectInfoById(@Param("drinkId")Integer drink_id);
    Integer AdminDeleteDrink(@Param("drinkId") Integer drinkId,@Param("isDeleted")Integer isDeleted);
    Integer AdminDeletedAllDrink(@Param("ids") String[] ids);
    Integer AdminAddScore(Comment comment);
    Integer ChangeScore(@Param("commentId") Integer commentId,@Param("score")String score);
    // 图片上传
    Integer upload (Drink drink);
    // 新增
    Integer AdminAddDrink(Drink drink);
    Integer AdminUpdateDrink(Drink drink);
}
