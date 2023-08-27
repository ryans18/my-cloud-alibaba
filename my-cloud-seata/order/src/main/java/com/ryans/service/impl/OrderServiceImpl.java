package com.ryans.service.impl;

import com.ryans.entity.TbOrder;
import com.ryans.mapper.OrderMapper;
import com.ryans.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author：Ryans
 * Date：Created in 2023/8/26 22:56
 * Introduction：
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public int add(TbOrder tbOrder) {
        return orderMapper.insert(tbOrder);
    }
}