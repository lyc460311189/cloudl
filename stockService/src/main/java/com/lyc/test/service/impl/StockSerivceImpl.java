package com.lyc.test.service.impl;

import com.lyc.test.mapper.StockMapper;
import com.lyc.test.model.Stock;
import com.lyc.test.service.StockSerivce;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liyuchun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/14 13:51
 */
@Service
public class StockSerivceImpl implements StockSerivce {

    @Resource
    private StockMapper stockMapper;

    @Override
    public void saveStock(Stock stock) {
        stockMapper.insert(stock);
    }


}
