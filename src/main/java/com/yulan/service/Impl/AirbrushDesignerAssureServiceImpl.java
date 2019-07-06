package com.yulan.service.Impl;

import com.yulan.dao.AirbrushDesignerAssureDao;
import com.yulan.dao.Web_userDao;
import com.yulan.pojo.AirbrushDesignerAssure;
import com.yulan.pojo.Web_user;
import com.yulan.service.AirbrushDesignerAssureService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AirbrushDesignerAssureServiceImpl implements AirbrushDesignerAssureService {
    @Autowired
    private Web_userDao web_userDao;
    @Autowired
    private AirbrushDesignerAssureDao airbrushDesignerAssureDao;

    private StringUtil stringUtil;

    @Override
    public Map getAirbrushDesignerAssure(String cid, String startDate,
                                         String endDate, String states,
                                         Integer page, Integer lastNum) {
        Map<String, Object> map = new HashMap<>();
        cid = changeLoginNameToCompanyID(cid);
        List<AirbrushDesignerAssure> airbrushDesignerAssureList =
                airbrushDesignerAssureDao.getAirbrushDesignerAssure(cid,
                        startDate, endDate, states, page, lastNum);
        for(int i = 0; i < airbrushDesignerAssureList.size() ; i++){
            AirbrushDesignerAssure airbrushDesignerAssure = airbrushDesignerAssureList.get(i);
            if(null != airbrushDesignerAssure.getCname()) {
                airbrushDesignerAssure.setCname(stringUtil.GBKToUTF8(airbrushDesignerAssure.getCname()));
            }
            if(null != airbrushDesignerAssure.getCustomerAgent()){
                airbrushDesignerAssure.setCustomerAgent(stringUtil.GBKToUTF8(airbrushDesignerAssure.getCustomerAgent()));
            }
            if(null != airbrushDesignerAssure.getSendbackReason()){
                airbrushDesignerAssure.setSendbackReason(stringUtil.GBKToUTF8(airbrushDesignerAssure.getSendbackReason()));
            }
        }

        map.put("airbrushDesignerAssureList",airbrushDesignerAssureList);
        map.put("code", 0);
        return map;
    }

    private String changeLoginNameToCompanyID(String cid) {
        return web_userDao.changeLoginNameToCompanyID(cid);
    }

}
