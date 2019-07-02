package com.yulan.dao;

import com.yulan.pojo.RtcbItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RtcbItemDao {

    int addRtcbItems(@Param("rtcbItems") List<RtcbItem> rtcbItems);

    List<RtcbItem> getRtcbItemsByRtcbID(String rtcbID);

}