package com.yulan.controller;

import com.yulan.pojo.AirbrushDesignerAssure;
import com.yulan.service.AirbrushDesignerAssureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("AirbrushDesignerAssure")
public class AirbrushDesignerAssureController {
    @Autowired
    private AirbrushDesignerAssureService airbrushDesignerAssureService;

    /**
     * 获取委托喷绘书接口
     * @param data
     * @return
     */
    @RequestMapping(value = "getAirbrushDesignerAssure")
    @ResponseBody
    public Map getAirbrushDesignerAssure(@RequestBody Map<String,Object> data){
        String cid = (String)data.get("cid");
        String state = (String)data.get("state");
        String startDate = (String)data.get("startDate");
        String endDate = (String)data.get("endDate");
        Integer limit = (Integer) data.get("limit");
        Integer page = (Integer)data.get("page");
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
        }
        int lastNum=page+limit-1;

        if (null != cid && cid.equals("")){
            cid=null;
        }
        if (null != state && state.equals("")){
            state=null;
        }
        if(null != startDate && startDate.equals("")){
            startDate = null;
        }
        if(null != endDate && endDate.equals("")){
            endDate = null;
        }

        return airbrushDesignerAssureService.getAirbrushDesignerAssure(cid,startDate,endDate, state, page, lastNum);
    }

    /**
     * 修改委托喷绘书客户状态接口
     * @param airbrushDesignerAssure
     * @return
     */
    @RequestMapping(value = "updateAirbrushDesignerAssure")
    @ResponseBody
    public Map updateAirbrushDesignerAssure(@RequestBody AirbrushDesignerAssure airbrushDesignerAssure){

        return airbrushDesignerAssureService.updateAirbrushDesignerAssure(airbrushDesignerAssure);
    }

    /**
     * 新增喷绘确认接口
     * @param airbrushDesignerAssure
     * @return
     */
    @RequestMapping("insertAirbrushDesignerAssure")
    @ResponseBody
    public Map insertAirbrushDesignerAssure(@RequestBody AirbrushDesignerAssure airbrushDesignerAssure){
        return airbrushDesignerAssureService.insertAirbrushDesignerAssure(airbrushDesignerAssure);
    }

    /**
     * 删除委托喷绘书接口
     * @param data
     * @return
     */
    @RequestMapping(value = "deleteAirbrushDesignerAssure")
    @ResponseBody
    public Map deleteAirbrushDesignerAssure(@RequestBody Map<String,Object> data){
        String id = (String)data.get("id");
        return airbrushDesignerAssureService.deleteAirbrushDesignerAssure(id);
    }

}
