package com.demo.bms.controller;

import com.demo.bms.config.BaseModel;
import com.demo.bms.pojo.Order;
import com.demo.bms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Order")
public class OrderController {
    @Autowired
    OrderService service;

    @GetMapping("/AdminSelectAll")
    public BaseModel AdminSelectAll(String user_name, int curr_page, int page_size, BaseModel baseModel) throws Exception {
        return service.AdminSelectAll(user_name, curr_page, page_size, baseModel);
    }

    @PostMapping("/AdminChangeState")
    public BaseModel AdminDeleteUser(Integer order_id, Integer state, BaseModel baseModel) throws Exception {
        Order order = new Order();
        if (state.equals(0)) {
            order.setState(1);
        }
        if (state.equals(1)) {
            order.setState(0);
        }
        return service.AdminChangeState(order_id,state,baseModel);
    }

    @PostMapping("/AdminChangDeleted")
    public BaseModel AdminChangDeleted(Integer order_id, Integer is_deleted, BaseModel baseModel) throws Exception {
        Order order = new Order();
        if (is_deleted.equals(0)) {
            order.setIsDeleted(1);
        }
        if (is_deleted.equals(1)) {
            order.setIsDeleted(0);
        }
        return service.AdminChangDeleted(order_id, is_deleted, baseModel);
    }

    @PostMapping("/AdmindeleteAll/{ids}")
    public BaseModel deleteAll(@PathVariable(value = "ids") String ids, BaseModel baseModel) throws Exception {
        return service.AdmindeleteAll(ids, baseModel);
    }

    @PostMapping("/ChangeAllState/{ids}")
    public BaseModel ChangeAllState(@PathVariable(value = "ids") String ids, BaseModel baseModel) throws Exception {
        return service.AdminChangeStateAll(ids, baseModel);
    }
}
