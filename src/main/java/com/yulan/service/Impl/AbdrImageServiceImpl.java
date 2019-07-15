package com.yulan.service.Impl;

import com.yulan.dao.AbdrImageDao;
import com.yulan.dao.AirbrushDesignerAssureDao;
import com.yulan.pojo.AbdrImage;
import com.yulan.pojo.AirbrushDesignerAssure;
import com.yulan.service.AbdrImageService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class AbdrImageServiceImpl implements AbdrImageService {
    @Autowired
    private AbdrImageDao abdrImageDao;
    @Autowired
    private AirbrushDesignerAssureDao airbrushDesignerAssureDao;

    private StringUtil stringUtil;

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
        if(null != abdrImage.getSpecifications()){
            abdrImage.setSpecifications(stringUtil.UTF8ToGBK(abdrImage.getSpecifications()));
        }
        if(null != abdrImage.getMemo()){
            abdrImage.setMemo(stringUtil.UTF8ToGBK(abdrImage.getMemo()));
        }

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

    @Override
    public Map getAbdrImage(String abdrImageId) {
        Map<String, Object> map = new HashMap<>();
        List<AbdrImage> abdrImageList = abdrImageDao.getAbdrImage(abdrImageId);
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
        map.put("abdrImageList",abdrImageList);
        map.put("code", 0);

        return map;
    }

}
