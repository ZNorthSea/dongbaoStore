package com.ztj.dongbao.portal.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhaoTj
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor())
				.addPathPatterns("/user-member/update");

	}

	@Bean
	public AuthInterceptor authInterceptor(){
		return new AuthInterceptor();
	}


}
