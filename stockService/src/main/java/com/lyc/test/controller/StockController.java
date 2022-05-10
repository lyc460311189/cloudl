package com.lyc.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liyuchun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/7 18:20
 */


@RestController
public class StockController {


    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/get/{id}")
    public Object getById(@PathVariable Integer id){
        Assert.notNull(id,"id不能为null");
        Map<String,Object> result = new HashMap<>();
        result.put(id+"",10);
        //String returnStr =  restTemplate.getForObject("http://127.0.0.1:8001/createOrder/1",String.class);
        String returnStr =  restTemplate.getForObject("http://order-service/createOrder/1",String.class);
        System.out.println(returnStr);
        return result;
    }
}
