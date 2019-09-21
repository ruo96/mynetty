package com.wrh.nullJudge;

import com.wrh.callback.Student;
import com.wrh.nullJudge.vo.Dog;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:25 2019/9/10 0010
 * @Modified By:
 */
@Slf4j
public class TestNullJudge {
    @Test
    public void test(){
        Dog dog = new Dog();
        if( null == dog.getAge()){
            log.info("=== result is : null");
            log.info("=== result is : {}", dog.getAge());
        }
        Dog dog1 = new Dog();
        dog1.setAge(dog.getAge());

    }
}
