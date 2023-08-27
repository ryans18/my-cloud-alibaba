package com.ryans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ryans.entity.Storage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Author：Ryans
 * Date：Created in 2023/8/26 22:41
 * Introduction：
 */
public interface StorageMapper extends BaseMapper<Storage> {

    @Update("update storage set count = count - #{num} where code = #{code}")
    int updateByCode(@Param("code") String code, @Param("num") int num);

    @Select("select * from storage where code = #{code}")
    Storage getByCode(@Param("code") String code);
}