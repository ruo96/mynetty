package com.wrh.nettyhttpserver.config;

import com.wrh.spring.custom.StringToUserPropertyEditor;
import com.wrh.spring.custom.service.User;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuruohong
 * @Classname AppConfig
 * @Description TODO
 * @Date 2022/2/22 14:56
 */
@Configuration
@ComponentScan(value = "com.wrh.nettyhttpserver"
/*excludeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = UserService.class
)},*/
/*includeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = UserService.class
        )
}*/)
public class NettyHttpConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        /** 这个就是i18n目录下文件的前缀*/
        messageSource.setBasename("messages");
        return messageSource;

        /** 国际化的使用*/
        //context.getMessage("自定义的展示，就是配置文件的key",null, new Locale("这个里面就是文件后缀"));
    }

    @Bean
    public CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();

        Map<Class<?>, Class<? extends PropertyEditor>> propertyEditorMap = new HashMap<>();
        propertyEditorMap.put(User.class, StringToUserPropertyEditor.class);

        customEditorConfigurer.setCustomEditors(propertyEditorMap);
        return customEditorConfigurer;
    }
}
