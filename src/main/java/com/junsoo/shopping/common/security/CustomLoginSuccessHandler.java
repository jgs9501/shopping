package com.junsoo.shopping.common.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		List<String> roleNames = new ArrayList<>();
		authentication.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		
		Enumeration<String> list = request.getSession().getAttributeNames();
		while(list.hasMoreElements()) {
			System.out.println(list.nextElement());
		}
		if(roleNames.contains("ROLE_USER")) {
			response.sendRedirect("/index");
		}
		else if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/index");
		}
		else if(roleNames.contains("ROLE_STORE")) {
			response.sendRedirect("/");
		}
	}

}
