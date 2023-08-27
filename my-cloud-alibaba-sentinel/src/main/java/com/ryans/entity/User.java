package com.ryans.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author：Ryans
 * Date：Created in 2023/8/17 20:56
 * Introduction：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String username;
    private int age;
    private String address;
}