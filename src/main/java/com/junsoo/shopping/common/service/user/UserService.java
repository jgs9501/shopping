package com.junsoo.shopping.common.service.user;

import com.junsoo.shopping.common.vo.UserVO;

public interface UserService {

	public void signOut(UserVO userVO) throws Exception;
	public UserVO selectOneUser(String user) throws Exception;
}
