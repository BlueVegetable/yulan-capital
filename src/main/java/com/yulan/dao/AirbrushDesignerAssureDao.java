package com.yulan.dao;

import com.yulan.pojo.AirbrushDesignerAssure;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AirbrushDesignerAssureDao {

    List<AirbrushDesignerAssure> getAirbrushDesignerAssure(@Param("CID") String cid, @Param("START_DATE") String startDate,
                                                           @Param("END_DATE")String endDate,@Param("STATE") String states, @Param("start")Integer page,@Param("number") Integer lastNum);

    AirbrushDesignerAssure getAirbrushDesignerAssureSingle(String id);

    List<Map<String,Object>> findAirbrushDesignerAssureId(@Param("idDate")String idDate);

    boolean updateAirbrushDesignerAssure(AirbrushDesignerAssure airbrushDesignerAssure);

    boolean deleteAirbrushDesignerAssure(String id);

    boolean insertAirbrushDesignerAssure(AirbrushDesignerAssure airbrushDesignerAssure);
}
