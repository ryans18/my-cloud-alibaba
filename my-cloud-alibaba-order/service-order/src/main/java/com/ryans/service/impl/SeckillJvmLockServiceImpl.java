package com.ryans.service.impl;

import com.ryans.service.SeckillGrabService;
import com.ryans.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author：Ryans
 * Date：Created in 2023/9/10 22:09
 * Introduction：
 */
@Service("seckillJvmLockService")
public class SeckillJvmLockServiceImpl implements SeckillService {

    @Autowired
    private SeckillGrabService seckillGrabService;

    @Override
    public String grabOrder(int goodsId, int userId) {
        try {
            System.out.println("用户： " + userId + " 执行秒杀");
            String lock = goodsId + "";
            // 锁住 goodsId 即商品这个对象
            synchronized (this) {
                boolean grab = seckillGrabService.grab(goodsId, userId);
                if (grab) {
                    System.out.println("抢单成功");
                    return "抢单成功";
                } else {
                    System.out.println("抢单失败");
                    return "抢单失败";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "遇到错误";
    }
}