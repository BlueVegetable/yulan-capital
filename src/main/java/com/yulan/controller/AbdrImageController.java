package com.yulan.controller;

import com.yulan.pojo.AbdrImage;
import com.yulan.service.AbdrImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("AbdrImage")
public class AbdrImageController {
    @Autowired
    private AbdrImageService abdrImageService;

    /**
     * 添加新的委托喷绘图片
     * @param abdrImage
     * @return
     */
    @RequestMapping(value = "insertAbdrImage")
    @ResponseBody
    public Map insertAbdrImage(@RequestBody AbdrImage abdrImage){
        return abdrImageService.insertAbdrImage(abdrImage);
    }




}
