package com.wrh;


import com.wrh.server.EchoServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class MynettyApplication {

	@Autowired
	private EchoServer echoServer;


	public static void main(String[] args) {
		SpringApplication.run(MynettyApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(){

        log.info("开始启动服务端的服务!");

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
