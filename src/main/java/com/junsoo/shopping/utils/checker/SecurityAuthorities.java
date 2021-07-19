package com.junsoo.shopping.utils.checker;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * Spring Security 권한정보 유틸
 * @author jjsoo
 *
 */
public class SecurityAuthorities {

	private static final String USER = "ROLE_USER";
	private static final String STORE = "ROLE_STORE";
	private static final String ADMIN = "ROLE_ADMIN";
	
	/**
	 * 현재 로그인 된 계정이 고객(유저)인지 판단
	 * @return
	 */
	public boolean hasRoleUser() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		return authorities.stream().filter(o -> o.getAuthority().equals(USER)).findAny().isPresent();
	}
	
	/**
	 * 현재 로그인 된 계정이 점포인지 판단
	 * @return
	 */
	public boolean hasRoleStore() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		return authorities.stream().filter(o -> o.getAuthority().equals(STORE)).findAny().isPresent();
	}

	/**
	 * 현재 로그인 된 계정이 관리자인지 판단
	 * @return
	 */
	public boolean hasRoleAdmin() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		return authorities.stream().filter(o -> o.getAuthority().equals(ADMIN)).findAny().isPresent();
	}
	
	/**
	 * 현재 로그인 된 계정 정보 추출
	 * @return
	 */
	public User getPrincipal() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (User)authentication.getPrincipal();
	}
	
	/**
	 * 현재 로그인 된 계정 정보 추출
	 * @return
	 */
	public String getUsername() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
