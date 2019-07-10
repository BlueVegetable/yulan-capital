package com.yulan.dao;

import com.yulan.pojo.SimpleItem;

public interface SimpleItemDao {
    SimpleItem getSimpleItemByItemNo(String itemNo);
}
