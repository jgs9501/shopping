package com.junsoo.shopping.common.controller.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.junsoo.shopping.common.dao.cart.CartDAO;
import com.junsoo.shopping.common.service.cart.CartService;
import com.junsoo.shopping.common.service.order.OrderDetailService;
import com.junsoo.shopping.common.service.order.OrderService;
import com.junsoo.shopping.common.service.user.UserService;
import com.junsoo.shopping.common.service.user.UserpointService;
import com.junsoo.shopping.common.vo.OrderDetailVO;
import com.junsoo.shopping.common.vo.OrderVO;
import com.junsoo.shopping.common.vo.UserVO;
import com.junsoo.shopping.utils.Utils;

@Controller
public class OrderController {

	@Inject
	private UserService userService;
	
	@Inject
	private CartService cartService;
	
	@Inject
	private OrderService orderService;
	
	@Inject
	private OrderDetailService orderDetailService;
	
	@Inject
	private UserpointService userPointService;
	
	@Inject
	private CartDAO cartDAO;
	
	/**
	 * 결제 화면을 불러오는 메소드
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public ModelAndView getPayment(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		UserVO userVO = new UserVO();
		List<HashMap<String, Object>> cartList = new ArrayList<>();
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		userVO = userService.selectOneUser(user_id);
		
		int seq_user_id = userVO.getSeq_user_id();
		int point = userPointService.selectUserPoint(userVO.getUser_id());
		cartList = cartService.selectAllCart(seq_user_id);
		
		mv.setViewName("/contents/payment/payment");
		mv.addObject("userVO", userVO);
		mv.addObject("cartList", cartList);
		mv.addObject("point", point);
		
		return mv;
	}
	
	/**
	 * 포인트로 결제할 경우 불러오는 메소드
	 * @param request
	 * @param usePoint
	 * @param totalPrice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/payment/point", method = RequestMethod.POST)
	public ModelAndView postPointPayment(HttpServletRequest request,
										 @RequestParam int usePoint,
										 @RequestParam int totalPrice,
										 @ModelAttribute OrderVO orderVO,
										 @ModelAttribute OrderDetailVO orderDetailsVO) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		UserVO userVO = new UserVO();
		Utils utils = new Utils();
		// 세션으로 유저정보 취득
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		userVO = userService.selectOneUser(user_id);
		int seq_user_id = userVO.getSeq_user_id();
		
		// 유저 포인트 정보를 HashMap으로 세팅
		HashMap<String, Object> userPointMap = new HashMap<String, Object>();
		userPointMap.put("user_id", user_id);
		userPointMap.put("point", usePoint);
		userPointMap.put("totalPrice", totalPrice);
		
		// 포인트로 결제 시, 유저 포인트 수정
		int flag = userPointService.updateUserPoint(userPointMap, orderVO.getPay_type());
		// 보유포인트가 충족될 경우 true
		if(flag == 1) {
			// 주문정보 데이터
			orderVO.setSeq_user_id(seq_user_id);
			String orderId = utils.getOrderId();
			orderVO.setOrder_id(orderId);
			orderVO.setTotal_price(totalPrice);
			
			// 상세주문정보 데이터 입력
			for(OrderDetailVO vo: orderDetailsVO.getOrderDetailsVO()) {
				vo.setOrder_id(orderId);
				vo.setSeq_user_id(seq_user_id);
				vo.setOrder_detail_id(utils.getOrderDetailId());
			}
			// 주문,주문상세 입력처리
			orderService.insertOrder(orderVO);
			orderDetailService.insertOrderDetail(orderDetailsVO);
			// 장바구니 삭제
			cartDAO.deleteAllCart(seq_user_id);
			// 입력된 주문,주문상세 검색
			orderVO = orderService.selectOrder(orderVO);
			ArrayList<OrderDetailVO> orderDetailList = orderDetailService.selectOrderDetail(orderDetailsVO);

			mv.addObject("order", orderVO);
			mv.addObject("orderDetailList", orderDetailList);
			mv.setViewName("contents/payment/completePayment");
		}
		else if(flag == -2){
			mv.setViewName("contents/error");
			mv.addObject("result", "잔여 포인트가 부족합니다.");
		}
		else {
			mv.setViewName("contents/error");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/payment/kakaopay", method = RequestMethod.POST)
	public ModelAndView postKakaoPayment(HttpServletRequest request,
										 @RequestParam int usePoint,
										 @RequestParam int totalPrice,
										 @ModelAttribute OrderVO orderVO,
										 @ModelAttribute OrderDetailVO orderDetailsVO) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		UserVO userVO = new UserVO();
		Utils utils = new Utils();
		// 세션으로 유저정보 취득
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		userVO = userService.selectOneUser(user_id);
		int seq_user_id = userVO.getSeq_user_id();
		
		// 유저 포인트 정보를 HashMap으로 세팅
		HashMap<String, Object> userPointMap = new HashMap<String, Object>();
		userPointMap.put("user_id", user_id);
		userPointMap.put("point", usePoint);
		userPointMap.put("totalPrice", totalPrice);
		
		// 포인트로 결제 시, 유저 포인트 수정
		int flag = userPointService.updateUserPoint(userPointMap, orderVO.getPay_type());
		// 보유포인트가 충족될 경우 true
		if(flag == 1) {
			// 주문정보 데이터
			orderVO.setSeq_user_id(seq_user_id);
			String orderId = utils.getOrderId();
			orderVO.setOrder_id(orderId);
			orderVO.setTotal_price(totalPrice);
			
			// 상세주문정보 데이터 입력
			for(OrderDetailVO vo: orderDetailsVO.getOrderDetailsVO()) {
				vo.setOrder_id(orderId);
				vo.setSeq_user_id(seq_user_id);
				vo.setOrder_detail_id(utils.getOrderDetailId());
			}
			// 주문,주문상세 입력처리
			orderService.insertOrder(orderVO);
			orderDetailService.insertOrderDetail(orderDetailsVO);
			// 장바구니 삭제
			cartDAO.deleteAllCart(seq_user_id);
			// 입력된 주문,주문상세 검색
			orderVO = orderService.selectOrder(orderVO);
			ArrayList<OrderDetailVO> orderDetailList = orderDetailService.selectOrderDetail(orderDetailsVO);

			mv.addObject("order", orderVO);
			mv.addObject("orderDetailList", orderDetailList);
			mv.setViewName("/contents/payment/completePayment");
		}
		else if(flag == -2){
			mv.setViewName("/contents/error");
			mv.addObject("result", "잔여 포인트가 부족합니다.");
		}
		else {
			mv.setViewName("/contents/error");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public ModelAndView getOrderHistory(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		UserVO userVO = new UserVO();
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		userVO = userService.selectOneUser(user_id);
		
		List<OrderVO> order_list = new ArrayList<OrderVO>();
		List<OrderDetailVO> orderDetail_list = new ArrayList<OrderDetailVO>();
			
		order_list = orderService.selectAllOrder(userVO.getSeq_user_id());
		orderDetail_list = orderDetailService.selectAllOrderDetail(userVO.getSeq_user_id());
		mv.addObject("orders", order_list);
		mv.addObject("details", orderDetail_list);
		mv.setViewName("/contents/user/order_history");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/order/cancel", method = RequestMethod.POST)
	public HashMap<String, Object> postOrderHistory(HttpServletRequest request,
										 @RequestBody HashMap<String, Object> map) throws Exception {
		
		orderDetailService.deleteOrderDetail(map);
		orderService.updateOrderTotalPrice(map);
		return map;
	}
	
}
