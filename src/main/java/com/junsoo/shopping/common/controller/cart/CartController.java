package com.junsoo.shopping.common.controller.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.junsoo.shopping.common.service.cart.CartService;
import com.junsoo.shopping.common.service.user.UserService;
import com.junsoo.shopping.common.vo.CartVO;
import com.junsoo.shopping.common.vo.UserVO;

@Controller
public class CartController {
	
	@Inject
	private UserService userService;
	
	@Inject
	private CartService cartService;
	
	// 장바구니 화면 호출 메소드
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView getCart(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		UserVO userVO = new UserVO();
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		userVO = userService.selectOneUser(user_id);
		int seq_user_id = userVO.getSeq_user_id();
		
		List<HashMap<String, Object>> cartList = new ArrayList<>();
		cartList = cartService.selectAllCart(seq_user_id);
		mv.setViewName("contents/cart");
		mv.addObject("cartList", cartList);
		return mv;
	}
	
	// 장바구니 추가 메소드
	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public String insertCart(HttpServletRequest request, 
							 @RequestParam("product_id") int product_id,
							 @RequestParam("amount") int amount) throws Exception {
		
		CartVO cartVO = new CartVO();
		UserVO userVO = new UserVO();
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		userVO = userService.selectOneUser(user_id);
		System.out.println(userVO);
		int seq_user_id = userVO.getSeq_user_id();
		
		cartVO.setSeq_user_id(seq_user_id);
		cartVO.setProduct_id(product_id);
		cartVO.setAmount(amount);
		cartService.insertCart(cartVO);
		
		return "redirect:/products/"+product_id;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ajaxCartAmountUpdate", method = RequestMethod.POST)
	public int updateCartAmount(@RequestBody CartVO cartVO) throws Exception {
		
		return cartService.updateCartAmount(cartVO);
	}
	
	// 장바구니에서 해당 품목 삭제
	@RequestMapping(value = "/cart/{seq_user_id}/delete/{cart_id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteCart(@PathVariable("seq_user_id") int seq_user_id,
							 @PathVariable("cart_id") int cart_id) throws Exception {
		
		CartVO cartVO = new CartVO();
		cartVO.setCart_id(cart_id);
		cartVO.setSeq_user_id(seq_user_id);
		
		cartService.deleteCart(cartVO);
		return "redirect:/cart";
	}
}
