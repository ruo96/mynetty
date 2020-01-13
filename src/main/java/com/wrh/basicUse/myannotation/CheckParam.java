package com.wrh.basicUse.myannotation;

import com.wrh.basicUse.Enum.CheckTypeEnum;
import com.wrh.basicUse.Enum.TourMarkEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wuruohong
 * @Classname CheckParam
 * @Description TODO
 * @Date 2020/1/9 19:10
 * @Created by wuruohong
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckParam {
    public CheckTypeEnum checkTypeEunm() default CheckTypeEnum.StringOnly;
    public boolean needRoleType() default false;
    public TourMarkEnum roleTypeEnum() default TourMarkEnum.TOUR_MARK;
}
