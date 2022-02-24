package com.wrh.spring.custom;

import com.wrh.spring.custom.service.User;
import org.junit.Test;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.Collections;
import java.util.Set;

/**
 * @author wuruohong
 * @Classname StringToUserPropertyEditor
 * @Description 这个是spring里面会使用的，因此非常重要
 * @Date 2022/2/22 18:03
 */
public class StringToUserPropertyConvert implements ConditionalGenericConverter {

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return sourceType.getType().equals(String.class) && targetType.getType().equals(User.class);
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, User.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        User user = new User();
        user.setName((String) source);
        return user;
    }

    /** 不依赖spring就可使用*/
    /** 这个是spring里面做类型转化用的*/
    @Test
    public void Test36() {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToUserPropertyConvert());
        User value = conversionService.convert("wrh1", User.class);
        System.out.println("value = " + value);
        System.out.println("value.getName() = " + value.getName());
    }

    /** 如果要在spring里面使用自定义类型转换器的话*/
    //@Bean
    public ConversionServiceFactoryBean conversionServiceFactoryBean() {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        conversionServiceFactoryBean.setConverters(Collections.singleton(new StringToUserPropertyConvert()));
        return conversionServiceFactoryBean;
    }

    /** spring内部还有一个更加上层的类型转换方式*/
    /** 当容器获取bean的时候如果指定了类型，那么内部会去调用转换器转换，如果转换失败，那么就会抛出异常*/
    @Test
    public void Test59() {
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();
        typeConverter.registerCustomEditor(User.class, new StringToUserPropertyEditor());
        /** 这里也可以注册上面的 DefaultConversionService*/
        //DefaultConversionService conversionService = new DefaultConversionService();
        //conversionService.addConverter(new StringToUserPropertyConvert());
        //typeConverter.setConversionService(conversionService);

        /** 使用这个方法就可以不用管底层到底用哪个了*/
        User value = typeConverter.convertIfNecessary("123", User.class);
        System.out.println("value.getName() = " + value.getName());


    }
}
