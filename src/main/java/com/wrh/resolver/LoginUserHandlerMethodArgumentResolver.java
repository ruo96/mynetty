package com.wrh.resolver;

import com.wrh.annotate.annotation.Passport;
import com.wrh.elasticsearch.Student;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author wuruohong
 * @Classname LoginUserHandlerMethodArgumentResolver
 * @Description TODO
 * @Date 2021/3/2 15:33
 */
//@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(Student.class)&&methodParameter.hasParameterAnnotation(Passport.class);
    }

    /** supportsParameter 返回为true才会进入本方法  本方法可以将request中的attribute中的参数值取出，然后放入controller的带指定注解的对象参数中*/
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        //获取登陆用户信息
        Object object = nativeWebRequest.getAttribute("user_info", RequestAttributes.SCOPE_REQUEST);
        if(object == null){
            return null;
        }
        return (Student)object;
    }
}
