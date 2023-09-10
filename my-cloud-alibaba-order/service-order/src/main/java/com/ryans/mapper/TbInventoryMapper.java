package com.ryans.mapper;

import com.ryans.entity.TbInventory;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author：Ryans
 * Date：Created in 2023/9/10 22:45
 * Introduction：
 */
@Mapper
public interface TbInventoryMapper {

    TbInventory getById(int goodsId);

    // 扣减库存
    void reduceInventory(TbInventory tbInventory);
}