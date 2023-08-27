package com.ryans.controller;

import com.ryans.entity.Storage;
import com.ryans.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author：Ryans
 * Date：Created in 2023/8/26 22:53
 * Introduction：
 */
@RestController
@RequestMapping("/storage")
@Slf4j
public class StorageController {
    @Autowired
    private StorageService storageService;

    @PostMapping("/update")
    public int update(@RequestBody Storage storage) {
        log.info("开始更新库存");
        int update = storageService.update(storage);
        log.info("更新库存完毕：" + update);
        return update;
    }

}