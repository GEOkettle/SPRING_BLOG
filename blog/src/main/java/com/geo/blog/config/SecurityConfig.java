package com.geo.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration //bean에 configure라고 등록
@EnableWebSecurity // security 설정을 여기서 하게따
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하게따.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable();//이거안하면 403떨어짐
		http
			.authorizeHttpRequests()
				.antMatchers("/auth/**"//auth이하로 떨어지는 요청은
							,"/"
							,"/index.html"
							,"/html/main/index.html"
							,"/html/main/signIn.html"
							,"/html/main/signUp.html")
				.permitAll()// 인증안해도 접근가능
				.anyRequest()//위에 빼고 모든요청은
				.authenticated()// 인증되야 접근가능
			.and()
				.formLogin()
				.loginPage("/html/main/signIn.html")
				.loginProcessingUrl("/auth/user/signIn")
				.usernameParameter("userId")
				.passwordParameter("password");
	}

}
