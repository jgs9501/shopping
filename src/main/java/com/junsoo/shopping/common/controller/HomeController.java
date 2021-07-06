package com.junsoo.shopping.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.junsoo.shopping.common.service.product.ProductService;
import com.junsoo.shopping.common.service.user.UserService;
import com.junsoo.shopping.common.vo.UserVO;

/**
 * 메인페이지 호출 함수
 * @author jjsoo
 *
 */
@Controller
public class HomeController{
	
	@Inject
	private ProductService productService;
	
	@Inject
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(HttpServletRequest request) throws Exception {
		
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView redirectMain(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		UserVO userVO = new UserVO();
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		userVO = userService.selectOneUser(user_id);
		// 고객들에게 인기있는 상품
		List<HashMap<String, Object>> favorite_hashMap = new ArrayList<HashMap<String, Object>>();
		favorite_hashMap = productService.selectFavoriteProduct();
		
		// product_id, product_name, product_thumbImg, product_price, discount, reply_count, rating
		mv.addObject("favorite_list", favorite_hashMap);
		mv.addObject("userVO", userVO);
		mv.setViewName("/index");
		return mv;
	}
}
