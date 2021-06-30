package com.junsoo.shopping.common.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityUserVO implements UserDetails {

	private static final long serialVersionUID = 174726374856727L;
	
	private String username;
	private String password;
	private String user_email;
	private boolean emailVerified;
	private boolean locked;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public SecurityUserVO(UserVO userVO) {
		this.username = userVO.getUser_id();
		this.password = userVO.getPassword();
		this.user_email = userVO.getUser_email() + "@" + userVO.getUser_domain();
		this.authorities = getAuthorities(userVO.getAuth());
	}
	
	/**
	 * 해당 유저의 권한 목록
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(int arg) {
		List<GrantedAuthority> auth = new ArrayList<>();
		String role = "";
		// 1: ROLE_USER, 2: ROLE_STORE, 3: ROLE_ADMIN
		role = (arg != 1) ? (arg == 2) ? "ROLE_STORE" : "ROLE_ADMIN" : "ROLE_USER";
		auth.add(new SimpleGrantedAuthority(role));
		return auth;
	}
	
	/**
	 * 계정 만료 여부<br>true : 만료 안됨, false : 만료
	 * @return
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	/**
	 * 계정 잠김 여부<br>true : 잠기지 않음, false : 잠김
	 * @return
	 */
	@Override
	public boolean isAccountNonLocked() {
		return locked;
	}

	/**
	 * 비밀번호 만료 여부<br>true : 만료 안됨, false : 만료
	 * @return
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 계정 만료 여부<br>true : 만료 안됨, false : 만료
	 * @return
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * 계정 활성화 여부<br>true : 활성화, false : 비활성화
	 * @return
	 */
	@Override
	public boolean isEnabled() {
		return (emailVerified && !locked);
	}


}
