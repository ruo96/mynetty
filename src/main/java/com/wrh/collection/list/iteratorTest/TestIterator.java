package com.wrh.collection.list.iteratorTest;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import com.wrh.copy.DeepCopyVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:56 2019/10/11 0011
 * @Modified By:
 */
@Slf4j
public class TestIterator {

    @Test
    public void test(){
        List<DeepCopyVo> list = new ArrayList<>();

        DeepCopyVo v1 = new DeepCopyVo();
        v1.setName("w1");
        v1.setAge(0);
        v1.setCars(Lists.newArrayList("dongfeng","dazhong"));

        DeepCopyVo v2 = new DeepCopyVo();
        v2.setName("w2");
        v2.setAge(20);
        v2.setCars(Lists.newArrayList("baoma","benchi"));

        DeepCopyVo v3 = new DeepCopyVo();
        v3.setName("w2");
        v3.setAge(30);
        v3.setCars(Lists.newArrayList("feiji","dapao"));

        list.add(v1);
        list.add(v2);
        list.add(v3);

        log.info("=== result is :{}", JSON.toJSONString(list));

        Iterator<DeepCopyVo> iterable = list.iterator();
        while(iterable.hasNext()){
            DeepCopyVo v4 = iterable.next();
            if(v4.getAge() == 0){
                v4.getCars().add("qirui");
            }

            if(v4.getAge() == 20){
                iterable.remove();
            }

        }


        log.info("=== result is :{}", JSON.toJSONString(list));

    }


    @Test
    public void test1(){

        List<IteratorVo> total = new ArrayList<>();

        IteratorVo it1 = new IteratorVo();
        it1.setBrand("shuai");
        it1.setNum(99);
        it1.setCars(getDeepCopyVoList());

        IteratorVo it2 = new IteratorVo();
        it2.setBrand("chou");
        it2.setNum(100);
        it2.setCars(getDeepCopyVoList2());

        total.add(it1);
        total.add(it2);

        log.info("=== result is :{}", JSON.toJSONString(total));

    }

    private  List<DeepCopyVo> getDeepCopyVoList(){
        List<DeepCopyVo> list = new ArrayList<>();

        DeepCopyVo v1 = new DeepCopyVo();
        v1.setName("w1");
        v1.setAge(0);
        v1.setCars(Lists.newArrayList("dongfeng","dazhong"));

        DeepCopyVo v2 = new DeepCopyVo();
        v2.setName("w2");
        v2.setAge(20);
        v2.setCars(Lists.newArrayList("baoma","benchi"));

        DeepCopyVo v3 = new DeepCopyVo();
        v3.setName("w3");
        v3.setAge(30);
        v3.setCars(Lists.newArrayList("feiji","dapao"));

        list.add(v1);
        list.add(v2);
        list.add(v3);

        return list;
    }

    private  List<DeepCopyVo> getDeepCopyVoList2(){
        List<DeepCopyVo> list = new ArrayList<>();

        DeepCopyVo v1 = new DeepCopyVo();
        v1.setName("w4");
        v1.setAge(40);
        v1.setCars(Lists.newArrayList("baoshijie","jiebao"));

        DeepCopyVo v2 = new DeepCopyVo();
        v2.setName("w5");
        v2.setAge(50);
        v2.setCars(Lists.newArrayList("gongsi","laoban"));

        DeepCopyVo v3 = new DeepCopyVo();
        v3.setName("w6");
        v3.setAge(60);
        v3.setCars(Lists.newArrayList("yingxiong","hanjian"));

        list.add(v1);
        list.add(v2);
        list.add(v3);

        return list;
    }


    @Test
    public void test2(){

        List<IteratorVo> total = new ArrayList<>();

        IteratorVo it1 = new IteratorVo();
        it1.setBrand("w1");
        it1.setNum(11);
//        it1.setCars(getDeepCopyVoList());

        IteratorVo it2 = new IteratorVo();
        it2.setBrand("w2");
        it2.setNum(22);
//        it2.setCars(getDeepCopyVoList2());

        IteratorVo it3 = new IteratorVo();
        it3.setBrand("w3");
        it3.setNum(33);
//        it3.setCars(getDeepCopyVoList2());

        total.add(it1);
        total.add(it2);
        total.add(it3);

        log.info("===> before total: {}",JSON.toJSONString(total));


        Iterator<IteratorVo> iterator = total.iterator();
        while(iterator.hasNext()){
            IteratorVo v1 = iterator.next();

            if(v1.getBrand().equals("w2")){
                iterator.remove();
                continue;
            }else {
                v1.setNum(999);
            }
        }
        log.info("===> after total: {}",JSON.toJSONString(total));



    }
}
