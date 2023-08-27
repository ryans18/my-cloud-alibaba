package com.ryans.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author：Ryans
 * Date：Created in 2023/8/26 22:38
 * Introduction：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer id;
    private Integer userId;
    private int money;
}