package com.wrh.controller.multidb;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "multi")
@Configuration
public class DbsProperties {
    private Map<String, Map<String, String>> dbs = new HashMap<>();

    public Map<String, Map<String, String>> getDbs() {
        return dbs;
    }

    public void setDbs(Map<String, Map<String, String>> dbs) {
        this.dbs = dbs;
    }
}
