package com.wrh.controller.multidb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//@Configuration
public class DynamicDataSourceConfiguration {
    @Autowired
    DbsProperties properties;

    //@Bean
    public DataSource dataSource(){
        DynamicDataSource dataSource = new DynamicDataSource();
        final Map<Object,Object> targetDataSource  = getTargetDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        //TODO 默认数据库需要设置
        dataSource.setDefaultTargetDataSource(targetDataSource.values().iterator().next());
        return dataSource;
    }

    private Map<Object,Object> getTargetDataSource(){
        Map<Object,Object> dataSources = new HashMap<>();
        this.properties.getDbs().entrySet().stream()
                .forEach(e->{
                    DriverManagerDataSource dmd = new DriverManagerDataSource();
                    dmd.setUrl(e.getValue().get("url"));
                    dmd.setDriverClassName(e.getValue().get("driver-class-name"));
                    dataSources.put(e.getKey(),dmd);
                });
        return  dataSources;
    }
}
