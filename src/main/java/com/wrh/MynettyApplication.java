package com.wrh;


import com.wrh.server.EchoServer;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
//@ServletComponentScan(value = {"com.wrh.filter", "com.wrh.listener"}) //用于支持过滤器、监听器注解
@ServletComponentScan( "com.wrh.listener") //用于支持过滤器、监听器注解
@SpringBootApplication
@EnableConfigurationProperties
//@ServletComponentScan("com.wrh.filter")  // 这个是配合注解实现拦截器的
public class MynettyApplication {

	@Autowired
	private EchoServer echoServer;

	@Autowired
    Environment environment;

	@Autowired
    private DataSource dataSource;


	public static void main(String[] args) {
		SpringApplication.run(MynettyApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() throws InterruptedException, SQLException {

        log.info("开始启动服务端的服务!");

        TimeUnit.SECONDS.sleep(1);

        log.info(">>> profile: {}" , Arrays.toString(environment.getActiveProfiles()));

        log.info("开始检查数据库的服务!");
        Connection conn = dataSource.getConnection();
        log.info("数据操作对象： {}", conn);
        conn.close();


        return e->{
            new Thread(() -> {
                try {
                    echoServer.active(8088);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }).start();

        };


    }


}
