package com.wrh.bean.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

/**
 * @author wuruohong
 * @Classname GPInstantiationAwareBeanPostProcessor
 * @Description TODO
 * @Date 2021/12/14 16:14
 */
@Slf4j
@Component
public class GPInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    public GPInstantiationAwareBeanPostProcessor() {
        super();
        log.info("调用InstantiationAwareBeanPostProcessorAdapter实现类构造器！！");
    }

    // 接口方法、实例化Bean之前调用

    /**
     * 在实例化目标Bean之前应用此BeanPostProcessor。
     * 这个返回的Bean也许是一个代理代替目标Bean，有效地抑制目标Bean的默认实例化。
     * 如果此方法返回一个非空对象，则Bean的创建过程将被短路。
     * 唯一的进一步处理被应用是BeanPostProcessor.postProcessAfterInitialization(java.lang.Object, java.lang.String)方法
     * （改变了Bean的生命周期实例化之后直接进入BeanPostProcessor.postProcessAfterInitialization）回调来自于配置好的BeanPostProcessors。
     * 这个回调将仅被应用于有Bean Class的BeanDefintions。特别是，它不会应用于采用”factory-method“的Bean。
     * 后处理器可以实现扩展的SmartInstantiationAwareBeanPostProcessor接口，以便预测它们将返回的Bean对象的类型
     */
    @Override
    public Object postProcessBeforeInstantiation(Class beanClass,String beanName) throws BeansException {
        log.info("InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法");
        return null;
    }

    // 接口方法、实例化Bean之后调用

    /**
     * 在Bean初始化回调（如InitializingBean的afterPropertiesSet或者定制的init-method）之后，
     * 应用这个BeanPostProcessor去给一个新的Bean实例。Bean已经配置了属性值，返回的Bean实例可能已经被包装。
     * 如果是FactoryBean，这个回调将为FactoryBean实例和其他被FactoryBean创建的对象所调用。
     * 这个post-processor可以通过相应的FactoryBean实例去检查决定是否应用FactoryBean或者被创建的对象或者两个都有。
     * 这个回调在一个由InstantiationAwareBeanPostProcessor短路的触发之后将被调用
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法");
        return bean;
    }

    // 接口方法、设置某个属性时调用

    /**
     *在工厂将给定的属性值应用到给定的Bean之前，对给定的属性值进行后处理。
     * 允许检查全部依赖是否已经全部满足，例如基于一个@Required在Bean属性的Setter方法上。
     * 还允许替换要应用的属性值，通常通过基于原始的PropertyValues创建一个新的MutablePropertyValues实例，添加或删除特定的值
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        log.info("InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法");
        return pvs;
    }


}
