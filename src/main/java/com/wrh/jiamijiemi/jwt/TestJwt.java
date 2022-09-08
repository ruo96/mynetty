package com.wrh.jiamijiemi.jwt;

import clojure.lang.IFn;
import com.wrh.functionInterfaceTest.Student;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2022/9/1 17:25
 */
public class TestJwt {


    @Test
    public void Test10() {
        long currentTime = System.currentTimeMillis() + 30000;
        Date date = new Date(currentTime);
        System.out.println("LocalDateTime.now().toString() = " + LocalDateTime.now().toString());
        JwtBuilder builder = Jwts.builder()
                .setId("123")
                .setSubject(new Student("w1",2,3).toString())
                .setIssuedAt(new Date())
                .claim("name1","w2")
//                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256, "w123456");
        System.out.println("LocalDateTime.now().toString() = " + LocalDateTime.now().toString());

        System.out.println("builder.compact() = " + builder.compact());
        // eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjMiLCJzdWIiOiJTdHVkZW50KG5hbWU9dzEsIGFnZT0yLCBzdGF0dXJlPTMpIiwiaWF0IjoxNjYyMDI1MTYxfQ.IuRsy9NdEbNrKspnDqAPIO-sGk5CBpCSq-fJfppWJdc

    }

    @Test
    public void Test31() {
        System.out.println("LocalDateTime.now().toString() = " + LocalDateTime.now().toString());   // 49:45

        String s = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjMiLCJzdWIiOiJTdHVkZW50KG5hbWU9dzEsIGFnZT0yLCBzdGF0dXJlPTMpIiwiaWF0IjoxNjYyMDI2Njk0LCJuYW1lMSI6IncyIn0.YxvlxgGt3SXiIFXqkVd9Ok7Grdptn15TveL44G5AqkE";
        Claims claims = Jwts.parser().setSigningKey("w123456").parseClaimsJws(s).getBody();
        System.out.println("claims = " + claims);
        System.out.println("claims.getSubject() = " + claims.getSubject());

    }
}
