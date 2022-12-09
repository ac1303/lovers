package pers.fanshuhua.lovers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.fanshuhua.lovers.Interceptor.LoginHandlerInterceptor;

/**
 * @author 小王的饭饭
 * @create 2022/11/26 16:16
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    private final LoginHandlerInterceptor loginHandlerInterceptor;

    @Autowired
    public MvcConfig(LoginHandlerInterceptor loginHandlerInterceptor) {
        this.loginHandlerInterceptor = loginHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandlerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/error/** ", "/favicon.ico", "/Login");
    }
}