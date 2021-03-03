package com.wrh.failureAnalyzer;

import com.wrh.exception.ExceptionA;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.web.server.PortInUseException;
import org.springframework.stereotype.Component;

/**
 * @author wuruohong
 * @Classname MyPortInUseFailureAnalyzer
 * @Description TODO
 * @Date 2021/3/3 15:01
 */
@Component
public class MyJavaStackExceptionAnalyzer extends AbstractFailureAnalyzer<ExceptionA> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, ExceptionA cause) {
        return new FailureAnalysis("ExceptionA发生异常了……",
                "赶快去检查一下吧！",
                cause);
    }
}
