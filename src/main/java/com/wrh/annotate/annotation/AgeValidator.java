package com.wrh.annotate.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:36 2019/11/11 0011
 * @Modified By:
 */
public class AgeValidator implements ConstraintValidator<Odd, Integer> {
    @Override
    public void initialize(Odd constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
        return age % 2 != 0;
    }
}
