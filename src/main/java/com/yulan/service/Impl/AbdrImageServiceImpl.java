package com.yulan.service.impl;

import com.yulan.dao.AbdrImageDao;
import com.yulan.dao.AirbrushDesignerAssureDao;
import com.yulan.pojo.AbdrImage;
import com.yulan.pojo.AirbrushDesignerAssure;
import com.yulan.service.AbdrImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class AbdrImageServiceImpl implements AbdrImageService {
    @Autowired
    private AbdrImageDao abdrImageDao;
    @Autowired
    private AirbrushDesignerAssureDao airbrushDesignerAssureDao;

    /**
     * 增加委托喷绘图片接口
     * @param abdrImage
     * @return
     */
    @Override
    public Map insertAbdrImage(AbdrImage abdrImage) {
        Map<String, Object> map = new HashMap<>();
        AirbrushDesignerAssure airbrushDesignerAssure = airbrushDesignerAssureDao.getAirbrushDesignerAssureSingle(abdrImage.getAbdrId());
        //给图片排序号
        abdrImage.setImageIndex((short)(airbrushDesignerAssure.getImageMaxIndex()+1));
        if(abdrImageDao.insertAbdrImage(abdrImage)){
            airbrushDesignerAssure.setImageMaxIndex((short)(airbrushDesignerAssure.getImageMaxIndex()+1));
            airbrushDesignerAssure.setImageCount((short)(airbrushDesignerAssure.getImageCount()+1));
            airbrushDesignerAssureDao.updateAirbrushDesignerAssure(airbrushDesignerAssure);
            map.put("msg", "SUCCESS");
            map.put("code", 0);
        }else{
            map.put("msg", "FAILED");
            map.put("code", 1);
        }
        return map;
    }

}
