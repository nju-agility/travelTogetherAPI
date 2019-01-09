package com.aglie.nju.traveltogetherapi.config;

import com.aglie.nju.traveltogetherapi.util.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 生成过滤器，对"/secure/*链接下的所有资源访问进行JWT的验证
 *
 */

@Configuration
public class JwtCfg {
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/api/secure/*");
        return registrationBean;
    }
}
