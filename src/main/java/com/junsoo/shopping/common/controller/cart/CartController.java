package com.junsoo.shopping.common.controller.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.junsoo.shopping.common.dao.cart.CartDAO;
import com.junsoo.shopping.common.service.cart.CartService;
import com.junsoo.shopping.common.vo.CartVO;
import com.junsoo.shopping.common.vo.UserVO;

@Controller
public class CartController {
	
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@Inject
	CartService cartService;
	@Inject
	CartDAO cartDAO;
	
	// 장바구니 화면 호출 메소드
	@RequestMapping(value = "cart/{seq_user_id}", method = RequestMethod.GET)
	public ModelAndView getCart(HttpServletRequest request,
								@PathVariable("seq_user_id") int seq_user_id) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		List<HashMap<String, Object>> cartList = new ArrayList<>();
		cartList = cartService.selectAllCart(seq_user_id);
		mv.setViewName("contents/cart");
		mv.addObject("cartList", cartList);
		return mv;
	}
	
	// 장바구니 추가 메소드
	@RequestMapping(value = "insertCart", method = RequestMethod.POST)
	public String insertCart(HttpServletRequest request, 
							 @RequestParam("product_id") int product_id,
							 @RequestParam("amount") int amount) throws Exception {
		
		CartVO cartVO = new CartVO();
		
		UserVO userVO = (UserVO)request.getSession().getAttribute("userVO");
		int seq_user_id = userVO.getSeq_user_id();
		
		cartVO.setSeq_user_id(seq_user_id);
		cartVO.setProduct_id(product_id);
		cartVO.setAmount(amount);
		cartService.insertCart(cartVO);
		
		return "redirect:/products/"+product_id;
	}
	
	// 장바구니에서 해당 품목 삭제
	@RequestMapping(value = "cart/{seq_user_id}/delete/{cart_id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteCart(@PathVariable("seq_user_id") int seq_user_id,
							 @PathVariable("cart_id") int cart_id) throws Exception {
		
		CartVO cartVO = new CartVO();
		cartVO.setCart_id(cart_id);
		cartVO.setSeq_user_id(seq_user_id);
		
		cartService.deleteCart(cartVO);
		return "redirect:/cart/"+seq_user_id;
	}
}
