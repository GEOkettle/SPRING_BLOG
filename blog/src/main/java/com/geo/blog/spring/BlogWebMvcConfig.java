	package com.geo.blog.spring;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableConfigurationProperties
@EnableWebMvc
public class BlogWebMvcConfig implements WebMvcConfigurer{


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("**/").addResourceLocations("classpath:/public/");
		System.out.println();

	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/html/");
	    registry.viewResolver(internalResourceViewResolver);
	}


	// configureViewResolvers() = @EnableWebMvc에 의한 세팅 값 + 사용자에 의한 추가 세팅
	 @Bean
	     ViewResolver jspViewResolver() {
	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	        resolver.setPrefix("/WEB-INF/views/"); // Set the prefix for the JSP files
	        resolver.setSuffix(".jsp"); // Set the suffix for the JSP files
	        resolver.setViewClass(JstlView.class); // Set the view class to JSTL
	        return resolver;
	    }


}


