package com.wrh.annotate.annotation.check;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * @author wuruohong
 * @Classname ParamConstraintValidated
 * @Description TODO
 * @Date 2020/8/21 18:02
 */
public class ParamConstraintValidated implements ConstraintValidator<Check, Object> {

    /**
     * 合法的参数值， 从注解中获取
     */
    private List<String> paramValues;

    @Override
    public void initialize(Check constraintAnnotation) {
        paramValues = Arrays.asList(constraintAnnotation.paramValues());
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (paramValues.contains(o)) {
            return true;
        }
        /** 不在指定的列表中*/
        return false;
    }
}
