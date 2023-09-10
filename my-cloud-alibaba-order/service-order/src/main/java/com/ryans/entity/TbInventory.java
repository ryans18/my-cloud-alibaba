package com.ryans.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * Author：Ryans
 * Date：Created in 2023/9/10 22:29
 * Introduction：
 */
@Mapper
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbInventory {

    private Integer goodsId;
    private Integer num;  // 库存数量
    private Date updateTime = new Date();
}