package com.ryans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ryans.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Author：Ryans
 * Date：Created in 2023/8/26 22:46
 * Introduction：
 */
public interface AccountMapper extends BaseMapper<Account> {

    @Update("update account set money = money - #{cost} where user_id = #{userId}")
    int updateByUserId(@Param("userId")Integer userId, @Param("cost")Integer cost);

    @Select("select * from account where user_id = #{userId}")
    Account getByUserId(@Param("userId") Integer userId);
}