package com.yulan.service.impl;

import com.yulan.dao.PaymentBillDao;
import com.yulan.pojo.PaymentBill;
import com.yulan.service.PaymentBillService;
import com.yulan.utils.MapUtils;
import com.yulan.utils.Response;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PaymentBillServiceImpl implements PaymentBillService {
    @Autowired
    private PaymentBillDao paymentBillDao;


    /**
     * 生成流水号
     * @return
     */
    @Override
    public String getBigPaymentBillId() {
        String id="";
        String idhead="PZ";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String s=df.format(new java.util.Date());
        s=s.substring(2);//获取时间后6位，如20190227得190227
        List<Map<String,Object>> idMaps=paymentBillDao.findPaymentBillIds(s);
        if (idMaps.size()==0){
            id=idhead+s+"00001";
        }else {
            List<Integer> nums=new ArrayList<>();
            for (Map<String,Object> map:idMaps){
                String idS=map.get("ID").toString();
                idS=idS.substring(8);//取后面流水号
                int idI=Integer.parseInt(idS);
                nums.add(idI);
            }
            int idIMax= Collections.max(nums);//取最大
            int o=100000;
            o=o+idIMax+1;
            String p=o+"";
            p=p.substring(1);//获取流水号
            id=idhead+s+p;
        }

        return id;
    }

    @Override
    public Map insertPaymentBill(Map<String, Object> map) throws UnsupportedEncodingException {
        Map result=new HashMap();
        Timestamp nowTime= new Timestamp(System.currentTimeMillis());//获取当前时间
        Date now=new Date(System.currentTimeMillis());//测试接口

        for (Map.Entry<String, Object> entry : map.entrySet()) {//转码
            if (entry.getValue() instanceof String) {
                String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                entry.setValue(origin);
            }
        }
        PaymentBill paymentBill= MapUtils.mapToBean(map,PaymentBill.class);
        paymentBill.setCreateTs(nowTime);
        paymentBill.setPayDate(now);//测试

        if (paymentBillDao.insertPaymentBill(paymentBill)){
            result.put("code",0);
            result.put("msg","新建成功");
        }else {
            result.put("code",1);
            result.put("msg","新建失败");
        }
        return result;
    }

    @Override
    public Map getPaymentBillsbyCid(Map<String, Object> map) throws UnsupportedEncodingException {
        Map result=new HashMap();
        String  cid=map.get("cid").toString();
        String  state=map.get("state").toString();
        String beginTime=map.get("beginTime").toString();
        String finishTime=map.get("finishTime").toString();
        Integer limit=(Integer) map.get("limit");
        Integer page=(Integer) map.get("page");
        if (beginTime.equals("") ||finishTime.equals("")){
            beginTime=null;
            finishTime=null;
        }
        if (cid.equals("")){
            cid=null;
        }
        if (state.equals("")){
            state=null;
        }
        Integer lastNum=null;
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
            lastNum=page+limit-1;
        }

        List<PaymentBill> list=paymentBillDao.getPaymentBillsbyCid(cid,state,beginTime,finishTime,page,lastNum);
        List<PaymentBill> dataList=new ArrayList<>();
        for (PaymentBill pb :list){
            Map<String,Object> pBmap=MapUtils.beanToMaplin(pb);
            for (Map.Entry<String, Object> entry : pBmap.entrySet()) {//转码
                if (entry.getValue() instanceof String) {
                    String origin = StringUtil.getUtf8(String.valueOf(entry.getValue()));
                    entry.setValue(origin);
                }
            }
            PaymentBill paymentBill=MapUtils.mapToBean(pBmap,PaymentBill.class);
            dataList.add(paymentBill);

        }
        result= Response.getResponseMap(0,"SUCCESS",dataList);





        return result;
    }


}

