package com.ryans.service.impl;

import com.ryans.entity.TbInventory;
import com.ryans.entity.TbOrder;
import com.ryans.mapper.TbInventoryMapper;
import com.ryans.mapper.TbOrderMapper;
import com.ryans.service.SeckillGrabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Author：Ryans
 * Date：Created in 2023/9/10 22:17
 * Introduction：
 */
@Service
public class SeckillGrabServiceImpl implements SeckillGrabService {

    @Autowired
    private TbInventoryMapper tbInventoryMapper;
    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Override
    public boolean grab(int goodsId, int userId) {
        TbInventory tbInventory = tbInventoryMapper.getById(goodsId);
        try {
            // 模拟多并发停留
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 拿到库存
        int num = tbInventory.getNum().intValue();
        System.out.println("库存 num：" + num);
        if (num > 0) {
            // 扣减库存
            tbInventoryMapper.reduceInventory(tbInventory);
            // 新增订单
            TbOrder tbOrder = new TbOrder();
            tbOrder.setOrderDescription("用户 "+userId + " 抢到了华为Mate60 pro手机");
            tbOrder.setOrderStatus(1);
            tbOrder.setUserId(userId);
            tbOrderMapper.insert(tbOrder);
        }
        return false;
    }
}