package com.junsoo.shopping.common.dao.user;

import com.junsoo.shopping.common.vo.UserVO;

public interface UserDAO {

	public String selectPassword(String userId) throws Exception;
	public UserVO selectOneUser(UserVO vo) throws Exception;
	public UserVO selectOneUser(String userId) throws Exception;
	public void updateUser(UserVO userVO) throws Exception;
	public void updatePassword(UserVO userVO) throws Exception;
	public void insertUser(UserVO vo) throws Exception;
	public int selectCheckId(String user_id) throws Exception;
	public void signOut(UserVO userVO) throws Exception;
	
}
