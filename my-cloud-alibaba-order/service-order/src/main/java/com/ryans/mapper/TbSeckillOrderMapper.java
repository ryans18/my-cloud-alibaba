package com.ryans.mapper;

import com.ryans.entity.TbSeckillOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbSeckillOrderMapper {

    void insert(TbSeckillOrder tbOrder);
}
