package com.ryans.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * Author：Ryans
 * Date：Created in 2023/9/10 22:36
 * Introduction：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbSeckillOrder {

    private Long orderId;
    private int orderStatus;
    private String orderDescription;
    private Integer userId;
    private Date updateTime = new Date();
}