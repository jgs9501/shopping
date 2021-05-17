package com.junsoo.shopping.common.service.user;

import java.util.HashMap;

import com.junsoo.shopping.common.vo.UserVO;

public interface UserpointService {

	public int selectCntUserPointId(String user_id) throws Exception;
	public int selectUserPoint(String user_id) throws Exception;
	public void insertUserPoint(UserVO userVO) throws Exception;
	public int updateUserPoint(HashMap<String, Object> userPointMap, String pay_type) throws Exception;
	public int deleteUserPoint(String user_id) throws Exception;
	
}
