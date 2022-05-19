package com.lyc.test.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author liyuchun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/19 11:38
 */
@RestController
@Slf4j
public class RateLimitController {

    // 测试QPS
    // 线程数的测试
    @GetMapping("testA")
    public String testA() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "测试TestA";
    }

    @GetMapping("testB")
    public String testB() {
        log.info("1秒处理一个请求，排队处理......");
        //log.info("TestB运行中.....");
        return "测试TestB";
    }

    @GetMapping("testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //log.info("TestB运行中.....");
        return "测TestD中RT配置";
    }

    @GetMapping("testC")
    public String testD1() {
        int i = 10 / 0;
        //log.info("TestB运行中.....");
        return "测TestC中异常比例配置";
    }

    @GetMapping("testE")
    public String testE() {
        int i = 10 / 0;
        log.info("测TestE中异常输配置");
        return "测TestE中异常输配置";
    }

    @GetMapping("testHotkey")
    @SentinelResource(value = "testHotkey", /*defaultFallback = "hotkeyHandler"*/blockHandler = "handler")
    public String testHotkey(
            @RequestParam(value = "p1",required = false) String p1,
            @RequestParam(value = "p2",required = false) String p2) {
        return "测试热点key限流";
    }

    public String handler(String p1, String p2, BlockException e) {
        return "fdsfdsfdsf " + e.getMessage();
    }

    public String hotkeyHandler() {
        return "触发降级方法!";
    }
}
