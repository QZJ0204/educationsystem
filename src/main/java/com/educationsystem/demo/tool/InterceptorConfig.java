package com.educationsystem.demo.tool;

//拦截器的配置
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor()) //配置拦截器的规则
                .addPathPatterns("/**")//拦截所有的请求路径
//                方式一
                .excludePathPatterns("/user/login","/user/register");//不拦截登录界面
//                .excludePathPatterns("/register");
        super.addInterceptors(registry);
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

}