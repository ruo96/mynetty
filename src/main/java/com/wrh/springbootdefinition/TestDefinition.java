package com.wrh.springbootdefinition;

import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.web.servlet.context.WebApplicationContextServletContextAwareProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author wuruohong
 * @Classname TestDefinition
 * @Description TODO
 * @Date 2020/9/3 18:45
 */
@Slf4j
public class TestDefinition {

    /**
     * 容器启动阶段
     * 容器启动时，会通过某种途径加载Configuration MetaData。除了代码方式比较直接外，在大部分情况下，容器需要依赖某些工具类，
     * 比如：BeanDefinitionReader，BeanDefinitionReader会对加载的Configuration MetaData进行解析和分析，并
     * 将分析后的信息组装为相应的BeanDefinition，最后把这些保存了bean定义的BeanDefinition，注册到相应的BeanDefinitionRegistry，
     * 这样容器的启动工作就完成了。
     * 这个阶段主要完成一些准备性工作，更侧重于bean对象管理信息的收集，当然一些验证性或者辅助性的工作也在这一阶段完成。
     * 来看一个简单的例子吧，过往，所有的bean都定义在XML配置文件中，
     *
     * 经过第一阶段，所有bean定义都通过BeanDefinition的方式注册到BeanDefinitionRegistry中
     * 当某个请求通过容器的getBean方法请求某个对象，或者因为依赖关系容器需要隐式的调用getBean时，
     * 就会触发第二阶段的活动：容器会首先检查所请求的对象之前是否已经实例化完成。
     * 如果没有，则会根据注册的BeanDefinition所提供的信息实例化被请求对象，并为其注入依赖。
     * 当该对象装配完毕后，容器会立即将其返回给请求方法使用。BeanFactory只是Spring IoC容器的一种实现，
     * 如果没有特殊指定，它采用采用延迟初始化策略：只有当访问容器中的某个对象时，才对该对象进行初始化和依赖注入操作。
     */
    @Test
    public void Test14() {
        DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();

        AbstractBeanDefinition definition = new RootBeanDefinition(Student.class, 1,true);

        beanRegistry.registerBeanDefinition("student", definition);
        BeanFactory container = (BeanFactory) beanRegistry;
        Student student = (Student) container.getBean("student");
        System.out.println(student);


    }

    /**
     * 而在实际场景下，我们更多的使用另外一种类型的容器：ApplicationContext，
     * 它构建在BeanFactory之上，属于更高级的容器，除了具有BeanFactory的所有能力之外，
     * 还提供对事件监听机制以及国际化的支持等。它管理的bean，在容器启动时全部完成初始化和依赖注入操作。
     *
     * 简单的对比，BeanFactoryPostProcessor处理bean的定义，而BeanPostProcessor则处理bean完成实例化后的对象。
     */
    @Test
    public void Test34() {
        ApplicationContext context;
        PropertyPlaceholderConfigurer configurer;
        BeanPostProcessor postProcessor;

    }

    /**
     * 在Spring中经常能够看到各种各样的Aware接口，其作用就是在对象实例化完成以后将Aware接口定义中规定的依赖注入到当前实例中。
     */
    @Test
    public void Test69() {
        ApplicationContextAware applicationContextAware;
//        ApplicationContextAwareProcessor
    }
}
