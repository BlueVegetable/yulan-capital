package com.yulan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequestMapping("upload")
public class UploadController {


    @RequestMapping("upload")
    @ResponseBody
    public Map uploadImg(@RequestParam("file") MultipartFile file, @RequestParam("imgType") String imgType ,
                         @RequestParam("fileName")String fileName){
        return null;
    }
}
