package com.lee.testar.config.mysql;

import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

public class MyRoutingDataSource extends AbstractRoutingDataSource {



    @Nullable
    @Override
    public Object determineCurrentLookupKey(){
        return DBContextHolder.get();
    }
}
