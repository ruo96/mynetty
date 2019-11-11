package com.wrh.lambda.optionalTest;

import com.alibaba.fastjson.JSON;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;
import java.util.Optional;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:29 2019/10/23 0023
 * @Modified By:
 */
@Slf4j
public class TestOptional {

    @Test
    public void test(){
        Optional<String> optionalS = getOptionalResult();
        optionalS.ifPresent(System.out::println);
    }

    private Optional<String> getOptionalResult() {
        return Optional.of("wrh");
    }

    @Test
    public void test1(){
        //注意可以包装一下
        Optional<CompanyVo> optionalS = Optional.ofNullable(getOptionalCompany());
        Optional<String> name = optionalS.map(CompanyVo::getName);
        name.ifPresent(System.out::println);
    }

    private CompanyVo getOptionalCompany() {
        CompanyVo companyVo = new CompanyVo();
        companyVo.setName("华为");
        return companyVo;
    }

    @Test
    public void test2(){
        Optional<CompanyVo> optionalS = Optional.ofNullable(getOptionalCompanyEmpty());
        String name = optionalS.map(CompanyVo::getName).orElse("no name");
        log.info("===> name:{}",name);

        String name1 = optionalS.map(CompanyVo::getName).orElseThrow(RuntimeException::new);
    }

    private CompanyVo getOptionalCompanyEmpty() {
        CompanyVo companyVo = new CompanyVo();
        return companyVo;
    }

    /**
     *  链式调用
     */
    @Test
    public void test3(){
        CompanyVo vo = new CompanyVo().setName("benz").setMoney(10000).setDate(new Date());

        Company1Vo vo1 = Company1Vo.builder().name("bmw").money(20000).date(new Date()).build();

        log.info("===>  vo {}", JSON.toJSONString(vo));
        log.info("===> vo1 {}", JSON.toJSONString(vo1));
    }
}
