package com.demo.bms.dao;

import com.demo.bms.pojo.Address;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface AddressDao {
    List<Address> AdminSelectAll (@Param("name")String name);
    Integer AdminAddAddress(Address address);
    Address AdminSelectAddressById(@Param("addressId")Integer addressId);
    Integer AdminUpdateAddress(Address address);
    Integer AdminDeleteAddress(@Param("addressId") Integer addressId,@Param("isDeleted")Integer isDeleted);
    Integer AdminDeletedAllAddress(@Param("ids") String[] ids);
}
