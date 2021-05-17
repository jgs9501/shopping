package com.junsoo.shopping.common.service.order;

import java.util.ArrayList;

import com.junsoo.shopping.common.vo.OrderDetailVO;

public interface OrderDetailService {
	public ArrayList<OrderDetailVO> selectOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	public int insertOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	public int updateOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	public int deleteOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
}
