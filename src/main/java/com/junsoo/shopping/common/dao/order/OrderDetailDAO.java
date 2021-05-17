package com.junsoo.shopping.common.dao.order;

import java.util.List;

import com.junsoo.shopping.common.vo.OrderDetailVO;

public interface OrderDetailDAO {

	public List<OrderDetailVO> selectOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	public void insertOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	public void updateOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	public void deleteOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
}
