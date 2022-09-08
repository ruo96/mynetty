package com.wrh.basicUse;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.wrh.basicUse.vo.OnLineCleanParamDto;
import com.wrh.entity.Person;
import com.wrh.functionInterfaceTest.Student;
import com.wrh.utils.Md5Utils;
import com.wrh.utils.ObjectCheckHandleUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname TestString
 * @Description TODO
 * @Date 2020/1/9 16:59
 * @Created by wuruohong
 */
@Slf4j
public class TestString {
    @Test
    public void Test() {
        String uid = "123";
        String inter = null;
        String ds = "456";

        String uidRowKey = Joiner.on("_").skipNulls().join(uid, inter,ds);
        log.info(" result: {}",uidRowKey);
    }

    @Test
    public void Test231() {
        String i = "user_online_gameid_";
        String data = "user_online_gameid_20200316_1234_5678";
        log.info("{}",i.length());
        log.info("data: {}, substring:{}",data, StringUtils.substring(data,0,i.length()));
        log.info("data: {}, substring:{}",data, StringUtils.substring(data,0,2));
//        System.out.println(StringUtils.substring(data,0,i.length()));
    }

    @Test
    public void Test22() {

        String a = "";
//        Long b = Long.valueOf(a);
        Long c = Long.valueOf(null);
//        System.out.println(b);
        System.out.println(c);
    }

    @Test
    public void Test23() {
        String a = "12345654634534512312312312312";
        Long start = System.currentTimeMillis();
        int length;
        for (int i = 0; i < 100000000; i++) {
            if(a.length() >20 ){

            }
        }
        Long end = System.currentTimeMillis();
        System.out.println(end - start);

        Long start1 = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            if(StringUtils.length(a) >20 ){

            }
        }
        Long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);

    }

    /**
     * 测试md5工具类
     */
    @Test
    public void Test1() {
        String a = "123";
        String md5Hash = Md5Utils.encrypt(a);
        log.info(" result: {}",md5Hash);

    }

    @Test
    public void Test2() {
        OnLineCleanParamDto student = new OnLineCleanParamDto();
        student.setTourMark("123   ");
        student.setUid("234  ");
        student.setBuvid("21312  ");
        student.setGameId(" 123123s ");
        student.setServerRecTime("dsfs ");
        student.setIntervalTime("  adfas");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        /*for (int i = 0; i < 10000; i++) {

            boolean flag = ObjectCheckHandleUtils.handle(student);
        }*/
        boolean flag = ObjectCheckHandleUtils.handle(student);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
        System.out.println(flag);
        System.out.println(JSON.toJSONString(student));
    }

    @Test
    public void Test3() {
        String[] a = new String[2];
        Object[] b = a;
        a[0] = "hi";
        b[1] = Integer.valueOf(42);
    }

    @Test
    public void Test4() {
        String a = null;
        String b = "1";
        a = "12";
        System.out.println(Integer.valueOf(a));
        System.out.println(Integer.valueOf(b));
    }

    @Test
    public void Test128() {
        String str = String.format("format_%s", 1);
        System.out.println(str);

    }

    @Test
    public void Test135() {
        double a = 0.0;
        System.out.println(a);
    }

    @Test
    public void Test141() {
        int i = 1;
        i = i++;
        System.out.println(i);

    }

    @Test
    public void Test149() {
        String s = "abc";
        changeString(s);
        System.out.println(s);
    }

    private void changeString(String s) {
        s = "123";
    }

    public static final String OFFLINE_TOTAL_KPI = "offline_total_kpi_%s";
    @Test
    public void Test160() {
        Integer i = 100;
        System.out.println(String.format(OFFLINE_TOTAL_KPI, i));

    }

    @Test
    public void Test168() {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println("str1.intern() == str1 = " + (str1.intern() == str1));// 首次出现的字符串，则intern返回的就是堆中的引用，但是如果非首次出现，那么就不一样对象

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println("str2.intern() == str2 = " + (str2.intern() == str2));

        String str3 = new StringBuilder("boo").append("lean").toString();
        System.out.println("str3.intern() == str3 = " + (str3.intern() == str3));


    }

    @Test
    public void Test182() {
        /*int i  = 2;
        int j = --i;
        System.out.println(i);
        System.out.println(j);*/

        Integer[] a = {1,2,3,4,5};
        int index = 4;
        Integer i = a[--index];
        System.out.println("1--i = " + i);
        System.out.println("1--Arrays.toString(a) = " + Arrays.toString(a));
        a[index] = null;
        System.out.println("2--Arrays.toString(a) = " + Arrays.toString(a));
        System.out.println("2--i = " + i);

    }

    @Test
    public void Test202() {
        String str = "UTC+8";
        System.out.println("str.substring(3) = " + str.substring(4));

    }

    @Test
    public void Test209() {
        String ds = "year";
        System.out.println("getStrSwitch(ds) = " + getStrSwitch(ds));

    }

    String getStrSwitch(String ds) {
        String ds1 ;
        switch (ds) {
            case "day":
                System.out.println("is day");
                ds1 = "day";
                break;
            case "year":
                System.out.println("is year");
                ds1 = "year";
                break;
            default:
                return "default";
        }
        return ds1;
    }

    @Test
    public void Test233() throws IOException {
        Person s = new Person();
        s.setName("w1");
        String jsonStr = JSON.toJSONString(s);
        System.out.println("jsonStr = " + jsonStr);
        Person s1 = new ObjectMapper().readValue(jsonStr, Person.class);
        System.out.println("s1 = " + s1);

    }

    @Test
    public void Test247() {
        String a = "abc_%s_%s";
        String b = "model#version";
        String format = String.format(a, b.replace("#", "_"), b.replace("#","*"));
        System.out.println("format = " + format);


    }

    @Test
    public void Test257() {
        String str = "This is A Test String";
        int upCount = 0;
        int lowCount = 0;
        int spaceCount = 0;

        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c >= 'a' && c <= 'z') {
                lowCount++;
            } else if(c >= 'A' && c <= 'Z') {
                upCount++;
            } else if(c == ' '){
                spaceCount++;
            }
        }
        System.out.println("大写之母个数：" + upCount);
        System.out.println("小写字母个数：" + lowCount);
        System.out.println("空格字符个数：" + spaceCount);

    }

    @Test
    public void Test280() {
        Student s = new Student("w1",1,1);
        System.out.println("s = " + s);

        Student s1 = new Student("w2",2,2);
        s1.setName(s.getName().replace("w",""));
        System.out.println("s1 = " + s1);

    }
    
    @Test
    public void Test291() {
        String s = "{\n \"dsl_version\": 2,\n \"initiator\": {\n \"role\": \"guest\",\n \"party_id\": 1000\n },\n \"role\": {\n \"host\": [\n 1100,\n 2000\n ],\n \"guest\": [\n 1000\n ]\n },\n \"job_parameters\": {\n \"common\": {\n \"job_type\": \"train\",\n \"backend\": 0,\n \"work_mode\": 1,\n \"task_cores\": 4,\n \"spark_run\": {\n \"executor-memory\": \"12g\",\n \"executor-cores\": 8,\n \"total-executor-cores\": 24\n }\n }\n\",\n \"namespace\": \"6266858144891146240\"\n }\n },\n \"data_transform_0\": {\n \"with_label\": false\n }\n },\n \"1\": {\n \"reader_0\": {\n \"table\": {\n \"name\": \"6266859830934245376\",\n \"namespace\": \"6266858144891146240\"\n }\n },\n \"data_transform_0\": {\n \"with_label\": false\n }\n }\n }\n }\n }\n}";

//        String s = "{  \"dsl_version\": 2,  \"initiator\": {  \"role\": \"guest\",  \"party_id\": 1000  },  \"role\": {  \"host\": [  1100,  2000  ],  \"guest\": [  1000  ]  },  \"job_parameters\": {  \"common\": {  \"job_type\": \"train\",  \"backend\": 0,  \"work_mode\": 1,  \"task_cores\": 4,  \"spark_run\": {  \"executor-memory\": \"12g\",  \"executor-cores\": 8,  \"total-executor-cores\": 24  }  } \",  \"namespace\": \"6266858144891146240\"  }  },  \"data_transform_0\": {  \"with_label\": false  }  },  \"1\": {  \"reader_0\": {  \"table\": {  \"name\": \"6266859830934245376\",  \"namespace\": \"6266858144891146240\"  }  },  \"data_transform_0\": {  \"with_label\": false  }  }  }  }  } }";

//        s = "{\n\"name\":\"w1\"}";
        s = "{\n  \"dsl_version\": 2,\n  \"initiator\": {\n    \"role\": \"guest\",\n    \"party_id\": 1000\n  },\n  \"role\": {\n    \"host\": [\n      1100,\n      2000\n    ],\n    \"guest\": [\n      1000\n    ]\n  },\n  \"job_parameters\": {\n    \"common\": {\n      \"job_type\": \"train\",\n      \"backend\": 0,\n      \"work_mode\": 1,\n      \"task_cores\": 4,\n      \"spark_run\": {\n        \"executor-memory\": \"12g\",\n        \"executor-cores\": 8,\n        \"total-executor-cores\": 24\n      }\n    }\n  },\n  \"component_parameters\": {\n    \"common\": {\n      \"hetero_secure_boost_0\": {\n        \"task_type\": \"classification\",\n        \"objective_param\": {\n          \"objective\": \"cross_entropy\"\n        },\n        \"num_trees\": 3,\n        \"validation_freqs\": 1,\n        \"encrypt_param\": {\n          \"method\": \"Paillier\"\n        },\n        \"tree_param\": {\n          \"max_depth\": 3\n        }\n      },\n      \"evaluation_0\": {\n        \"eval_type\": \"binary\",\n        \"unfold_multi_result\": false\n      },\n      \"intersection_0\": {\n        \"intersect_method\": \"rsa\",\n        \"rsa_params\": {\n          \"hash_method\": \"sha256\",\n          \"final_hash_method\": \"sha256\",\n          \"random_base_fraction\": 0.11,\n          \"key_length\": 1024,\n          \"random_bit\": 128\n        }\n      },\n      \"hetero_data_split_0\": {\n        \"train_size\": 0.6,\n        \"validate_size\": 0.4\n      }\n    },\n    \"role\": {\n      \"guest\": {\n        \"0\": {\n          \"reader_0\": {\n            \"table\": {\n              \"name\": \"6266858977225609216\",\n              \"namespace\": \"6266858144891146240\"\n            }\n          },\n          \"data_transform_0\": {\n            \"with_label\": true,\n            \"output_format\": \"dense\",\n            \"label_name\": \"y\",\n            \"label_type\": \"int\"\n          }\n        }\n      },\n      \"host\": {\n        \"0\": {\n          \"reader_0\": {\n            \"table\": {\n              \"name\": \"6266859455393042432\",\n              \"namespace\": \"6266858144891146240\"\n            }\n          },\n          \"data_transform_0\": {\n            \"with_label\": false\n          }\n        },\n        \"1\": {\n          \"reader_0\": {\n            \"table\": {\n              \"name\": \"6266859830934245376\",\n              \"namespace\": \"6266858144891146240\"\n            }\n          },\n          \"data_transform_0\": {\n            \"with_label\": false\n          }\n        }\n      }\n    }\n  }\n}";
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
//        map = gson.fromJson(s, map.getClass());
        map = JSON.parseObject(s, Map.class);
//        System.out.println("map.get(\"job_parameters\") = " + map.get("job_parameters"));
//        System.out.println("((Map<String, Object>) map.get(\"job_parameters\")).get(\"common\") = " + ((Map<String, Object>) map.get("job_parameters")).get("common"));
        System.out.println("s.indexOf(\"spark_run\") = " + s.indexOf("spark_run"));
        int a = s.indexOf("{",s.indexOf("spark_run"));
        int b = s.indexOf("}", s.indexOf("spark_run"));
        String substring = s.substring(a, b+1 );
        System.out.println("substring = [" + substring+"]");
        Map<String, Object> rMap = JSON.parseObject(substring, Map.class);
        System.out.println("rMap.get(\"executor-memory\") = " + rMap.get("executor-memory"));
        System.out.println("rMap.get(\"total-executor-cores\") = " + rMap.get("total-executor-cores"));
        int c = s.indexOf("1231231231");
        System.out.println("c = " + c);
//        System.out.println("map = " + map);

    }

    @Test
    public void Test322() {
        String s = "{\n  \"dsl_version\": 2,\n  \"initiator\": {\n    \"role\": \"guest\",\n    \"party_id\": 1000\n  },\n  \"role\": {\n    \"host\": [\n      1100,\n      2000\n    ],\n    \"guest\": [\n      1000\n    ]\n  },\n  \"job_parameters\": {\n    \"common\": {\n      \"job_type\": \"train\",\n      \"backend\": 0,\n      \"work_mode\": 1,\n      \"task_cores\": 4,\n      \"spark_run\": {\n        \"executor-memory\": \"12g\",\n        \"executor-cores\": 8,\n        \"total-executor-cores\": 24\n      }\n    }\n  },\n  \"component_parameters\": {\n    \"common\": {\n      \"hetero_secure_boost_0\": {\n        \"task_type\": \"classification\",\n        \"objective_param\": {\n          \"objective\": \"cross_entropy\"\n        },\n        \"num_trees\": 3,\n        \"validation_freqs\": 1,\n        \"encrypt_param\": {\n          \"method\": \"Paillier\"\n        },\n        \"tree_param\": {\n          \"max_depth\": 3\n        }\n      },\n      \"evaluation_0\": {\n        \"eval_type\": \"binary\",\n        \"unfold_multi_result\": false\n      },\n      \"intersection_0\": {\n        \"intersect_method\": \"rsa\",\n        \"rsa_params\": {\n          \"hash_method\": \"sha256\",\n          \"final_hash_method\": \"sha256\",\n          \"random_base_fraction\": 0.11,\n          \"key_length\": 1024,\n          \"random_bit\": 128\n        }\n      },\n      \"hetero_data_split_0\": {\n        \"train_size\": 0.6,\n        \"validate_size\": 0.4\n      }\n    },\n    \"role\": {\n      \"guest\": {\n        \"0\": {\n          \"reader_0\": {\n            \"table\": {\n              \"name\": \"6266858977225609216\",\n              \"namespace\": \"6266858144891146240\"\n            }\n          },\n          \"data_transform_0\": {\n            \"with_label\": true,\n            \"output_format\": \"dense\",\n            \"label_name\": \"y\",\n            \"label_type\": \"int\"\n          }\n        }\n      },\n      \"host\": {\n        \"0\": {\n          \"reader_0\": {\n            \"table\": {\n              \"name\": \"6266859455393042432\",\n              \"namespace\": \"6266858144891146240\"\n            }\n          },\n          \"data_transform_0\": {\n            \"with_label\": false\n          }\n        },\n        \"1\": {\n          \"reader_0\": {\n            \"table\": {\n              \"name\": \"6266859830934245376\",\n              \"namespace\": \"6266858144891146240\"\n            }\n          },\n          \"data_transform_0\": {\n            \"with_label\": false\n          }\n        }\n      }\n    }\n  }\n}";
        checkSparkResource(s);
    }

    private void checkSparkResource(String configJson) {
        int cpu = 10;
        int mem = 10;
        int sparkIndex = configJson.indexOf("spark_run");
        if (sparkIndex < 0) {
            log.info("[checkSparkResource]>>> configJson not contain spark_run params, please check");
            throw new RuntimeException();  //
        }
        int a = configJson.indexOf("{", configJson.indexOf("spark_run"));
        int b = configJson.indexOf("}", configJson.indexOf("spark_run"));
        Map<String, Object> resourceMap = JSON.parseObject(configJson.substring(a, b + 1), Map.class);
        if(cpu < Integer.parseInt(String.valueOf(resourceMap.get("total-executor-cores"))) ||
                mem < Integer.parseInt(String.valueOf(resourceMap.get("executor-memory")))){
            log.info("[checkSparkResource]>>> configJson exceed resource limit, please check");
            throw new RuntimeException(
                    String.format("项目资源限制 cpu:%sC 内存:%sG", cpu, mem));
        }
        return;
    }


}
