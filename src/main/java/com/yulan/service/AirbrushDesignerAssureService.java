package com.yulan.service;

import com.yulan.pojo.AirbrushDesignerAssure;

import java.util.Map;

public interface AirbrushDesignerAssureService {

    Map getAirbrushDesignerAssure(String cid, String startDate, String endDate, String states, Integer page, Integer lastNum);

    Map updateAirbrushDesignerAssure(AirbrushDesignerAssure airbrushDesignerAssure);

    Map deleteAirbrushDesignerAssure(String id);


}
