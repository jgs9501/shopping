package com.junsoo.shopping.common.service.reigster;

import javax.servlet.http.HttpServletRequest;

import com.junsoo.shopping.common.vo.UserVO;

public interface RegisterService {

	public void insertUser(UserVO vo, HttpServletRequest request) throws Exception;
	public int selectCheckId(String user_id) throws Exception;
}
