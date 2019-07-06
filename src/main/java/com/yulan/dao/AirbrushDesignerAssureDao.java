package com.yulan.dao;

import com.yulan.pojo.AirbrushDesignerAssure;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AirbrushDesignerAssureDao {

    List<AirbrushDesignerAssure> getAirbrushDesignerAssure(@Param("CID") String cid, @Param("START_DATE") String startDate,
                                                           @Param("END_DATE")String endDate,@Param("STATE") String states, @Param("start")Integer page,@Param("number") Integer lastNum);

    boolean updateAirbrushDesignerAssure(AirbrushDesignerAssure airbrushDesignerAssure);

    boolean deleteAirbrushDesignerAssure(String id);
}
