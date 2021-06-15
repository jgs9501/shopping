package com.junsoo.shopping.common.dao.order;

import java.util.HashMap;
import java.util.List;

import com.junsoo.shopping.common.vo.OrderVO;

public interface OrderDAO {

	public List<OrderVO> selectAllOrder(int seq_user_id) throws Exception;
	public OrderVO selectOrder(OrderVO orderVO) throws Exception;
	public void insertOrder(OrderVO orderVO) throws Exception;
	public void updateOrderStatus(OrderVO orderVO) throws Exception;
	public void updateOrderTotalPrice(HashMap<String, Object> map) throws Exception;
	public void deleteOrder(OrderVO orderVO) throws Exception;
	public void deleteOrder(HashMap<String, Object> map) throws Exception;
}
