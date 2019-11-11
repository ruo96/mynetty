package com.wrh.designMode.strategyMode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:06 2019/10/15 0015
 * @Modified By:
 */
@Slf4j
public class TestStrategyMode {

    /**
     * Validator是为客户提供服务时使用的上下文环境，每个Valiator对象中都封装了具体的Strategy对象，
     * 在实际工作中，我们可以通过更换具体的Strategy对象来进行客户服务的升级，而且不需要让客户进行升级。
     */
    @Test
    public void test(){
        Validator numbericValidator = new Validator(new IsNumberic());
        boolean resl = numbericValidator.validate("7780");
        log.info("===>{}",resl);

        Validator lowerCaseValidator = new Validator(new IsAllowerCase());
        boolean res2 = lowerCaseValidator.validate("aaaddd");
        log.info("===>{}",res2);
    }


    /**
     * 如果使用Lambda表达式考虑，你会发现ValidationStrategy就是一个函数接口（还与Predicate具有同样的函数描述），
     * 那么就不需要定义上面那些实现类了，可以直接用下面的代码替换，原因是Lambda表达式内部已经对这些类进行了一定的封装。
     */
    @Test
    public void test1(){
        Validator numbericValidator = new Validator(s -> s.matches("\\d+"));
        boolean resl = numbericValidator.validate("7780");
        log.info("===>{}",resl);

        Validator lowerCaseValidator = new Validator(s->s.matches("[a-z]+"));
        boolean res2 = lowerCaseValidator.validate("aaaddd");
        log.info("===>{}",res2);
    }


}
