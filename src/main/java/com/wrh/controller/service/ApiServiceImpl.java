package com.wrh.controller.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wuruohong
 * @Classname ApiServiceImpl
 * @Description TODO
 * @Date 2020/8/28 14:52
 */
@Slf4j
@Service
public class ApiServiceImpl implements ApiService {

    //@Value("${param.juzhen1}")
    private int[] testArray1;

    /*@Value("${param.juzhen3: 04,05,06}")
    private double[] testArray2;*/

    /**
     * 这个并非最终方法 当不配置该 key 值，默认值会为空串，它的 length = 1（不同于数组，length = 0），这样解析出来 list 的元素个数就不是空了
     */
    //@Value("#{'${param.list:}'.split(',')}")
    private List<String> list1;

    @Value("#{'${param.emptylist:}'.split(',')}")
    private List<String> emptylist;

    /**
     * 最终版本  加入判空操作
     */
    @Value("#{'${param.list:}'.empty ? null: '${param.list:}'.split(',')}")
    private List<String> list2;

    @Value("#{'${param.set:}'.empty ? null : '${param.set:}'.split(',')}")
    private Set<Integer> testSet;

    //@Value("#{${param.map1}}")
    private Map<String, String> map1;

    //@Value("#{${param.map2}}")
    private Map<String, Integer> map2;


    /**
     * 特别注意的是 @Value 注解不能和 @AllArgsConstructor 注解同时使用，否则会报错
     * 读取配置文件 @Value
     * @return
     */
    @Override
    public String getParam() {
        log.info(">>> testArray1: {}", Arrays.toString(testArray1));
//        log.info(">>> testArray2: {}", Arrays.toString(testArray2));
        log.info(">>> list1: {} size: {}  get0:{}", list1.toString(), list1.size(),list1.get(0));

        log.info(">>> emptylist: {} size: {}  get0:{}", emptylist.toString(), emptylist.size());
        log.info(">>> list2: {} size: {}  get0:{}", list2.toString(), list2.size(),list2.get(0));

        log.info(">>> set: {} size: {}  get:{}", testSet.toString(), testSet.size(), testSet);
        log.info(">>> map1: {} size: {} ", map1, map1.size());
        log.info(">>> map2: {} size: {} ", map2, map2.size());

        return null;
    }
}
