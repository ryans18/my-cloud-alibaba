package com.ryans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ryans.entity.Storage;
import com.ryans.mapper.StorageMapper;
import com.ryans.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author：Ryans
 * Date：Created in 2023/8/26 22:49
 * Introduction：
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageMapper storageMapper;
    @Override
    public int update(Storage storage) {
        Storage byCode = storageMapper.getByCode(storage.getCode());
        if (storage.getCount() > byCode.getCount()) {
            throw new RuntimeException("库存不够");
        }
        return storageMapper.updateByCode(storage.getCode(), storage.getCount());
    }
}