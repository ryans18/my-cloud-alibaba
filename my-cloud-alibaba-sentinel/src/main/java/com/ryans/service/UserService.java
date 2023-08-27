package com.ryans.service;

import com.ryans.entity.User;

/**
 * Author : Ryans
 * Date : 2023/8/25
 * Introduction :
 */
public interface UserService {

    String queryUser();

    User getUser(Long id);
}
