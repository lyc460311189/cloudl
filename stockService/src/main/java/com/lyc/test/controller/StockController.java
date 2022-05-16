package com.lyc.test.controller;

import com.lyc.test.util.RedisUtil;
import com.lyc.test.util.RocketMqUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @Autowired
    private RedisUtil redisUtil;


    @Autowired
    private RocketMqUtil rocketMqUtil;

    @RequestMapping(value = "/get/{id}")
    public Object getById(@PathVariable Integer id){
        Assert.notNull(id,"id不能为null");
        Map<String,Object> result = new HashMap<>();
        result.put(id+"",10);
        //String returnStr =  restTemplate.getForObject("http://127.0.0.1:8001/createOrder/1",String.class);
        //把ip地址改为nacos中的服务名称 这样就灵活了  不用关心ip的变化
        String returnStr =  restTemplate.getForObject("http://order-service/createOrder/1",String.class);
        System.out.println(returnStr);
        return result;
    }


    @RequestMapping(value = "/test/redis")
    public Object add(@RequestParam(required = false) String name){
        System.out.println(name);
        redisUtil.set("name",name);
        return name;
    }

    @RequestMapping(value = "/test/rocketMq")
    public Object sendMsg(@RequestParam(required = false) String name){
        rocketMqUtil.sendMessage("TestTopic",name);
        System.out.println("发送rocketMq 消息" + name);
        return name;
    }

}
