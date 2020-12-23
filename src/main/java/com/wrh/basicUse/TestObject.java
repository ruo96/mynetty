package com.wrh.basicUse;

import com.wrh.basicUse.vo.Blog;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wuruohong
 * @Classname TestObject
 * @Description TODO
 * @Date 2020/11/26 11:48
 */
@Slf4j
public class TestObject {

    /**
     * 查看对象的内存布局
     * 开启指针压缩，对象大小（16字节） = MarkWord（8字节）+ KlassPointer（4字节）+ 数组长度（0字节） + 实例数据（0字节）+ 对齐填充（4字节）
     * 关闭指针压缩，对象大小（16字节）= MarkWord（8字节）+ KlassPointer（8字节）+ 数组长度（0字节）+ 实例数据（0字节） + 对齐填充（0字节）
     */
    @Test
    public void Test15() {
        Object o = new Object();
        log.info("layout: {}",ClassLayout.parseInstance(o).toPrintable());
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

    }


    /**
     * 关于如何对象的大小，其实很简单，我们首先关注是否是开启了指针压缩，然后关注其是普通对象还是数组对象，这里做个总结。
     * 如果是普通对象，那么只需要计算：MarkWord+KlassPointer（8B）+实例数据+对齐填充。
     * 如果是数组对象，则需要分两种情况，如果是开启指针压缩的情况，那么分为五段：MarkWord+KlassPointer（4B）+第一段对齐填充+实例数据+第二段对齐填充。
     * 如果对象中存在引用类型数据，则保存的只是指向这个数据的指针，在开启指针压缩的情况下，为4字节，关闭指针压缩为8字节。
     * 如果对象中存在基本数据类型，那么保存的就是其实体，这就需要按照8中基本数据类型的大小来灵活计算了。
     */
    @Test
    public void Test34() {
        Blog blog = new Blog();
        System.out.println(ClassLayout.parseInstance(blog).toPrintable());

    }
}
