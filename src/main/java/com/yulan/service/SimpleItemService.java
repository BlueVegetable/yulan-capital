package com.yulan.service;

import com.yulan.dao.SimpleItemDao;
import com.yulan.pojo.SimpleItem;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleItemService {
    @Autowired
    private SimpleItemDao simpleItemDao;
    public SimpleItem getSimpleItemByItemNo(String itemNo) {
        SimpleItem simpleItem = simpleItemDao.getSimpleItemByItemNo(itemNo);
        simpleItem.setProductVersionName(StringUtil.GBKToUTF8(simpleItem.getProductVersionName()));
        simpleItem.setUnit(StringUtil.GBKToUTF8(simpleItem.getUnit()));
        return simpleItem;
    }
}
