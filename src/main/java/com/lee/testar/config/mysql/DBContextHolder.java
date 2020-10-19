package com.lee.testar.config.mysql;

import java.util.concurrent.atomic.AtomicInteger;

public class DBContextHolder {



    private static final ThreadLocal<DbEnum> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DbEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DbEnum get() {
        return contextHolder.get();
    }

    public static void master() {
        set(DbEnum.MASTER);
        System.out.println("切换到master");
    }

    public static void slave() {
        //  轮询
        int index = counter.getAndIncrement() % 2;
        if (counter.get() > 9999) {
            counter.set(-1);
        }
        if (index == 0) {
            set(DbEnum.SLAVE);
            System.out.println("切换到slave1");
        }else {
            set(DbEnum.SLAVE);
            System.out.println("切换到slave1");
        }
    }
}
