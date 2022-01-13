package com.wrh;


import com.wrh.listener.fourmethods.MyListener1;
import com.wrh.server.EchoServer;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.Locale;

@Slf4j
@EnableWebSocket
//@ServletComponentScan(value = {"com.wrh.filter", "com.wrh.listener"}) //用于支持过滤器、监听器注解
@ServletComponentScan(value = {"com.wrh.listener","com.wrh.servlet"}) //用于支持过滤器、监听器注解 参数就是Filter所在的包路径，相当于告诉 SpringBoot，去哪里扫描 Filter
@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
@EnableAsync
@MapperScan("com.wrh.controller.mapper")
@EnableCaching
//@EnablePrometheusEndpoint
//@EnableSpringBootMetricsCollector
//@ServletComponentScan("com.wrh.filter")  // 这个是配合注解实现拦截器的
public class MynettyApplication {

	@Autowired
	private EchoServer echoServer;

	@Autowired
    Environment environment;

	//@Autowired
    //private DataSource dataSource;

	@Autowired
    private RedisTemplate redisTemplate;

	public static ConfigurableApplicationContext ac;


	public static void main(String[] args) {
		ac = SpringApplication.run(MynettyApplication.class, args);
		ac.addApplicationListener(new MyListener1());
	}

    /*@Bean
    public CommandLineRunner commandLineRunner(){
        throw new ExceptionA("Java技术栈异常");
    }*/

	/*@Bean
	public CommandLineRunner commandLineRunner() throws InterruptedException, SQLException {

        log.info("开始启动服务端的服务!");

        TimeUnit.SECONDS.sleep(1);

        log.info(">>> profile: {}" , Arrays.toString(environment.getActiveProfiles()));

        log.info("开始检查数据库的服务!");
        Connection conn = dataSource.getConnection();
        log.info("数据操作对象： {}", conn);
        log.info("redis： {}", redisTemplate);
        conn.close();


        return e->{
            new Thread(() -> {
                try {
//                    echoServer.active(8088);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }).start();

        };


    }*/

    @Bean

    public LocaleResolver localeResolver() {

        SessionLocaleResolver slr = new SessionLocaleResolver();

        //设置默认区域,

        slr.setDefaultLocale(Locale.CHINA);

        return slr;

    }


}
