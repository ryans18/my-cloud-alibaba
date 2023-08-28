package com.ryans;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Author : Ryans
 * Date : 2023/8/28
 * Introduction :
 */
public class TestZonedDateTime {

    public static void main(String[] args) {
//        LocalTime localTime = new
        ZonedDateTime time = ZonedDateTime.of(2023, 8,28,14,57,0,0, ZoneId.of("Asia/Shanghai"));
        System.out.println(time);
        System.out.println(ZonedDateTime.now());

    }
}
