package com.junsoo.shopping.common.dao.user;

import com.junsoo.shopping.common.vo.UserVO;

public interface UserDAO {

	public void signOut(UserVO userVO) throws Exception;
	public UserVO selectOneUser(String userId) throws Exception;
	
}
