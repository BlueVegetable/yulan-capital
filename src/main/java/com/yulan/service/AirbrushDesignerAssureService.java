package com.yulan.service;

import com.yulan.pojo.AirbrushDesignerAssure;
import com.yulan.pojo.Customer;

import java.util.Map;

public interface AirbrushDesignerAssureService {

    Map getAirbrushDesignerAssure(String cid, String startDate, String endDate, String states, Integer page, Integer lastNum);

    Map updateAirbrushDesignerAssure(AirbrushDesignerAssure airbrushDesignerAssure);

    Map deleteAirbrushDesignerAssure(String id);

    String getAirbrushDesignerAssureId();

    AirbrushDesignerAssure insertAirbrushDesignerAssure( String id, Customer customer, String erpCreator);

}
