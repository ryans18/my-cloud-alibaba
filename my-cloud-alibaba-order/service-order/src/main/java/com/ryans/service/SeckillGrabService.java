package com.ryans.service;

/**
 * Author：Ryans
 * Date：Created in 2023/9/10 22:11
 * Introduction：
 */
public interface SeckillGrabService {

    // 抢单实际逻辑
    boolean grab(int goodsId, int userId);
}