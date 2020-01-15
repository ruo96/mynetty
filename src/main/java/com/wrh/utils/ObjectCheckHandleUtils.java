package com.wrh.utils;

import com.wrh.basicUse.myannotation.CheckParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @Classname ObjectCheckHandleUtils
 * @Description TODO
 * @Date 2020/1/9 18:19
 * @Created by wuruohong
 */
@Slf4j
public class ObjectCheckHandleUtils {

    public static boolean handle(Object object) {
        //获取该类中所有的域(属性)
        Field[] fields = object.getClass().getDeclaredFields();

        for(Field field : fields) {
            field.setAccessible(true);
//            Annotation[] anns = field.getDeclaredAnnotations();
            CheckParam anns = field.getAnnotation(CheckParam.class);
            if(Objects.isNull(anns)) {
                //没有该注解
                continue;
            }
            //将私有属性设置为可访问状态
//            if(anns[0] instanceof CheckParam) {
            if(anns instanceof CheckParam) {
                //开始针对自定义校验注解进行检查
                CheckParam checkParam = ((CheckParam) anns);
                switch (checkParam.checkTypeEunm()) {
                    case StringOnly:
                        if(field.getType().equals(String.class)) {
                            try {
                                String string = (String)field.get(object);
                                string = StringUtils.trim(string);
                                if (StringUtils.isEmpty(string)) {
                                    return false;
                                }
                                //将所有的空格字符用""替换
                                //相当于调用了set方法设置属性
                                field.set(object,string);
                            } catch (IllegalAccessException e) {
                                return false;
                            }
                        }else {
                            return false;
                        }
                        break;
                    case StringAndMustBeNum:
                        if(field.getType().equals(String.class)) {
                            try {
                                String strMustNum = (String)field.get(object);
                                if (StringUtils.isEmpty(strMustNum) || !StringUtils.isNumeric(strMustNum)) {
                                    log.info(">>> strMustNum is not Num: {}",strMustNum);
                                    return false;
                                }
                                strMustNum = StringUtils.trim(strMustNum);
                                Long longData = Long.valueOf(strMustNum);

                                if (longData.compareTo(0L) <= 0) {
                                    return false;
                                }
                                field.set(object,strMustNum);
                            } catch (IllegalAccessException e) {
                                return false;
                            }
                        }else {
                            return false;
                        }
                        break;
                    default:
                        return true;
                }
            } else {
                return false;
            }
        }
        return true;
    }



    /**
     * @Description : 为object中的所有String属性去除空格字符
     * @param object 待处理的实体对象
     * @return : boolean
     */
    public static boolean replaceBlankSpace(Object object){
        //获取该类中所有的域(属性)
        Field[] fields = object.getClass().getDeclaredFields();

        for(Field field : fields){
            //对所有的属性判断是否为String类型
            if(field.getType().equals(String.class)){
                //将私有属性设置为可访问状态
                field.setAccessible(true);
                try {
                    String string = (String)field.get(object);
                    if (StringUtils.isEmpty(string)) {
                        return false;
                    }
                    //将所有的空格字符用""替换
                    string = StringUtils.trim(string.replaceAll(" ",""));
                    //相当于调用了set方法设置属性
                    field.set(object,string);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }/*else if ( field.getType().equals(Long.class)) {
                field.setAccessible(true);
                Long longdata = Long.valueOf(String.valueOf(object));

            }*/
        }
        return true;
    }


    /**
     * @Description : 为object中的所有String属性去除空格字符，Long类型判断是否为负数
     * @param object 待处理的实体对象
     * @return : boolean
     */
    public static boolean replaceBlankSpaceAndCheckNum(Object object){
        //获取该类中所有的域(属性)
        Field[] fields = object.getClass().getDeclaredFields();

        for(Field field : fields){
            //对所有的属性判断是否为String类型
            if(field.getType().equals(String.class)){
                //将私有属性设置为可访问状态
                field.setAccessible(true);
                try {
                    String string = (String)field.get(object);
                    //将所有的空格字符用""替换
                    string = string.replaceAll(" ","");
                    //相当于调用了set方法设置属性
                    field.set(object,string);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

}
