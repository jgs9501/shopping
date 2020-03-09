package com.junsoo.shopping.common.dao.regist;

import com.junsoo.shopping.common.vo.regist.UserVO;

public interface UserDAO {
	
	public UserVO selectOneUser(int seq_user_id) throws Exception;
	public void insertUser(UserVO vo) throws Exception;
	public int selectCheckId(String user_id) throws Exception;
}
