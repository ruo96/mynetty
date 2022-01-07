package com.wrh.config.importConfig.ImportBeanDefinitionRegistrar;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:30 2019/12/2 0002
 * @Modified By:
 */
// 通过实现 ImportBeanDefinitionRegistrar 导入指定bean
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Rectanagle.class);
        beanDefinitionRegistry.registerBeanDefinition("rectangle", rootBeanDefinition);
    }
}
