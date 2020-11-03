package com.junsoo.shopping.common.service.modify;

import javax.servlet.http.HttpServletRequest;

import com.junsoo.shopping.common.vo.UserVO;

public interface ModifyService {

	public UserVO selectOneUser(String userId) throws Exception;
	public void updateUser(UserVO userVO) throws Exception;
	public void updatePassword(UserVO userVO, HttpServletRequest request) throws Exception;
	
}
