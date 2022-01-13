package com.wrh.config;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @author wuruohong
 * @Classname i18nconfig
 * @Description 在这段配置中，我们首先提供了一个 SessionLocaleResolver 实例，这个实例会替换掉默认的 AcceptHeaderLocaleResolver，
 * 不同于 AcceptHeaderLocaleResolver 通过请求头来判断当前的环境信息，SessionLocaleResolver 将客户端的 Locale 保存到 HttpSession 对象中，
 * 并且可以进行修改（这意味着当前环境信息，前端给浏览器发送一次即可记住，只要 session 有效，浏览器就不必再次告诉服务端当前的环境信息）。
 * 另外我们还配置了一个拦截器，这个拦截器会拦截请求中 key 为 lang 的参数（不配置的话是 locale），这个参数则指定了当前的环境信息。
 *
 * 我们通过在请求中添加 lang 来指定当前环境信息。这个指定只需要一次即可，也就是说，在 session 不变的情况下，下次请求可以不必带上 lang 参数，服务端已经知道当前的环境信息了。
 * CookieLocaleResolver 也是类似用法，不再赘述。
 * @Date 2022/1/7 18:21
 */
//@Configuration
public class i18nconfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        registry.addInterceptor(interceptor);
    }

    //@Bean
    LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }
}
