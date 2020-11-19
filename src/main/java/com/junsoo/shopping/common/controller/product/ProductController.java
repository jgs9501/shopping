package com.junsoo.shopping.common.controller.product;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.junsoo.shopping.common.service.product.ProductService;
import com.junsoo.shopping.common.vo.ProductVO;
import com.junsoo.shopping.common.vo.UserVO;

@Controller
@RequestMapping(value = "/contents")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Inject
	ProductService productService;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@RequestMapping(value = "/product/release", method = RequestMethod.GET)
	public ModelAndView getProductRelease(HttpSession session) throws Exception {
		
		logger.info("getProductRelease method called");
		ModelAndView mv = new ModelAndView();
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		try {
			if(userVO.getAuth() == 2) {
				mv.setViewName("contents/product/release");
			}
			else {
				mv.setViewName("contents/error");
				mv.addObject("result", "잘못된 권한입니다");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			mv.setViewName("contents/error");
			mv.addObject("result", "시스템 오류가 발생하였습니다");
		}
		return mv;
	}
	
	@RequestMapping(value = "/product/release", method = RequestMethod.POST)
	public ModelAndView postProductRelease(ProductVO productVO, 
											HttpServletRequest request, 
											@RequestPart("uploadFile") MultipartFile uploadFile) throws Exception {
		
		logger.info("postProductRelease method called");
		
		ModelAndView mv = new ModelAndView();
		MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) request;
		
		try {
			System.out.println(uploadFile.getOriginalFilename());
			productVO.setSeq_user_id(Integer.parseInt(multiReq.getParameter("seq_user_id")));
			productVO.setProduct_name(multiReq.getParameter("product_name"));
			productVO.setProduct_cnt(Integer.parseInt(multiReq.getParameter("product_cnt")));
			productVO.setProduct_price(Integer.parseInt(multiReq.getParameter("product_price")));
			productVO.setProduct_desc(multiReq.getParameter("product_desc"));
			productVO.setCategory(Integer.parseInt(multiReq.getParameter("category")));
			productVO.setSale(multiReq.getParameter("sale").charAt(0));
			productVO.setDiscount(Integer.parseInt(multiReq.getParameter("discount")));

			if(productService.insertProduct(productVO, uploadFile) == 1) {
				mv.setViewName("contents/complete");
				mv.addObject("result", "상품등록");
			}
			else {
				mv.setViewName("contents/error");
				mv.addObject("result", "상품등록 에러");
			}
		} finally {}
		
		return mv;
		
	}
}
