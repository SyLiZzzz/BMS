package com.demo.bms.controller;

import com.demo.bms.config.BaseModel;
import com.demo.bms.pojo.Address;
import com.demo.bms.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/Address")
public class AddressController {
    @Autowired
    AddressService service;

    @GetMapping("/AdminSelectAll")
    public BaseModel AdminSelectAll(String name, int curr_page, int page_size, BaseModel baseModel) throws Exception {
        return service.AdminSelectAll(name, curr_page, page_size, baseModel);
    }

    @PostMapping(value = "/AdminAddAddress")
    public BaseModel AdminAddAddress(Address address, BaseModel baseModel) throws Exception {
        address.setCreateTime(new Date());
        address.setVersion(0);
        address.setIsDeleted(0);
        return service.AdminAddAddress(address, baseModel);
    }

    @GetMapping("/AdminSelectAddressById")
    public BaseModel AdminSelectAddressById(Integer addressId, BaseModel baseModel) throws Exception {
        return service.AdminSelectAddressById(addressId, baseModel);
    }

    @PostMapping("/AdminUpdateAddress")
    public BaseModel AdminUpdateAddress(Address address, BaseModel baseModel) throws Exception {
        address.setUpdateTime(new Date());
        address.setIsDeleted(0);
        return service.AdminUpdateAddress(address, baseModel);
    }

    @PostMapping("/AdminDeleteAddress")
    public BaseModel AdminDeleteAddress(Integer addressId, Integer isDeleted, BaseModel baseModel) throws Exception {
        Address address = new Address();
        if (isDeleted.equals(0)) {
            address.setIsDeleted(1);
        }
        if (isDeleted.equals(1)) {
            address.setIsDeleted(0);
        }
        return service.AdminDeleteAddress(addressId, isDeleted, baseModel);
    }

    @PostMapping("/deleteAddress/{ids}")
    public BaseModel AdminDeletedAllAddress(@PathVariable(value = "ids") String ids, BaseModel baseModel) throws Exception {
        return service.AdminDeletedAllAddress(ids, baseModel);
    }
}
