package com.yulan.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface PaymentBillService {

    /**
     * 生成流水号
     * @return
     */
    String getBigPaymentBillId();

    /**
     * 新建银行汇款
     * @param map
     * @return
     */
    Map insertPaymentBill(Map<String,Object> map) throws UnsupportedEncodingException;

    /**
     * 获取汇款凭证列表
     * @param map
     * @return
     */
    Map getPaymentBillsbyCid(Map<String,Object> map) throws UnsupportedEncodingException;


}
