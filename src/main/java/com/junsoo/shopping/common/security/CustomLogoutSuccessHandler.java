package com.junsoo.shopping.common.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		
		if(authentication != null && authentication.getDetails() != null) {
			request.getSession().invalidate();
		}
		System.out.println("handl logout");
		request.getSession().removeAttribute("userVO");
		response.setStatus(HttpServletResponse.SC_OK);
		response.sendRedirect("/index");
	}

}
