package com.lyc.test.service;

import com.lyc.test.model.Stock;

/**
 * @author liyuchun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/14 13:48
 */
public interface StockSerivce {
    //入库、出库
    public void saveStock(Stock stock);
}
