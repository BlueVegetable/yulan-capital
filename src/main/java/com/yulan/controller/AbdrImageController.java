package com.yulan.controller;

import com.yulan.pojo.AbdrImage;
import com.yulan.service.AbdrImageService;
import com.yulan.utils.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("AbdrImage")
public class AbdrImageController {
    @Autowired
    private AbdrImageService abdrImageService;

    private static final String AbdrImage_Directory = "/abdr-image";

    /**
     * 添加新的委托喷绘图片
     *
     * @param abdrImage
     * @return
     */
    @RequestMapping(value = "insertAbdrImage")
    @ResponseBody
    public Map insertAbdrImage(@RequestBody AbdrImage abdrImage) {
        return abdrImageService.insertAbdrImage(abdrImage);
    }

    /**
     * 文件上传接口
     *
     * @param file
     * @return
     */
    @RequestMapping("upload")
    @ResponseBody
    public Map uploadImg(@RequestParam("file") MultipartFile file) {
        Map<String, Object> data = new HashMap<>(2);

       SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyyMMddHHmmss");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
       String fileName = "AbdrImage" + sdf.format(date);

        Map<String, Object> value = FileUpload.copyAbdrImage(file, fileName);
        String name = (String) value.get("fileName");
        String msg = value.get("msg").equals("SUCCESS") ? "SUCCESS" : "上传失败";
        int code = value.get("code").equals("SUCCESS") ? 0 : 1;
        data.put("path", AbdrImage_Directory + "/" + name);
        data.put("type", value.get("fileType"));

        Map<String, Object> result = new HashMap<>(3);
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }


}
