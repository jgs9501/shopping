package com.junsoo.shopping.common.dao.cart;

import java.util.HashMap;
import java.util.List;

import com.junsoo.shopping.common.vo.CartVO;

public interface CartDAO {

	public void insertCart(CartVO cartVO) throws Exception;
	public List<HashMap<String, Object>> selectAllCart(int seq_user_id) throws Exception;
	public void deleteCart(CartVO cartVO) throws Exception;
}
