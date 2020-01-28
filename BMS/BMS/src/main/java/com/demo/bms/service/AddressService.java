package com.demo.bms.service;

import com.demo.bms.config.BaseModel;
import com.demo.bms.pojo.Address;

public interface AddressService {
    BaseModel AdminSelectAll(String name, Integer curr_page, Integer page_size, BaseModel baseModel)throws Exception;
    BaseModel AdminAddAddress(Address address, BaseModel baseModel) throws Exception;
    BaseModel AdminSelectAddressById(Integer addressId, BaseModel baseModel) throws  Exception;
    BaseModel AdminUpdateAddress(Address address, BaseModel baseModel) throws Exception;
    BaseModel AdminDeleteAddress(Integer addressId,Integer isDeleted,BaseModel baseModel) throws Exception;
    BaseModel AdminDeletedAllAddress(String ids,BaseModel baseModel)throws Exception;
}
