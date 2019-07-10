package com.yulan.controller;

import com.yulan.pojo.SimpleItem;
import com.yulan.service.SimpleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("simpleItem")
public class SimpleItemController {
    @Autowired
    private SimpleItemService simpleItemService;
    @ResponseBody
    @RequestMapping("getSimpleItemByItemNo")
    public SimpleItem getSimpleItemByItemNo(@RequestParam("itemNo")String itemNo) {
        SimpleItem simpleItem = simpleItemService.getSimpleItemByItemNo(itemNo);
        return simpleItem;
    }
}
