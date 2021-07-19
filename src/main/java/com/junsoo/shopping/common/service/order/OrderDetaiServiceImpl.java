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
import com.junsoo.shopping.common.service.product.ProductService;
import com.junsoo.shopping.common.service.user.UserpointService;
import com.junsoo.shopping.common.vo.OrderDetailVO;
import com.junsoo.shopping.common.vo.OrderVO;
import com.junsoo.shopping.common.vo.ProductDetailVO;
import com.junsoo.shopping.utils.checker.SecurityAuthorities;

@Service
@Transactional
public class OrderDetaiServiceImpl implements OrderDetailService {

	private static final Logger logger = LoggerFactory.getLogger(OrderDetailService.class);
			
	@Inject
	private OrderDetailDAO orderDetailDAO;
	
	@Inject
	private OrderService orderService;
	
	@Inject
	private ProductService productService;
	
	@Inject
	private UserpointService userpointService;
	
	@Override
	public List<OrderDetailVO> selectAllOrderDetail(int seq_user_id) throws Exception {
		
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		try {
			if(seq_user_id < 1) {
				return null;
			}
			list = orderDetailDAO.selectAllOrderDetail(seq_user_id);
			return list;
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			npe.printStackTrace();
			throw npe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
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
			
			return list;
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			npe.printStackTrace();
			throw npe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
	}
	
	@Override
	public OrderDetailVO selectOrderDetail(OrderDetailVO orderDetailVO) throws Exception {
		
		try {
			if(orderDetailVO.getSeq_user_id() < 1) {
				logger.error("selectOrderDetail method error : seq_user_id does not exist.");
				return null;
			}
			if(orderDetailVO.getOrder_detail_id() == "") {
				logger.error("selectOrderDetail method error : order_detail_id does not exist");
				return null;
			}
			return orderDetailDAO.selectOrderDetail(orderDetailVO);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			npe.printStackTrace();
			throw npe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public ArrayList<OrderDetailVO> selectListOrderDetail(OrderDetailVO orderDetailVO) throws Exception {

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
				list = (ArrayList<OrderDetailVO>) orderDetailDAO.selectListOrderDetail(orderDetailVO.getOrderDetailsVO().get(0));
			}
			return list;
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			npe.printStackTrace();
			throw npe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public int insertOrderDetail(OrderDetailVO orderDetailVO) throws Exception {
		
		try {
			ProductDetailVO pdVO = new ProductDetailVO();
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			int result_product_cnt = 0;
			hashMap.put("product_id", 0);
			hashMap.put("product_cnt", 0);
			
			for(OrderDetailVO vo : orderDetailVO.getOrderDetailsVO()) {
				if(vo.getSeq_user_id() < 1) {
					logger.error("insertOrderDetail method error : seq_user_id does not exist.");
					return 311;
				}
				if(vo.getOrder_id() == "" || vo == null) {
					logger.error("insetOrderDetail method error : order_id does not exist.");
					return 312;
				}
				if(vo.getOrder_detail_id() == "" || vo == null) {
					logger.error("insertOrderDetail method error : order_detail_id does not exist.");
					return 313;
				}
				
				pdVO = productService.selectProductDetail(vo.getProduct_id());
				result_product_cnt = pdVO.getProductVO().getProduct_cnt() - vo.getAmount();
				if(result_product_cnt < 0) {
					logger.error("does not exist amount");
					return 400;
				}
				
				hashMap.replace("product_id", vo.getProduct_id());
				hashMap.replace("product_cnt", result_product_cnt);
				orderDetailDAO.insertOrderDetail(vo);
				productService.updateProductAmount(hashMap);
			}
			return 1;
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			npe.printStackTrace();
			throw npe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
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
			return 1;
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			npe.printStackTrace();
			throw npe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public int deleteOrderDetail(OrderDetailVO orderDetailVO) throws Exception {
		
		try {
			ProductDetailVO pdVO = new ProductDetailVO();
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			
			int result_product_cnt = 0;
			hashMap.put("product_id", 0);
			hashMap.put("product_cnt", 0);
			
			if(orderDetailVO.getSeq_user_id() < 1) {
				logger.error("deleteOrderDetail method error : seq_user_id does not exist.");
				return 311;
			}
			if(orderDetailVO.getOrder_id() == "") {
				logger.error("deleteOrderDetail method error : order_id does not exist.");
				return 312;
			}
			// 상품 개수 처리
			pdVO = productService.selectProductDetail(orderDetailVO.getProduct_id());
			result_product_cnt = pdVO.getProductVO().getProduct_cnt() + orderDetailVO.getAmount();
			
			hashMap.replace("product_id", orderDetailVO.getProduct_id());
			hashMap.replace("product_cnt", result_product_cnt);
			orderDetailDAO.deleteOrderDetail(orderDetailVO);
			productService.updateProductAmount(hashMap);
			
			return 1;
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			npe.printStackTrace();
			throw npe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public int deleteOrderDetail(HashMap<String, Object> map) throws Exception {
		
		ProductDetailVO pdVO = new ProductDetailVO();
		SecurityAuthorities securityAuthorities = new SecurityAuthorities();
		HashMap<String, Object> amount_hashMap = new HashMap<String, Object>();
		HashMap<String, Object> userpoint_hashMap = new HashMap<String, Object>();
		// 유저 포인트 조회
		String user_id = securityAuthorities.getPrincipal().getUsername();
		int total_point = userpointService.selectUserPoint(user_id);
		// view에서 받은 orderDetail(유저상세정보), product(상품) 정보들 조회
		int seq_user_id = Integer.parseInt(String.valueOf(map.get("seq_user_id")));
		int product_id = Integer.parseInt(String.valueOf(map.get("product_id")));
		int amount = Integer.parseInt(String.valueOf(map.get("amount")));
		int product_price = Integer.parseInt(String.valueOf("product_price"));
		String pay_type = String.valueOf(map.get("pay_type"));
		String order_id = String.valueOf(map.get("order_id"));
		String order_detail_id = String.valueOf(map.get("order_detail_id"));
		
		int result_product_cnt = 0;
		
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
			// 결제 내역 중 상품 개수 반환
			pdVO = productService.selectProductDetail(product_id);
			result_product_cnt = pdVO.getProductVO().getProduct_cnt() + amount;
			
			amount_hashMap.put("product_id", product_id);
			amount_hashMap.put("product_cnt", result_product_cnt);
			productService.updateProductAmount(amount_hashMap);
			
			logger.info("Order cancel history " +
						"[seq_user_id: "+map.get("seq_user_id") + 
						" order_id: "+map.get("order_id")+ 
						" order_detail_id: " + map.get("order_detail_id") + "]");
			// 특정 주문 삭제
			orderDetailDAO.deleteOrderDetail(map);
			// 상세 주문 내역이 없으면 촘합 주문내역 삭제
			if(orderDetailDAO.selectCntOrderDetail(map) < 1) {
				logger.info("Delete the order because it does not exist. "
						+ "[seq_user_id: "+map.get("seq_user_id") + " order_id: " + map.get("order_id"));
				orderService.deleteOrder(map);
			}
			// 포인트로 결제시 환불
			if(pay_type == "point") {
				
				userpoint_hashMap.put("user_id", user_id);
				userpoint_hashMap.put("point", total_point + (product_price * amount));
				userpointService.updateUserPoint(userpoint_hashMap, pay_type);
			}
			return 1;
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			npe.printStackTrace();
			throw npe;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
	}
}
