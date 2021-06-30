package com.junsoo.shopping.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// 사용자 세부 서비스를 설정하기 위한 오버라이딩.
	@Override
	public void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	// 스프링 시큐리티의 필터 연결을 설정하기 위한 오버라이딩
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers("/favicon.ico", "/resources/**");
	}
	
	// 인터셉터로 요청을 안전하게 보호하는 방법을 설정하기 위한 오버라이딩
	@Override
	public void configure(final HttpSecurity http) throws Exception {
		
		http
			.csrf().and()
			.authorizeRequests()
			.antMatchers("/user/regist", "/user/password").anonymous()
			.antMatchers("/cart*", "/order*", "/payment*", "/user/*", "/logout").authenticated()
			.antMatchers("/product/release*").hasAuthority("ROLE_STORE")
			.antMatchers("/categories/*", "/contact/*", "/contents/*", "/products/*", "/index").permitAll()
			.anyRequest().permitAll()
			.and()
		.formLogin()
			.usernameParameter("user_id")
			.passwordParameter("password")
			.loginPage("/login")
			.loginProcessingUrl("/loginPost")
			.defaultSuccessUrl("/index")
			.failureUrl("/login?error=true")
			.and()
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login")
//			.logoutSuccessHandler(logoutSuccessHandler())
			.deleteCookies("JSESSIONID")
			.invalidateHttpSession(true);
	}
	
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}
	
	@Bean
	public AuthenticationSuccessHandler authSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		return new CustomAuthenticationProvider();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
