package com.demo.bms.dao;

import com.demo.bms.pojo.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface OrderDao {
    List<Order> AdminSelectAll(@Param("user_name")String userName);
    Integer AdminChangeState(@Param("order_id") Integer order_id,@Param("state")Integer state);
    Integer AdminChangDeleted(@Param("order_id") Integer order_id,@Param("is_deleted")Integer is_deleted);
    Integer AdmindeleteAll(@Param("ids") String[] ids);
    Integer ChangeStateAll(@Param("ids") String[] ids);
}
