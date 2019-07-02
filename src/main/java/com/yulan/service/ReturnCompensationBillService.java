package com.yulan.service;

import com.yulan.dao.ReturnCompensationBillDao;
import com.yulan.dao.RtcbItemDao;
import com.yulan.pojo.ReturnCompensationBill;
import com.yulan.pojo.RtcbItem;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReturnCompensationBillService {

    @Autowired
    private ReturnCompensationBillDao returnCompensationBillDao;
    @Autowired
    private RtcbItemDao rtcbItemDao;

    public List<ReturnCompensationBill> getSimpleReturnCompensationBills(String CID, Integer page, Integer number,
                                                                         Timestamp startTime,Timestamp endTime,String state,
                                                                         String createName,String cName) {
        Integer start = (page-1)*number+1;
        Integer end = start + number;
        Map parameters = new HashMap();
        parameters.put("start",start);
        parameters.put("end",end);
        parameters.put("CID",CID);
        parameters.put("startTime",startTime);
        parameters.put("endTime",endTime);
        parameters.put("state",state);
        parameters.put("createName",StringUtil.UTF8ToGBK(createName));
        parameters.put("cName",StringUtil.UTF8ToGBK(cName));
        List<ReturnCompensationBill> result = returnCompensationBillDao.getSimpleReturnCompensationBills(parameters);
        for (ReturnCompensationBill inline:result) {
            inline.setErpCreatorname(StringUtil.GBKToUTF8(inline.getErpCreatorname()));
            inline.setCname(StringUtil.GBKToUTF8(inline.getCname()));
        }
        return result;
    }

    public Long countSimpleReturnCompensationBills(String CID, Timestamp startTime,Timestamp endTime,String state,
                                                   String createName,String cName) {
        Map parameters = new HashMap();
        parameters.put("CID",CID);
        parameters.put("startTime",startTime);
        parameters.put("endTime",endTime);
        parameters.put("state",state);
        parameters.put("createName",StringUtil.UTF8ToGBK(createName));
        parameters.put("cName",StringUtil.UTF8ToGBK(cName));
        return returnCompensationBillDao.countSimpleReturnCompensationBills(parameters);
    }

    public ReturnCompensationBill getReturnCompensationBill(String id) {
        ReturnCompensationBill returnCompensationBill = returnCompensationBillDao.getReturnCompensationBill(id);
        returnCompensationBill.setErpCreatorname(StringUtil.GBKToUTF8(returnCompensationBill.getErpCreatorname()));
        returnCompensationBill.setCname(StringUtil.GBKToUTF8(returnCompensationBill.getCname()));
        List<RtcbItem> rtcbItems = rtcbItemDao.getRtcbItemsByRtcbID(id);
        for (RtcbItem rtcbItem:rtcbItems) {
            rtcbItem.setProductionVersion(StringUtil.GBKToUTF8(rtcbItem.getProductionVersion()));
            rtcbItem.setUnit(StringUtil.GBKToUTF8(rtcbItem.getUnit()));
            rtcbItem.setNotes(StringUtil.GBKToUTF8(rtcbItem.getNotes()));
            rtcbItem.setProcess(StringUtil.GBKToUTF8(rtcbItem.getProcess()));
        }
        returnCompensationBill.setRtcbItems(rtcbItems);
        return returnCompensationBill;
    }

}
