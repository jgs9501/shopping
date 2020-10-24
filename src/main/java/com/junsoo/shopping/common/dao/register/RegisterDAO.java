package com.junsoo.shopping.common.dao.register;

import com.junsoo.shopping.common.vo.UserVO;

public interface RegisterDAO {
	
	public void insertUser(UserVO vo) throws Exception;
	public int selectCheckId(String user_id) throws Exception;
}
