package com.junsoo.shopping.common.service.order;

import com.junsoo.shopping.common.vo.OrderVO;

public interface OrderService {

	public OrderVO selectOrder(OrderVO orderVO) throws Exception;
	public int insertOrder(OrderVO orderVO) throws Exception;
	public int updateOrder(OrderVO orderVO) throws Exception;
	public int deleteOrder(OrderVO orderVO) throws Exception;
}
