package com.yulan.dao;

import com.yulan.pojo.ReturnCompensationBill;

import java.util.List;
import java.util.Map;

public interface ReturnCompensationBillDao {

    ReturnCompensationBill getReturnCompensationBill(String id);

    List<ReturnCompensationBill> getSimpleReturnCompensationBills(Map paramters);

}