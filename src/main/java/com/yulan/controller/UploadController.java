package com.yulan.controller;

import com.yulan.service.PaymentBillService;
import com.yulan.utils.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequestMapping("upload")
public class UploadController {
    @Autowired
    private PaymentBillService paymentBillService;


    /**
     * 上传汇款照片
     * @param file
     * @return
     */
    @RequestMapping("uploadPaymentBillImg")
    @ResponseBody
    public Map uploadImg(@RequestParam("file") MultipartFile file){
        String fileName=FileUpload.getFileName();
        Map result= FileUpload.copyPaymentBillImg(file,fileName);

        return result;
    }

    @RequestMapping("deletePaymentBillImg")
    @ResponseBody
    public Map deleteImg(@RequestBody Map map ){
        String path=map.get("path").toString();
        return FileUpload.deleteFile(path);
    }

}
