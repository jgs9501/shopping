package com.junsoo.shopping.common.service.order;

import java.util.HashMap;
import java.util.List;

import com.junsoo.shopping.common.vo.OrderVO;

public interface OrderService {

	public List<OrderVO> selectAllOrder(int seq_user_id) throws Exception;
	public OrderVO selectOrder(OrderVO orderVO) throws Exception;
	public int insertOrder(OrderVO orderVO) throws Exception;
	public int updateOrderStatus(OrderVO orderVO) throws Exception;
	public int updateOrderTotalPrice(HashMap<String, Object> map) throws Exception;
	public int deleteOrder(OrderVO orderVO) throws Exception;
	public int deleteOrder(HashMap<String, Object> map) throws Exception;
}
