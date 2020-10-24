package com.junsoo.shopping.common.service.login;

import com.junsoo.shopping.common.vo.UserVO;

public interface LoginService {

	public String selectPassword(String userId) throws Exception;
	public UserVO selectOneUser(UserVO vo) throws Exception;
	
}
