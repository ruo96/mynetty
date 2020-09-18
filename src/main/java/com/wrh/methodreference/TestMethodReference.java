package com.wrh.methodreference;

import com.wrh.serialize.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author wuruohong
 * @Classname TestMethodReference
 * @Description TODO
 * @Date 2020/9/18 10:15
 */
@Slf4j
public class TestMethodReference {

    /**
     * 方法引用分为三种，方法引用通过一对双冒号:: 来表示，方法引用是一种函数式接口的另一种书写方式
     * 静态方法引用，通过类名::静态方法名， 如 Integer::parseInt
     * 实例方法引用，通过实例对象::实例方法，如 str::substring
     * 构造方法引用，通过类名::new， 如 User::new
     */
    @Test
    public void Test15() {
        /**
         *  使用双冒号::来构造静态函数引用
         */
        Function<String, Integer> fun = Integer::parseInt;
        Integer value = fun.apply("123");
        System.out.println(value);

        /**
         * 使用双冒号::来构造非静态函数引用
         */
        String content = "hello world";
        Function<Integer, String> func = content::substring;
        String result = func.apply(1);
        System.out.println(result);

        BiFunction<String, String, User> biFunction = User::new;
        User user = biFunction.apply("w1","w2");
        System.out.println(user.toString());

        sayHello(String::toUpperCase, "hello");

    }

    // 方法有两个参数，一个是
    private static void sayHello(Function<String, String> func, String parameter){
        String result = func.apply(parameter);
        System.out.println(result);
    }
}
