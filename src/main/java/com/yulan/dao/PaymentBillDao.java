package com.yulan.dao;

import com.yulan.pojo.PaymentBill;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PaymentBillDao {
    PaymentBill getPaymentBillByID(String id);

    /**
     * 通过日期和cid获取id集合
     * @param idDate
     *
     * @return
     */
    List<Map<String,Object>> findPaymentBillIds(@Param("idDate")String idDate);

    /**
     * 新增汇款凭证
     * @param paymentBill
     * @return
     */
    boolean insertPaymentBill(PaymentBill paymentBill);
}
