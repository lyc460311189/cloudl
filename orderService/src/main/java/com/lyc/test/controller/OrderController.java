package com.lyc.test.controller;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liyuchun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/7 18:25
 */
@RestController
public class OrderController {


    @RequestMapping(value = "/createOrder/{goodsId}")
    public Object getById(@PathVariable Integer goodsId){
        //TODO 此处获取用户id   计划用JWT 实现  头信息中 token的方式
        Assert.notNull(goodsId,"goodsId不能为null");
        Map<String,Object> result = new HashMap<>();
        result.put("goodsName","电脑");
        result.put("goodsPrice","5000");
        result.put("num",1);
        return result;
    }
}
