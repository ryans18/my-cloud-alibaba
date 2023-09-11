package com.ryans.service.impl;

import com.ryans.entity.TbOrderLock;
import com.ryans.lock.MySqlLock;
import com.ryans.service.SeckillGrabService;
import com.ryans.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author：Ryans
 * Date：Created in 2023/9/10 22:09
 * Introduction：
 */
@Service("seckillMysqlLockService")
public class SeckillMysqlLockServiceImpl implements SeckillService {

    @Autowired
    private SeckillGrabService seckillGrabService;
    @Autowired
    private MySqlLock lock;
    private ThreadLocal<TbOrderLock> orderLockThreadLocal = new ThreadLocal<>();

    @Override
    public String grabOrder(int goodsId, int userId) {
        try {
            System.out.println("用户： " + userId + " 执行秒杀");
            TbOrderLock tbOrderLock = new TbOrderLock(goodsId, userId);
            orderLockThreadLocal.set(tbOrderLock);
            lock.setOrderLockThreadLocal(orderLockThreadLocal);
            lock.lock();
            try {
                boolean grab = seckillGrabService.grab(goodsId, userId);
                if (grab) {
                    System.out.println("抢单成功");
                    return "抢单成功";
                } else {
                    System.out.println("抢单失败");
                    return "抢单失败";
                }
            } finally {
                lock.unlock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "遇到错误";
    }
}