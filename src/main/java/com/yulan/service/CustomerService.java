package com.yulan.service;

import com.yulan.dao.AirbrushDesignerAssureDao;
import com.yulan.dao.CustomerDao;
import com.yulan.pojo.AirbrushDesignerAssure;
import com.yulan.pojo.Customer;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private AirbrushDesignerAssureDao airbrushDesignerAssureDao;
    @Autowired
    private AirbrushDesignerAssureService airbrushDesignerAssureService;

    public Customer getSimpleCustomerByID(String cid) {
        Customer customer = customerDao.getSimpleCustomerByID(cid);
        customer.setShortName(StringUtil.GBKToUTF8(customer.getShortName()));
        customer.setCustomerAgent(StringUtil.GBKToUTF8(customer.getCustomerAgent()));
        return customer;
    }

    public Map getSingleCustomerInfo(String cid, String erpCreator) {
        Map<String, Object> map = new HashMap<>();
        Customer customer = customerDao.getSimpleCustomerByID(cid);
        customer.setShortName(StringUtil.GBKToUTF8(customer.getShortName()));
        customer.setCustomerAgent(StringUtil.GBKToUTF8(customer.getCustomerAgent()));

        String id = airbrushDesignerAssureService.getAirbrushDesignerAssureId();

        map.put("AirbrushDesignerAssure",airbrushDesignerAssureService.insertAirbrushDesignerAssure( id, customer,erpCreator));
        map.put("customerInfo",customer);
        map.put("code",0);
        return map;
    }


}
