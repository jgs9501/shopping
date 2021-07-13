package com.junsoo.shopping.common.service.cart;

import java.util.HashMap;
import java.util.List;

import com.junsoo.shopping.common.vo.CartVO;

public interface CartService {

	public int insertCart(CartVO cartVO) throws Exception;
	public List<HashMap<String, Object>> selectAllCart(int seq_user_id) throws Exception;
	public int updateCartAmount(CartVO cartVO) throws Exception;
	public int deleteCart(CartVO cartVO) throws Exception;
}
