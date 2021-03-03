package com.wrh.failureAnalyzer;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.web.server.PortInUseException;
import org.springframework.stereotype.Component;

import java.net.BindException;

/**
 * @author wuruohong
 * @Classname MyPortInUseFailureAnalyzer
 * @Description TODO
 * @Date 2021/3/3 15:01
 */
@Component
public class MyPortInUseFailureAnalyzer extends AbstractFailureAnalyzer<PortInUseException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, PortInUseException cause) {
        return new FailureAnalysis("你启动的端口 " + cause.getPort() + " 被占用了.",
                "快检查下端口 " + cause.getPort() + " 被哪个程序占用了，或者强制杀掉进程.",
                cause);
    }
}
