package com.geo.blog.dto;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.geo.blog.user.service.CustomUserDetailsServiceImpl;

@Component
public class CustomUserDetailsImpl implements AuthenticationProvider{

	@Autowired
	private CustomUserDetailsServiceImpl userDetailsServiceImpl;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException{
		ServletRequestAttributes servletContainer = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request= servletContainer.getRequest();
		String id = authentication.getPrincipal().toString();
		String pw = authentication.getCredentials().toString();


		return new UsernamePasswordAuthenticationToken(null,null,null);
	}
	@Override
	public boolean supports(Class<?> authentication) {

		return authentication.equals(UsernamePasswordAuthenticationToken.class);

	}

}
