package com.yulan.dao;

import com.yulan.pojo.ReturnCompensationBill;

import java.util.List;
import java.util.Map;

public interface ReturnCompensationBillDao {

    int addReturnCompensationBill(ReturnCompensationBill returnCompensationBill);

    String getMaxID(String previous);

    ReturnCompensationBill getReturnCompensationBill(String id);

    List<ReturnCompensationBill> getSimpleReturnCompensationBills(Map paramters);

    Long countSimpleReturnCompensationBills(Map parameters);

}