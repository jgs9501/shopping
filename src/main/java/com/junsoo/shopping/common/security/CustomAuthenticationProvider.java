package com.junsoo.shopping.common.security;

import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.junsoo.shopping.common.vo.SecurityUserVO;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Inject
	private UserDetailsService userDetailsService;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String user_id = authentication.getName();
		String password = (String)authentication.getCredentials();
		
		SecurityUserVO userDetails = (SecurityUserVO) userDetailsService.loadUserByUsername(user_id);
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("BadCredentialsException");
		}
		
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
				
		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
