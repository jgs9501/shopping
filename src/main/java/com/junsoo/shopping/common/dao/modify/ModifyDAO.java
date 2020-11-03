package com.junsoo.shopping.common.dao.modify;

import com.junsoo.shopping.common.vo.UserVO;

public interface ModifyDAO {
	
	public UserVO selectOneUser(String userId) throws Exception;
	public void updateUser(UserVO userVO) throws Exception;
	public void updatePassword(UserVO userVO) throws Exception;
}
