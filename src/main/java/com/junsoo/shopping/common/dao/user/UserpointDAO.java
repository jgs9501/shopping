package com.junsoo.shopping.common.dao.user;

import java.util.HashMap;

import com.junsoo.shopping.common.vo.UserVO;

public interface UserpointDAO {
	
	public int selectCountUserId(String user_id) throws Exception;
	public int selectUserPoint(String user_id) throws Exception;
	public void insertUserPoint(UserVO userVO) throws Exception;
	public int updateUserPoint(HashMap<String, Object> userPointMap) throws Exception;
	public int deleteUserPoint(String user_id) throws Exception;
}
