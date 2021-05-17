package com.junsoo.shopping.common.dao.order;

import java.util.ArrayList;

import com.junsoo.shopping.common.vo.OrderVO;

public interface OrderDAO {

	public ArrayList<OrderVO> selectAllOrder(int seq_user_id) throws Exception;
	public OrderVO selectOrder(OrderVO orderVO) throws Exception;
	public void insertOrder(OrderVO orderVO) throws Exception;
	public void updateOrder(OrderVO orderVO) throws Exception;
	public void deleteOrder(OrderVO orderVO) throws Exception;
}
