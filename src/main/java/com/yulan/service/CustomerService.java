package com.yulan.service;

import com.yulan.dao.AirbrushDesignerAssureDao;
import com.yulan.dao.CustomerDao;
import com.yulan.pojo.Customer;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private AirbrushDesignerAssureDao airbrushDesignerAssureDao;

    public Customer getSimpleCustomerByID(String cid) {
        Customer customer = customerDao.getSimpleCustomerByID(cid);
        customer.setShortName(StringUtil.GBKToUTF8(customer.getShortName()));
        customer.setCustomerAgent(StringUtil.GBKToUTF8(customer.getCustomerAgent()));
        return customer;
    }

    public Map getSingleCustomerInfo(String cid) {
        Map<String, Object> map = new HashMap<>();
        Customer customer = customerDao.getSimpleCustomerByID(cid);
        customer.setShortName(StringUtil.GBKToUTF8(customer.getShortName()));
        customer.setCustomerAgent(StringUtil.GBKToUTF8(customer.getCustomerAgent()));

        String airbrushDesignerAssureId = getAirbrushDesignerAssureId();
        map.put("AirbrushDesignerAssureId",airbrushDesignerAssureId);
        map.put("customerInfo",customer);
        map.put("code",0);
        return map;
    }

    private String getAirbrushDesignerAssureId() {
        String id = "";
        String idhead = "AB";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String s = df.format(new java.util.Date());
        s = s.substring(2);//获取时间后6位，如20190227得190227
        List<Map<String, Object>> idMaps =
                airbrushDesignerAssureDao.findAirbrushDesignerAssureId(s);
        if (idMaps.size() == 0) {
            id = idhead + s + "00001";
        } else {
            List<Integer> nums = new ArrayList<>();
            for (Map<String, Object> map : idMaps) {
                String idS = map.get("ID").toString();
                idS = idS.substring(8);//取后面流水号
                int idI = Integer.parseInt(idS);
                nums.add(idI);
            }
            int idIMax = Collections.max(nums);//取最大
            int o = 100000;
            o = o + idIMax + 1;
            String p = o + "";
            p = p.substring(1);//获取流水号
            id = idhead + s + p;

        }
        return id;

    }

}
