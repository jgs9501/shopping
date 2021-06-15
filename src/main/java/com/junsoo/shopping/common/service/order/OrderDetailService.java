package com.junsoo.shopping.common.service.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.junsoo.shopping.common.vo.OrderDetailVO;
import com.junsoo.shopping.common.vo.OrderVO;

public interface OrderDetailService {
	public List<OrderDetailVO> selectAllOrderDetail(int seq_user_id) throws Exception;
	public ArrayList<OrderDetailVO> selectOrderDetail(OrderVO orderVO) throws Exception;
	public ArrayList<OrderDetailVO> selectOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	public int insertOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	public int updateOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	public int deleteOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	public int deleteOrderDetail(HashMap<String, Object> map) throws Exception;
}
