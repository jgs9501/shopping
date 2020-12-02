package com.junsoo.shopping.common.controller.product;


import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		ModelAndView mv = new ModelAndView();
		MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) request;
		
		try {
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
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			mv.setViewName("contents/error");
			mv.addObject("result", "상품등록 에러");
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage());
			mv.setViewName("contents/error");
			mv.addObject("result", "상품등록 에러");
		} catch (Exception e) {
			logger.error(e.getMessage());
			mv.setViewName("contents/error");
			mv.addObject("result", "상품등록 에러");
		}
		return mv;
	}
	
	@RequestMapping(value = "/product/categories/{category}", method = RequestMethod.GET)
	public ModelAndView getCategory(ProductVO productVO, @PathVariable("category") int category) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		try {
			
			List<ProductVO> productList = productService.selectRecentlyProduct(category);
			int productCount = productList.size();
			String categoryName = "";
			switch (category) {
				case 100 : categoryName="전기/전자제품"; break;
				case 200 : categoryName="화장품/향수"; break;
				case 300 : categoryName="식료품"; break;
				case 400 : categoryName="의류"; break;
				case 500 : categoryName="신발류"; break;
				case 600 : categoryName="악세서리"; break;
				case 700 : categoryName="사무용품"; break;
				case 800 : categoryName="주방용품"; break;
				case 900 : categoryName="응반/DVD"; break;
				case 1000 : categoryName="기타"; break;
				default: categoryName="error"; break;
			}
			
			if(productCount < 1) {
				mv.setViewName("contents/product/categories");
				mv.addObject("result", "해당 카테고리에 검색된 상품이 없습니다");
				mv.addObject("categoryName", categoryName);
			} else {
				mv.setViewName("contents/product/categories");
				mv.addObject("result", productCount);
				mv.addObject("productVO", productList);
				mv.addObject("categoryName", categoryName);
			}
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			mv.setViewName("contents/error");
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage());
			mv.setViewName("contents/error");
		} catch (Exception e) {
			logger.error(e.getMessage());
			mv.setViewName("contents/error");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/product/{product_id}", method = RequestMethod.GET)
	public ModelAndView getProductDetail(ProductVO productVO, @PathVariable("product_id") int product_id) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		return null;
	}
}
