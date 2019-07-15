package com.yulan.service.Impl;

import com.yulan.dao.AbdrImageDao;
import com.yulan.dao.AirbrushDesignerAssureDao;
import com.yulan.dao.Web_userDao;
import com.yulan.pojo.AbdrImage;
import com.yulan.pojo.AirbrushDesignerAssure;
import com.yulan.pojo.Customer;
import com.yulan.pojo.Web_user;
import com.yulan.service.AirbrushDesignerAssureService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
        if(cid != null) {
            cid = changeLoginNameToCompanyID(cid);
        }
        List<AirbrushDesignerAssure> airbrushDesignerAssureList =
                airbrushDesignerAssureDao.getAirbrushDesignerAssure(cid,
                        startDate, endDate, states, page, lastNum);
        for(int i = 0; i < airbrushDesignerAssureList.size() ; i++){
            AirbrushDesignerAssure airbrushDesignerAssure = airbrushDesignerAssureList.get(i);
            airbrushDesignerAssure.setRealName(stringUtil.GBKToUTF8(web_userDao.getWebUserNameById(airbrushDesignerAssure.getErpCreator())));
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
            case"客户确认中":
                airbrushDesignerAssure.setState("CUSTOMERAFFIRM");
                break;
                default:
                    map.put("data","客户状态不正确");
                    flag = 1;

        }
        Date reassureTs =new Date(System.currentTimeMillis());//测试接口
        airbrushDesignerAssure.setReassureTs(reassureTs);

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
    public AirbrushDesignerAssure insertAirbrushDesignerAssure(String id, Customer customer, String erpCreator) {
        AirbrushDesignerAssure airbrushDesignerAssure = new AirbrushDesignerAssure();

        Date createTs =new Date(System.currentTimeMillis());//测试接口
        airbrushDesignerAssure.setCreateTs(createTs);

        airbrushDesignerAssure.setCid(customer.getCustomerCode());
        airbrushDesignerAssure.setId(id);
        airbrushDesignerAssure.setOfficeTel(customer.getOfficeTel());
        airbrushDesignerAssure.setErpCreator(erpCreator);
       //存进数据库的时候要转码
       if(null != customer.getShortName()) {
            airbrushDesignerAssure.setCname(stringUtil.UTF8ToGBK(customer.getShortName()));
        }
        if(null != customer.getCustomerAgent()){
            airbrushDesignerAssure.setCustomerAgent(stringUtil.UTF8ToGBK(customer.getCustomerAgent()));
        }
        airbrushDesignerAssureDao.insertAirbrushDesignerAssure(airbrushDesignerAssure);

        //但是返回的数据要是UTF-8
        airbrushDesignerAssure.setCname(customer.getCustomerAgent());
        airbrushDesignerAssure.setCustomerAgent(customer.getCustomerAgent());
        return airbrushDesignerAssure;
    }

    //创建喷绘确认书流水号
    @Override
    public String getAirbrushDesignerAssureId() {
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

    private String changeLoginNameToCompanyID(String cid) {
        return web_userDao.changeLoginNameToCompanyID(cid);
    }

}
