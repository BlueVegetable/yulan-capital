package com.yulan.controller;

import com.yulan.pojo.ReturnCompensationBill;
import com.yulan.service.ReturnCompensationBillService;
import com.yulan.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @RequestMapping("getReturnCompensationBills")
    public Map<String,Object> getReturnCompensationBills(@RequestParam("CID")String CID,@RequestParam("page")Integer page,
                                                         @RequestParam("number")Integer number,@RequestParam("startDate")String startDate,
                                                         @RequestParam("endDate")String endDate,@RequestParam("state")String state) {
        Timestamp startTime = null,endTime = null;
        if(startDate != null)
        startTime = Timestamp.valueOf(startDate + " 00:00:00");
        if(endDate != null)
        endTime = Timestamp.valueOf(endDate + " 23:59:59");
        return Response.getResponseMap(0,"",returnCompensationBillService.getSimpleReturnCompensationBills(CID,page,number,startTime,endTime,state));
    }

    @ResponseBody
    @RequestMapping("getReturnCompensationBillByID")
    public Map<String,Object> getReturnCompensationBillByID(@RequestParam("id")String id) {
        ReturnCompensationBill returnCompensationBill = returnCompensationBillService.getReturnCompensationBill(id);
        return Response.getResponseMap(0,"",returnCompensationBill);
    }

}
