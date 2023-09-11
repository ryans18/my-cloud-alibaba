package com.ryans.service.impl;

import com.ryans.entity.TbOrderLock;
import com.ryans.lock.MySqlLock;
import com.ryans.service.SeckillGrabService;
import com.ryans.service.SeckillService;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author：Ryans
 * Date：Created in 2023/9/10 22:09
 * Introduction：
 */
@Service("seckillRedissonRedLockLockService")
public class SeckillRedissonRedLockLockServiceImpl implements SeckillService {

    @Autowired
    private SeckillGrabService seckillGrabService;
    @Autowired
    private RedissonClient redissonRed1;
    @Autowired
    private RedissonClient redissonRed2;
    @Autowired
    private RedissonClient redissonRed3;
    @Autowired
    private RedissonClient redissonRed4;
    @Autowired
    private RedissonClient redissonRed5;

    @Override
    public String grabOrder(int goodsId, int userId) {
        System.out.println("红锁实现类");
        // 生成key
        String lockKey = ("goodsId_" + goodsId).intern();

        // 红锁
        RLock lock1 = redissonRed1.getLock(lockKey);
        RLock lock2 = redissonRed2.getLock(lockKey);
        RLock lock3 = redissonRed3.getLock(lockKey);
        RLock lock4 = redissonRed4.getLock(lockKey);
        RLock lock5 = redissonRed5.getLock(lockKey);
        RedissonRedLock redLock = new RedissonRedLock(lock1, lock2, lock3, lock4, lock5);
        try {
            redLock.lock(); // 此代码默认 设置key 超时时间30秒，过10秒，再延时
            System.out.println("加锁成功");
            System.out.println("用户： " + userId + " 执行秒杀");
            boolean grab = seckillGrabService.grab(goodsId, userId);
            if (grab) {
                System.out.println("抢单成功");
                return "抢单成功";
            } else {
                System.out.println("抢单失败");
                return "抢单失败";
            }
        } finally {
            redLock.unlock();
        }
    }
}