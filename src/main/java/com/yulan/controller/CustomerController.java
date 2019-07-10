package com.yulan.controller;

import com.yulan.pojo.Customer;
import com.yulan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @ResponseBody
    @RequestMapping("getSimpleCustomerByID")
    public Customer getSimpleCustomerByID(@RequestParam("CID")String CID) {
        return customerService.getSimpleCustomerByID(CID);
    }
}
