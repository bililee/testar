package com.lee.testar.config.mysql;

public enum DbEnum {

    MASTER("master"),

    SLAVE("slave");

    public String name;


    DbEnum(String name){
        this.name = name;
    }
}
