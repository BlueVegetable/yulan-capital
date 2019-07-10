package com.yulan.dao;

import com.yulan.pojo.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerDao {
    Customer getSimpleCustomerByID(@Param("CID") String cid);
}