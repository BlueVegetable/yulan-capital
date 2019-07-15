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

    /**
     * 获取银行汇款列表
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     */

    @RequestMapping("getPayBills")
    @ResponseBody
    public Map getPayBills(@RequestBody Map map ) throws UnsupportedEncodingException {

        return paymentBillService.getPayBills(map);
    }

    /**
     * 获取汇款详情
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("getPayBillContent")
    @ResponseBody
    public Map getPayBillContent(@RequestBody Map map ) throws UnsupportedEncodingException {

        return paymentBillService.getPayBillContent(map);
    }

    /**
     *更新汇款凭证状态
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("updatePayBillState")
    @ResponseBody
    public Map updatePayBillState(@RequestBody Map map )  {

        return paymentBillService.updatePayBillState(map);
    }

    /**
     * 更新汇款凭证
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("updatePayBill")
    @ResponseBody
    public Map updatePayBill(@RequestBody Map map ) throws UnsupportedEncodingException {

        return paymentBillService.updatePayBill(map);
    }

    /**
     * 获取客户已填银行账号和汇款人名
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("getPayNameAndAccount")
    @ResponseBody
    public Map getPayNameAndAccount(@RequestBody Map map ) throws UnsupportedEncodingException {

        return paymentBillService.getPayNameAndAccount(map);
    }


}
