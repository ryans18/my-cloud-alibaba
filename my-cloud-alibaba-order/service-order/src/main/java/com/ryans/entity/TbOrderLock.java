package com.ryans.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author : Ryans
 * Date : 2023/9/11
 * Introduction :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbOrderLock {
    private Integer goodsId;
    private Integer userId;
}
