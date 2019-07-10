package com.yulan.service;

import com.yulan.dao.CustomerDao;
import com.yulan.pojo.Customer;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public Customer getSimpleCustomerByID(String cid) {
        Customer customer = customerDao.getSimpleCustomerByID(cid);
        customer.setShortName(StringUtil.GBKToUTF8(customer.getShortName()));
        customer.setCustomerAgent(StringUtil.GBKToUTF8(customer.getCustomerAgent()));
        return customer;
    }
}
