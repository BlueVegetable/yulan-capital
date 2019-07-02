package com.yulan.controller;

import com.yulan.pojo.ReturnCompensationBill;
import com.yulan.service.ReturnCompensationBillService;
import com.yulan.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Map;

@Controller
@RequestMapping("returnCompensationBill")
public class ReturnCompensationBillController {

    @Autowired
    private ReturnCompensationBillService returnCompensationBillService;

    @ResponseBody
    @RequestMapping("addReturnCompensationBill")
    public Map addReturnCompensationBill(@RequestBody ReturnCompensationBill returnCompensationBill) {
        return Response.getResponseMap(0,"",returnCompensationBillService.addReturnCompensationBill(returnCompensationBill));
    }

    @ResponseBody
    @RequestMapping("deleteReturnCompensationBill")
    public Map deleteReturnCompensationBill(String id) {
        return Response.getResponseMap(0,"",returnCompensationBillService.deleteReturnCompensationBill(id));
    }

    @ResponseBody
    @RequestMapping("getReturnCompensationBills")
    public Map<String,Object> getReturnCompensationBills
            (@RequestParam(value = "CID",required = false)String CID,
             @RequestParam(value = "createName",required = false)String createName,
             @RequestParam(value = "cName",required = false)String cName,
             @RequestParam("page")Integer page, @RequestParam("number")Integer number,
             @RequestParam(value = "startDate",required = false)String startDate,
             @RequestParam(value = "endDate",required = false)String endDate,
             @RequestParam(value = "state",required = false)String state) {
        Timestamp startTime = null,endTime = null;
        if(startDate != null)
        startTime = Timestamp.valueOf(startDate + " 00:00:00");
        if(endDate != null)
        endTime = Timestamp.valueOf(endDate + " 23:59:59");
        Map result = Response.getResponseMap(0,"",returnCompensationBillService.
                getSimpleReturnCompensationBills(CID,page,number,startTime,endTime,state,createName,cName));
        result.put("count",returnCompensationBillService.countSimpleReturnCompensationBills(CID,startTime,endTime,state,createName,cName));
        return result;
    }

    @ResponseBody
    @RequestMapping("getReturnCompensationBillByID")
    public Map<String,Object> getReturnCompensationBillByID(@RequestParam("id")String id) {
        ReturnCompensationBill returnCompensationBill = returnCompensationBillService.getReturnCompensationBill(id);
        return Response.getResponseMap(0,"",returnCompensationBill);
    }

    @ResponseBody
    @RequestMapping("updateReturnCompensationBill")
    public Map updateReturnCompensationBill(@RequestBody ReturnCompensationBill returnCompensationBill) {
        return Response.getResponseMap(0,"",returnCompensationBillService.updateReturnCompensationBill(returnCompensationBill));
    }

}
