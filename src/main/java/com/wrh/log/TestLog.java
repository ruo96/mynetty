package com.wrh.log;

import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestLog
 * @Description TODO
 * @Date 2022/1/12 11:53
 */
public class TestLog {

    @Test
    public void Test12() {

        /**
         * 要配置logback-spring.xml，springboot会默认加载此文件，为什么不配置logback.xml,因为logback.xml会先application.properties加载，
         * 而logback-spring.xml会后于application.properties加载，这样我们在application.properties文中设置日志文件名称和文件路径才能生效
         */

        /**
         * <?xml version="1.0" encoding="UTF-8"?>
         * <configuration  scan="true" scanPeriod="60 seconds" debug="false">
         *  <contextName>logback</contextName>
         *  <!--输出到控制台-->
         *  <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
         *   <encoder>
         *    <!--<pattern>%d{HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n</pattern>-->
         *    <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger - %msg%n</pattern>
         * <!--            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} -%5p ${PID:-} [%15.15t] %-30.30C{1.} : %m%n</pattern>-->
         *   </encoder>
         *  </appender>
         *
         *  <!--按天生成日志-->
         *  <appender name="logFile"  class="ch.qos.logback.core.rolling.RollingFileAppender">
         *     <Prudent>true</Prudent>
         *     <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
         *       <FileNamePattern>
         *      poslog/%d{yyyy-MM-dd}/%d{yyyy-MM-dd}.log
         *       </FileNamePattern>
         *       <maxHistory>7</maxHistory>
         *     </rollingPolicy>
         *     <layout class="ch.qos.logback.classic.PatternLayout">
         *       <Pattern>
         *        %d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger - %msg%n
         *       </Pattern>
         *     </layout>
         *  </appender>
         *
         *  <root level="INFO">
         *   <appender-ref ref="console" />
         *   <appender-ref ref="logFile" />
         *  </root>
         *
         * </configuration>
         */

        /**
         * 上述配置的编码中，对应符号的含义如下
         *
         * %d{HH:mm:ss.SSS}——日志输出时间
         * %thread——输出日志的进程名字，这在Web应用以及异步任务处理中很有用
         * %-5level——日志级别，并且使用5个字符靠左对齐
         * %logger ——日志输出者的名字
         * %msg——日志消息
         * %n——平台的换行符
         */
    }

    @Test
    public void Test71() {
        /** 日志配置到d盘了 */
        /**
         * <?xml version="1.0" encoding="UTF-8"?>
         * <configuration>
         *  <!-- 日志存放路径 -->
         *  <property name="log.path" value="d:/logback" />
         *  <!-- 日志输出格式 -->
         *  <property name="log.pattern" value="%d{HH:mm:ss.SSS} [%thread] %-5level %logger{20} - [%method,%line] - %msg%n" />
         *
         *  <!-- 控制台输出 -->
         *  <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
         *   <encoder>
         *    <pattern>${log.pattern}</pattern>
         *   </encoder>
         *  </appender>
         *
         *  <!-- 系统日志输出 -->
         *  <appender name="file_info" class="ch.qos.logback.core.rolling.RollingFileAppender">
         *   <file>${log.path}/sys-info.log</file>
         *   <!-- 循环政策：基于时间创建日志文件 -->
         *   <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
         *    <!-- 日志文件名格式 -->
         *    <fileNamePattern>${log.path}/sys-info.%d{yyyy-MM-dd}.log</fileNamePattern>
         *    <!-- 日志最大的历史 60天 -->
         *    <maxHistory>60</maxHistory>
         *   </rollingPolicy>
         *   <encoder>
         *    <pattern>${log.pattern}</pattern>
         *   </encoder>
         *   <filter class="ch.qos.logback.classic.filter.LevelFilter">
         *    <!-- 过滤的级别 只会打印debug不会有info日志-->
         * <!--            <level>DEBUG</level>-->
         *    <!-- 匹配时的操作：接收（记录） -->
         *    <onMatch>ACCEPT</onMatch>
         *    <!-- 不匹配时的操作：拒绝（不记录） -->
         *    <onMismatch>DENY</onMismatch>
         *   </filter>
         *  </appender>
         *
         *  <appender name="file_error" class="ch.qos.logback.core.rolling.RollingFileAppender">
         *   <file>${log.path}/sys-error.log</file>
         *   <!-- 循环政策：基于时间创建日志文件 -->
         *   <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
         *    <!-- 日志文件名格式 -->
         *    <fileNamePattern>${log.path}/sys-error.%d{yyyy-MM-dd}.log</fileNamePattern>
         *    <!-- 日志最大的历史 60天 -->
         *    <maxHistory>60</maxHistory>
         *   </rollingPolicy>
         *   <encoder>
         *    <pattern>${log.pattern}</pattern>
         *   </encoder>
         *   <filter class="ch.qos.logback.classic.filter.LevelFilter">
         *    <!-- 过滤的级别 -->
         *    <level>ERROR</level>
         *    <!-- 匹配时的操作：接收（记录） -->
         *    <onMatch>ACCEPT</onMatch>
         *    <!-- 不匹配时的操作：拒绝（不记录） -->
         *    <onMismatch>DENY</onMismatch>
         *   </filter>
         *  </appender>
         *
         *  <!-- 用户访问日志输出  -->
         *  <appender name="sys-user" class="ch.qos.logback.core.rolling.RollingFileAppender">
         *   <file>${log.path}/sys-user.log</file>
         *   <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
         *    <!-- 按天回滚 daily -->
         *    <fileNamePattern>${log.path}/sys-user.%d{yyyy-MM-dd}.log</fileNamePattern>
         *    <!-- 日志最大的历史 60天 -->
         *    <maxHistory>60</maxHistory>
         *   </rollingPolicy>
         *   <encoder>
         *    <pattern>${log.pattern}</pattern>
         *   </encoder>
         *  </appender>
         *
         *  <!-- 系统模块日志级别控制  -->
         *  <logger name="com.example" level="debug" />
         *  <!-- Spring日志级别控制  -->
         *  <logger name="org.springframework" level="warn" />
         *
         *  <root level="info">
         *   <appender-ref ref="console" />
         *  </root>
         *
         *  <!--系统操作日志-->
         *  <root level="info">
         *   <appender-ref ref="file_info" />
         *   <appender-ref ref="file_error" />
         *  </root>
         *
         *  <!--系统用户操作日志-->
         *  <logger name="sys-user" level="info">
         *   <appender-ref ref="sys-user"/>
         *  </logger>
         * </configuration>
         */

        /**
         * 还可以在application.yml中配置
         *
         * # 日志配置
         * logging:
         *   level:
         *     com.example: info
         *     org.springframework: warn
         */


    }
}
