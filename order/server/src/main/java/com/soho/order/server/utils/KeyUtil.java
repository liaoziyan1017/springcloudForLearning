package com.soho.order.server.utils;

import java.util.Random;

/**
 * Created by 廖师兄
 * 2017-12-10 16:57
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }

    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextInt(900000));
    }
}
