package com.ryans.mapper;

import com.ryans.entity.TbOrderLock;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author : Ryans
 * Date : 2023/9/11
 * Introduction :
 */
@Mapper
public interface TbOrderLockMapper {
    void insert(TbOrderLock orderLock);
    void delete(int goodsId);
}
