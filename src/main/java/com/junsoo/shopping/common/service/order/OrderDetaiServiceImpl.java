package com.junsoo.shopping.common.service.order;

import java.util.ArrayList;

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

}
