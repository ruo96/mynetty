package com.wrh.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author wuruohong
 * @Classname ComplexAssembly
 * @Description TODO
 * @Date 2020/10/20 19:43
 */
@Data
public class ComplexAssembly {
    private Long id;
    private List<String> list;
    private Map<String, String> map;
    private Properties properties;
    private Set<String> set;
    private String[] array;
}
