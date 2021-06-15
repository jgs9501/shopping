package com.junsoo.shopping.common.dao.order;

import java.util.HashMap;
import java.util.List;

import com.junsoo.shopping.common.vo.OrderDetailVO;
import com.junsoo.shopping.common.vo.OrderVO;

public interface OrderDetailDAO {

	public List<OrderDetailVO> selectAllOrderDetail(int seq_user_id) throws Exception;
	public List<OrderDetailVO> selectOrderDetail(OrderVO orderVO) throws Exception;
	public List<OrderDetailVO> selectOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	public int selectCntOrderDetail(HashMap<String, Object> map) throws Exception;
	public void insertOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	public void updateOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	public void deleteOrderDetail(OrderDetailVO orderDetailVO) throws Exception;
	public void deleteOrderDetail(HashMap<String, Object> map) throws Exception;
}
