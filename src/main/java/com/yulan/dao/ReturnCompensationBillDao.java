package com.yulan.dao;

import com.yulan.pojo.ReturnCompensationBill;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface ReturnCompensationBillDao {

    int addReturnCompensationBill(ReturnCompensationBill returnCompensationBill);

    int deleteReturnCompensationBill(String id);

    String getMaxID(String previous);

    ReturnCompensationBill getReturnCompensationBill(String id);

    List<ReturnCompensationBill> getSimpleReturnCompensationBills(Map paramters);

    List<ReturnCompensationBill> getAllReturnCompensationBills(Map parameters);

    Long countSimpleReturnCompensationBills(Map parameters);

    int updateReturnCompensationBill(ReturnCompensationBill returnCompensationBill);

    int updateReturnCompensationBillState(@Param("id") String id, @Param("state") String state, @Param("reassureTs")Timestamp reassureTs);

    int alterPrinted(@Param("id") String id,@Param("printed")String printed);

}