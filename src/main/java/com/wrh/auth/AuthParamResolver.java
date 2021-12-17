package com.wrh.auth;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author wuruohong
 * @Classname AuthParamResolver
 * @Description TODO
 * @Date 2021/12/17 11:21
 */
@Component
public class AuthParamResolver implements HandlerMethodArgumentResolver {

    /**
     * 使用参数解析器也需要单独配置，我们同样在 WebMvcConfigurerAdapter内配置：
     * @param param
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter param) {
        return param.getParameterType().equals(AuthParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest req, WebDataBinderFactory webDataBinderFactory) throws Exception {
        System.out.println("methodParameter = " + methodParameter.getParameter());
        System.out.println("methodParameter.getParameter().getName() = " + methodParameter.getParameter().getName());
        System.out.println("req.getNativeRequest().toString() = " + req.getNativeRequest().toString());
        System.out.println("req.getParameter(\"auth\") = " + req.getParameter("auth"));
        AuthParam param = new AuthParam();
        param.setName("wrh123");
        param.setAuth("none");
        return param;
    }
}
