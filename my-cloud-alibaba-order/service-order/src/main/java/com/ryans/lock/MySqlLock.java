package com.ryans.lock;

import com.ryans.entity.TbOrderLock;
import com.ryans.mapper.TbOrderLockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Author : Ryans
 * Date : 2023/9/11
 * Introduction :
 */
@Component
public class MySqlLock{

    @Autowired
    private TbOrderLockMapper tbOrderLockMapper;
    private ThreadLocal<TbOrderLock> orderLockThreadLocal;

    public void lock() {
        // 1. 尝试加锁
        if (tryLock()) {
            System.out.println("尝试加锁成功");
            return;
        }
        // 2. 休眠
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 3. 递归再次调用
        lock();
    }
    public void unlock() {
        tbOrderLockMapper.delete(orderLockThreadLocal.get().getGoodsId());
        System.out.println("解锁对象：" + orderLockThreadLocal.get());
        orderLockThreadLocal.remove();
    }

    private boolean tryLock() {
        try {
            TbOrderLock tbOrderLock = orderLockThreadLocal.get();
            tbOrderLockMapper.insert(tbOrderLock);
            System.out.println("加锁对象：" + tbOrderLock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setOrderLockThreadLocal(ThreadLocal<TbOrderLock> orderLockThreadLocal) {
        this.orderLockThreadLocal = orderLockThreadLocal;
    }
}
