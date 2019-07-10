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


    /**
     * 获取汇款凭证列表
     * @param cid
     * @param state
     * @param beginTime
     * @param finishTime
     * @return
     */
    List<PaymentBill> getPaymentBillsbyCid(@Param("cid")String cid,@Param("state")String state,
                                           @Param("beginTime") String beginTime,@Param("finishTime") String finishTime,
                                           @Param("start")Integer start, @Param("number") Integer number);

    /**
     * 统计列表总数
     * @param cid
     * @param state
     * @param beginTime
     * @param finishTime
     * @return
     */
    int countPaybills(@Param("cid")String cid,@Param("state")String state,@Param("beginTime") String beginTime,@Param("finishTime") String finishTime);


    /**
     * 查看汇款凭证详情
     * @param id
     * @return
     */
    PaymentBill getPayBillContent(@Param("id") String id);


    /**
     *更新汇款凭证状态
     * @param id
     * @param state
     * @return
     */
    boolean updatePayBillState(@Param("id") String id,@Param("state")String state);
}
