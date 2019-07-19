package com.yulan.service;

import com.yulan.dao.ReturnCompensationBillDao;
import com.yulan.dao.RtcbItemDao;
import com.yulan.pojo.ReturnCompensationBill;
import com.yulan.pojo.RtcbItem;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReturnCompensationBillService {

    @Autowired
    private ReturnCompensationBillDao returnCompensationBillDao;
    @Autowired
    private RtcbItemDao rtcbItemDao;

    public boolean addReturnCompensationBill(ReturnCompensationBill returnCompensationBill) {
        List<RtcbItem> rtcbItems = returnCompensationBill.getRtcbItems();

        String id = generateID();
        returnCompensationBill.setId(id);
        returnCompensationBill.setErpCreatorname(StringUtil.UTF8ToGBK(returnCompensationBill.getErpCreatorname()));
        returnCompensationBill.setCname(StringUtil.UTF8ToGBK(returnCompensationBill.getCname()));
        returnCompensationBill.setCreateTs(new Timestamp(System.currentTimeMillis()));
        returnCompensationBill.setState("ONCREATE");
        returnCompensationBill.setPrinted("1");
        returnCompensationBillDao.addReturnCompensationBill(returnCompensationBill);

        if(rtcbItems == null||rtcbItems.size() == 0) {
            return false;
        }
        for (RtcbItem rtcbItem:rtcbItems) {
            rtcbItem.setRtcbId(id);
            rtcbItem.setProductionVersion(StringUtil.UTF8ToGBK(rtcbItem.getProductionVersion()));
            rtcbItem.setUnit(StringUtil.UTF8ToGBK(rtcbItem.getUnit()));
            rtcbItem.setNotes(StringUtil.UTF8ToGBK(rtcbItem.getNotes()));
            rtcbItem.setProcess(StringUtil.UTF8ToGBK(rtcbItem.getProcess()));
        }
        rtcbItemDao.addRtcbItems(rtcbItems);
        return true;
    }

    public boolean deleteReturnCompensationBill(String id) {
        rtcbItemDao.deleteRtcbItemsByRtcbID(id);
        returnCompensationBillDao.deleteReturnCompensationBill(id);
        return true;
    }

    public List<ReturnCompensationBill> getSimpleReturnCompensationBills(String CID, Integer page, Integer number,
                                                                         Timestamp startTime,Timestamp endTime,String state,
                                                                         String createName,String cName,String itemNo) {
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
        parameters.put("itemNo",itemNo);
        List<ReturnCompensationBill> result = returnCompensationBillDao.getSimpleReturnCompensationBills(parameters);
        for (ReturnCompensationBill inline:result) {
            inline.setErpCreatorname(StringUtil.GBKToUTF8(inline.getErpCreatorname()));
            inline.setCname(StringUtil.GBKToUTF8(inline.getCname()));
        }
        return result;
    }

    public Map getAllReturnCompensationBills(String CID, Integer page, Integer number,
                                                                      Timestamp startTime,Timestamp endTime,String state,
                                                                      String createName,String cName,String itemNo) {
        Map parameters = new HashMap();
        Integer start = (page-1)*number;
        Integer end = start + number;
        parameters.put("start",start);
        parameters.put("end",end);
        parameters.put("CID",CID);
        parameters.put("startTime",startTime);
        parameters.put("endTime",endTime);
        parameters.put("state",state);
        parameters.put("createName",StringUtil.UTF8ToGBK(createName));
        parameters.put("cName",StringUtil.UTF8ToGBK(cName));
        List<ReturnCompensationBill> returnCompensationBills = returnCompensationBillDao.getAllReturnCompensationBills(parameters);
        List<ReturnCompensationBill> deal = new ArrayList<>();
        List<ReturnCompensationBill> data = new ArrayList<>();
        for (ReturnCompensationBill inline:returnCompensationBills) {
            List<RtcbItem> rtcbItems = inline.getRtcbItems();
            if (rtcbItems!=null&&rtcbItems.size()!=0) {
                for (RtcbItem rtcbItem:rtcbItems) {
                    if(rtcbItem.getItemNo().equals(itemNo)) {
                        deal.add(inline);
                        break;
                    }
                }
            }
        }
        if(deal.size()>=(start+1)) {
            if(deal.size()<end+1) {
                for (int i=start;i<deal.size();i++) {
                    data.add(deal.get(i));
                }
            } else {
                for (int i=start;i<end+1;i++) {
                    data.add(deal.get(i));
                }
            }
        }
        for (ReturnCompensationBill inline:data) {
            inline.setErpCreatorname(StringUtil.GBKToUTF8(inline.getErpCreatorname()));
            inline.setCname(StringUtil.GBKToUTF8(inline.getCname()));
        }
        Map result = new HashMap();
        result.put("code",0);
        result.put("msg","");
        result.put("count",deal.size());
        result.put("data",data);
        return result;
    }

    public Long countSimpleReturnCompensationBills(String CID, Timestamp startTime,Timestamp endTime,String state,
                                                   String createName,String cName,String itemNo) {
        Map parameters = new HashMap();
        parameters.put("CID",CID);
        parameters.put("startTime",startTime);
        parameters.put("endTime",endTime);
        parameters.put("state",state);
        parameters.put("createName",StringUtil.UTF8ToGBK(createName));
        parameters.put("cName",StringUtil.UTF8ToGBK(cName));
        parameters.put("itemNo",itemNo);
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

    public boolean updateReturnCompensationBill(ReturnCompensationBill returnCompensationBill) {
        String id = returnCompensationBill.getId();
        rtcbItemDao.deleteRtcbItemsByRtcbID(id);
        List<RtcbItem> rtcbItems = returnCompensationBill.getRtcbItems();
        if(rtcbItems == null||rtcbItems.size() == 0) {
            return false;
        }
        returnCompensationBill.setId(id);
        for (RtcbItem rtcbItem:rtcbItems) {
            rtcbItem.setRtcbId(id);
            rtcbItem.setProductionVersion(StringUtil.UTF8ToGBK(rtcbItem.getProductionVersion()));
            rtcbItem.setUnit(StringUtil.UTF8ToGBK(rtcbItem.getUnit()));
            rtcbItem.setNotes(StringUtil.UTF8ToGBK(rtcbItem.getNotes()));
            rtcbItem.setProcess(StringUtil.UTF8ToGBK(rtcbItem.getProcess()));
        }
        rtcbItemDao.addRtcbItems(rtcbItems);
        return true;
    }

    public boolean updateReturnCompensationBillState(String id,String state) {
        return returnCompensationBillDao.updateReturnCompensationBillState(id,state) > 0;
    }

    public synchronized String generateID() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        String previous = simpleDateFormat.format(timestamp);
        String maxID = returnCompensationBillDao.getMaxID(previous);
        if(maxID == null) {
            return "RZ"+previous+"00001";
        }
        Integer number = Integer.parseInt(maxID.split(previous)[1]);
        number++;
        String newID = "RZ" + previous + String.format("%05d", number);
        return newID;
    }

}
