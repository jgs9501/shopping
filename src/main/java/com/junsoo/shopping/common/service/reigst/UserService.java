package com.junsoo.shopping.common.service.reigst;

import javax.servlet.http.HttpServletRequest;

import com.junsoo.shopping.common.vo.regist.UserVO;

public interface UserService {

	public UserVO selectOneUser(int seq_user_id) throws Exception;
	public void insertUser(UserVO vo, HttpServletRequest request) throws Exception;
	public int selectCheckId(String user_id) throws Exception;
}
