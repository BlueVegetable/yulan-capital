package com.yulan.service.impl;

import com.yulan.dao.PaymentBillDao;
import com.yulan.service.PaymentBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class PaymentBillServiceImpl implements PaymentBillService {
    @Autowired
    private PaymentBillDao paymentBillDao;


    /**
     * 生成流水号
     * @param companyId
     * @return
     */
    @Override
    public String getBigPaymentBillId(String companyId) {
        String id="";
        String idhead="PZ";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String s=df.format(new java.util.Date());
        s=s.substring(2);//获取时间后6位，如20190227得190227
        List<Map<String,Object>> idMaps=paymentBillDao.findPaymentBillIds(s,companyId);
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
}

