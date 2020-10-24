package com.junsoo.shopping.common.dao.login;

import com.junsoo.shopping.common.vo.UserVO;

public interface LoginDAO {

	public String selectPassword(String userId) throws Exception;
	public UserVO selectOneUser(UserVO vo) throws Exception;
}
