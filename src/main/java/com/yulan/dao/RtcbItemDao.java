package com.yulan.dao;

import com.yulan.pojo.RtcbItem;

import java.util.List;

public interface RtcbItemDao {

    int addRtcbItems(List<RtcbItem> rtcbItems);

    List<RtcbItem> getRtcbItemsByRtcbID(String rtcbID);

}