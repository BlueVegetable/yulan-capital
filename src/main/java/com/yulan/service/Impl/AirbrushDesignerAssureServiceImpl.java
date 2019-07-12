package com.yulan.service.Impl;

import com.yulan.dao.AbdrImageDao;
import com.yulan.dao.AirbrushDesignerAssureDao;
import com.yulan.dao.Web_userDao;
import com.yulan.pojo.AbdrImage;
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
    @Autowired
    private AbdrImageDao abdrImageDao;

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

            List<AbdrImage> abdrImageList = abdrImageDao.getAbdrImage(airbrushDesignerAssure.getId());
            for(int j = 0; j < abdrImageList.size(); j++){
                AbdrImage abdrImage = abdrImageList.get(j);
                if(null != abdrImage.getMemo()){
                    abdrImage.setMemo(stringUtil.GBKToUTF8(abdrImage.getMemo()));
                }
                if(null != abdrImage.getImagePath()){
                    abdrImage.setImagePath(stringUtil.GBKToUTF8(abdrImage.getImagePath()));
                }
                if(null != abdrImage.getSpecifications()){
                    abdrImage.setSpecifications(stringUtil.GBKToUTF8(abdrImage.getSpecifications()));
                }
            }

            airbrushDesignerAssure.setAbdrImage(abdrImageList);
        }

        map.put("airbrushDesignerAssureList",airbrushDesignerAssureList);
        map.put("code", 0);
        return map;
    }

    @Override
    public Map updateAirbrushDesignerAssure(AirbrushDesignerAssure airbrushDesignerAssure) {
        Map<String, Object> map = new HashMap<>();
        int flag = 0;
       // airbrushDesignerAssure.setCid(changeLoginNameToCompanyID(airbrushDesignerAssure.getCid()));
      /*  if(null != airbrushDesignerAssure.getCname()) {
            airbrushDesignerAssure.setCname(stringUtil.UTF8ToGBK(airbrushDesignerAssure.getCname()));
        }
        if(null != airbrushDesignerAssure.getCustomerAgent()){
            airbrushDesignerAssure.setCustomerAgent(stringUtil.UTF8ToGBK(airbrushDesignerAssure.getCustomerAgent()));
        }
        if(null != airbrushDesignerAssure.getSendbackReason()){
            airbrushDesignerAssure.setSendbackReason(stringUtil.UTF8ToGBK(airbrushDesignerAssure.getSendbackReason()));
        }*/
        switch (airbrushDesignerAssure.getState()){
            case "同意":
                airbrushDesignerAssure.setState("APPROVED");
                break;
            case"不同意":
                airbrushDesignerAssure.setState("CANCELED");
                break;
                default:
                    map.put("data","客户状态不正确");
                    flag = 1;

        }
        if(flag == 0 && airbrushDesignerAssureDao.updateAirbrushDesignerAssure(airbrushDesignerAssure)){
            map.put("msg","SUCCESS");
            map.put("code", 0);

        }else{
            map.put("msg","FAILED");
            map.put("code", 1);
        }
        return map;
    }


    @Override
    public Map deleteAirbrushDesignerAssure(String id) {
        Map<String, Object> map = new HashMap<>();
        AirbrushDesignerAssure airbrushDesignerAssure = airbrushDesignerAssureDao.getAirbrushDesignerAssureSingle(id);
        if(airbrushDesignerAssure.getImageCount() == 0){
            if (airbrushDesignerAssureDao.deleteAirbrushDesignerAssure(id) ) {
                map.put("msg", "SUCCESS");
                map.put("code", 0);
            } else {
                map.put("msg", "FAILED");
                map.put("code", 1);
            }

        }else {
            if (airbrushDesignerAssureDao.deleteAirbrushDesignerAssure(id) && abdrImageDao.deleteAbdrImage(id)) {
                map.put("msg", "SUCCESS");
                map.put("code", 0);
            } else {
                map.put("msg", "FAILED");
                map.put("code", 1);
            }
        }
        return map;
    }

    @Override
    public Map insertAirbrushDesignerAssure(AirbrushDesignerAssure airbrushDesignerAssure) {
        Map<String, Object> map = new HashMap<>();
         airbrushDesignerAssure.setCid(changeLoginNameToCompanyID(airbrushDesignerAssure.getCid()));
       if(null != airbrushDesignerAssure.getCname()) {
            airbrushDesignerAssure.setCname(stringUtil.UTF8ToGBK(airbrushDesignerAssure.getCname()));
        }
        if(null != airbrushDesignerAssure.getCustomerAgent()){
            airbrushDesignerAssure.setCustomerAgent(stringUtil.UTF8ToGBK(airbrushDesignerAssure.getCustomerAgent()));
        }
        if(null != airbrushDesignerAssure.getSendbackReason()){
            airbrushDesignerAssure.setSendbackReason(stringUtil.UTF8ToGBK(airbrushDesignerAssure.getSendbackReason()));
        }

        if(airbrushDesignerAssureDao.insertAirbrushDesignerAssure(airbrushDesignerAssure)){
            map.put("msg", "SUCCESS");
            map.put("code", 0);
        }else {
            map.put("msg", "FAILED");
            map.put("code", 1);
        }
        return map;
    }

    private String changeLoginNameToCompanyID(String cid) {
        return web_userDao.changeLoginNameToCompanyID(cid);
    }

}
