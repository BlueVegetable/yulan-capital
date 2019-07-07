package com.yulan.controller;

import com.yulan.service.PaymentBillService;
import com.yulan.utils.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("PaymentBill")
public class PaymentBillController {
    @Autowired
    private PaymentBillService paymentBillService;


    /**
     * 生成流水号
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("getPayId")
    @ResponseBody
    public Map getPayId( ) throws UnsupportedEncodingException {
        Map result=new HashMap();
        result.put("data",paymentBillService.getBigPaymentBillId());
        result.put("code",0);
        result.put("msg","SUCCESS");
        return result;
    }

    /**
     * 新建汇款凭证
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("insertPaymentBill")
    @ResponseBody
    public Map insertPaymentBill(@RequestBody Map map ) throws UnsupportedEncodingException {

        return paymentBillService.insertPaymentBill(map);
    }
}
