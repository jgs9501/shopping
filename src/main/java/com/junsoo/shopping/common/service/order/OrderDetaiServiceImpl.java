package com.junsoo.shopping.common.service.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoo.shopping.common.dao.order.OrderDetailDAO;
import com.junsoo.shopping.common.vo.OrderDetailVO;
import com.junsoo.shopping.common.vo.OrderVO;

@Service
@Transactional
public class OrderDetaiServiceImpl implements OrderDetailService {

	private static final Logger logger = LoggerFactory.getLogger(OrderDetailService.class);
			
	@Inject
	OrderDetailDAO orderDetailDAO;
	
	@Inject
	OrderService orderService;
	
	@Override
	public List<OrderDetailVO> selectAllOrderDetail(int seq_user_id) throws Exception {
		
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		try {
			if(seq_user_id < 1) {
				return null;
			}
			list = orderDetailDAO.selectAllOrderDetail(seq_user_id);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		return list;
	}
	
	@Override
	public ArrayList<OrderDetailVO> selectOrderDetail(OrderVO orderVO) throws Exception {
		
		ArrayList<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		try {
			if(orderVO.getSeq_user_id() < 1) {
					logger.error("selectOrderDetail method error : seq_user_id does not exist.");
					return null;
			}
			if(orderVO.getOrder_id() == "") {
				logger.error("selectOrderDetail method error : order_id does not exist.");
				return null;
			}
			list = (ArrayList<OrderDetailVO>) orderDetailDAO.selectOrderDetail(orderVO);
			
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		
		return list;
	}
	
	@Override
	public ArrayList<OrderDetailVO> selectOrderDetail(OrderDetailVO orderDetailVO) throws Exception {

		ArrayList<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		try {
			
			for(OrderDetailVO vo : orderDetailVO.getOrderDetailsVO()) {
				
				if(vo.getSeq_user_id() < 1) {
					logger.error("selectOrderDetail method error : seq_user_id does not exist.");
					return null;
				}
				if(vo.getOrder_id() == "") {
					logger.error("selectOrderDetail method error : order_id does not exist.");
					return null;
				}
				list = (ArrayList<OrderDetailVO>) orderDetailDAO.selectOrderDetail(orderDetailVO.getOrderDetailsVO().get(0));
			}
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		
		return list;
	}

	@Override
	public int insertOrderDetail(OrderDetailVO orderDetailVO) throws Exception {
		
		try {
			for(OrderDetailVO vo : orderDetailVO.getOrderDetailsVO()) {
				if(vo.getSeq_user_id() < 1) {
					logger.error("insertOrderDetail method error : seq_user_id does not exist.");
					return 311;
				}
				if(vo.getOrder_id() == "") {
					logger.error("insetOrderDetail method error : order_id does not exist.");
					return 312;
				}
				if(vo.getOrder_detail_id() == "") {
					logger.error("insertOrderDetail method error : order_detail_id does not exist.");
					return 313;
				}
				orderDetailDAO.insertOrderDetail(vo);
			}
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return -1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -2;
		}
		return 1;
	}

	@Override
	public int updateOrderDetail(OrderDetailVO orderDetailVO) throws Exception {
		try {
			if(orderDetailVO.getSeq_user_id() < 1) {
				logger.error("updateOrderDetail method error : seq_user_id does not exist.");
				return 311;
			}
			if(orderDetailVO.getOrder_id() == "") {
				logger.error("updateOrderDetail method error : order_id does not exist.");
				return 312;
			}
			orderDetailDAO.updateOrderDetail(orderDetailVO);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return -1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -2;
		}
		
		return 1;
	}

	@Override
	public int deleteOrderDetail(OrderDetailVO orderDetailVO) throws Exception {
		try {
			if(orderDetailVO.getSeq_user_id() < 1) {
				logger.error("deleteOrderDetail method error : seq_user_id does not exist.");
				return 311;
			}
			if(orderDetailVO.getOrder_id() == "") {
				logger.error("deleteOrderDetail method error : order_id does not exist.");
				return 312;
			}
			
			orderDetailDAO.deleteOrderDetail(orderDetailVO);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return -1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -2;
		}
		
		return 1;
	}

	@Override
	public int deleteOrderDetail(HashMap<String, Object> map) throws Exception {
		
		int seq_user_id = Integer.parseInt(String.valueOf(map.get("seq_user_id")));
		String order_id = String.valueOf(map.get("order_id"));
		String order_detail_id = String.valueOf(map.get("order_detail_id"));
		
		try {
			if(seq_user_id < 1) {
				logger.error("deleteOrderDetail method error : seq_user_id does not exist.");
				return 311;
			}
			if(order_id == "") {
				logger.error("deleteOrderDetail method error : order_id does not exist.");
				return 312;
			}
			if(order_detail_id == "") {
				logger.error("deleteOrderDetail method error : order_detail_id does not exist.");
				return 313;
			}
			logger.info("Order Cancellation History " + 
						"[seq_user_id: "+map.get("seq_user_id") + 
						" order_id: "+map.get("order_id")+ 
						" order_detail_id: " + map.get("order_detail_id") + "]");
			orderDetailDAO.deleteOrderDetail(map);
			
			if(orderDetailDAO.selectCntOrderDetail(map) < 1) {
				logger.info("Delete the order because it does not exist. "
						+ "[seq_user_id: "+map.get("seq_user_id") + " order_id: " + map.get("order_id"));
				orderService.deleteOrder(map);
			}
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			return -1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -2;
		}
		
		return 1;
	}




}
