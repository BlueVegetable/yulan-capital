package com.yulan.service;

public interface PaymentBillService {

    /**
     * 生成流水号
     * @param companyId
     * @return
     */
    String getBigPaymentBillId(String companyId);
}
