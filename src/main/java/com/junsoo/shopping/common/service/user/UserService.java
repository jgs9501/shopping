package com.junsoo.shopping.common.service.user;

import javax.servlet.http.HttpServletRequest;

import com.junsoo.shopping.common.vo.UserVO;

public interface UserService {

	public String selectPassword(String userId) throws Exception;
	public UserVO selectOneUser(UserVO vo) throws Exception;
	public UserVO selectOneUser(String userId) throws Exception;
	public void updateUser(UserVO userVO) throws Exception;
	public void updatePassword(UserVO userVO, HttpServletRequest request) throws Exception;
	public void insertUser(UserVO vo, HttpServletRequest request) throws Exception;
	public int selectCheckId(String user_id) throws Exception;
	public void signOut(UserVO userVO) throws Exception;
}
