package com.junsoo.shopping.common.controller.product;


import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.junsoo.shopping.common.dao.product.ProductDAO;
import com.junsoo.shopping.common.dao.reply.ReplyDAO;
import com.junsoo.shopping.common.service.product.ProductService;
import com.junsoo.shopping.common.service.reply.ReplyService;
import com.junsoo.shopping.common.vo.ProductDetailVO;
import com.junsoo.shopping.common.vo.ProductReplyVO;
import com.junsoo.shopping.common.vo.ProductVO;
import com.junsoo.shopping.common.vo.UserVO;
import com.junsoo.shopping.common.vo.paging.PaginationInfo;
import com.junsoo.shopping.utils.checker.ValueChecker;

@Controller
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Inject
	ProductService productService;
	@Inject
	ProductDAO productDAO;
	
	@Inject
	ReplyDAO replyDAO;
	@Inject
	ReplyService replyService;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	/**
	 * header.jsp에서 사용
	 * auth=2 일 경우 상품등록 이동 메소드
	 * @param session
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "product/release", method = RequestMethod.GET)
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
	
	/**
	 * release.jsp
	 * 상품등록처리 메소드
	 * @param productVO
	 * @param request
	 * @param uploadFile
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "product/release", method = RequestMethod.POST)
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
			productVO.setSale(multiReq.getParameter("sale"));
			productVO.setDiscount(Integer.parseInt(multiReq.getParameter("discount")));
			productVO.setProduct_desc(multiReq.getParameter("weight"));
			productVO.setAttention(multiReq.getParameter("attention"));
			productVO.setValid_date(multiReq.getParameter("valid_date"));
			productVO.setUse_info(multiReq.getParameter("use_info"));
			productVO.setCountry(multiReq.getParameter("country"));

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
	
	/**
	 * header.jsp
	 * 자신이 출품한 상품목록 조회 페이지 이동
	 * @param seq_user_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "product/release/{seq_user_id}", method = RequestMethod.GET)
	public ModelAndView getProductReleaseList(HttpSession session, 
											@PathVariable("seq_user_id") int seq_user_id) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		try {
			List<ProductVO> productList = productDAO.selectStoreProducts(seq_user_id);
			UserVO userVO = (UserVO)session.getAttribute("userVO");
			
			if(userVO.getAuth() != 2) {
				mv.setViewName("contents/error");
				mv.addObject("result", "출품용 계정이 아닙니다");
				return mv;
			}
			if(productList.size() < 1) {
				mv.setViewName("contents/product/releaseList");
				mv.addObject("listResult", "등록한 상품이 없습니다");
				return mv;
			}
			
			mv.setViewName("contents/product/releaseList");
			mv.addObject("listResult", productList);
		} catch (NullPointerException npe) {
			logger.error(npe.getLocalizedMessage());
			mv.setViewName("contents/error");
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getLocalizedMessage());
			mv.setViewName("contents/error");
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			mv.setViewName("contents/error");
		}
		return mv;
	}
	
	/**
	 * releaseModify.jsp
	 * 자신이 등록한 상품 수정처리기능
	 * @param productVO
	 * @param request
	 * @param uploadFile
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "product/release/{seq_user_id}/{product_id}", method = RequestMethod.POST)
	public ModelAndView postProductModify(HttpServletRequest request,
										 @RequestPart("uploadFile") MultipartFile uploadFile,
										 @ModelAttribute ProductVO productVO,
										 @PathVariable("seq_user_id") int seq_user_id, 
										 @PathVariable("product_id") int product_id) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		try {
			
			System.out.println(productVO.getProduct_desc());
			if(productService.updateProduct(productVO, uploadFile) == 1) {
				logger.info(productVO.toString());
				mv.setViewName("contents/complete");
				mv.addObject("result", "상품수정");
			}
			else {
				mv.setViewName("contents/error");
				mv.addObject("result", "상품수정 에러");
			}
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			mv.setViewName("contents/error");
			mv.addObject("result", "상품수정 에러");
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage());
			mv.setViewName("contents/error");
			mv.addObject("result", "상품수정 에러");
		} catch (Exception e) {
			logger.error(e.getMessage());
			mv.setViewName("contents/error");
			mv.addObject("result", "상품수정 에러");
		}
		return mv;
	}
	
	/**
	 * releaseList.jsp에서 releaseModify.jsp 호출
	 * 자신이 출품한 물품의 수정페이지로 이동하는 메소드
	 * @param seq_user_id
	 * @param product_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "product/release/{seq_user_id}/{product_id}", method = RequestMethod.GET)
	public ModelAndView getProductModify(@ModelAttribute ProductVO productVO,
										 @PathVariable("seq_user_id") int seq_user_id, 
										 @PathVariable("product_id") int product_id) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		try {
			productVO = productDAO.selectStoreProduct(productVO);
			if(productVO.equals(null)) {
				mv.addObject("result", "검색된 출품 상품이 없습니다");
				mv.setViewName("contents/error");
				return mv;
			}
			mv.setViewName("contents/product/releaseModify");
			mv.addObject("product", productVO);
		} catch (NullPointerException npe) {
			logger.error(npe.getLocalizedMessage());
		} 
		catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} 
		return mv;
	}
	/**
	 * categories.jsp
	 * 해당 카테고리의 상품조회 및 페이지 이동
	 * @param productVO
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "categories/{category}", method = RequestMethod.GET)
	public ModelAndView getCategory(ProductVO productVO, 
									@RequestParam(defaultValue = "1") int curPage,
									@PathVariable("category") int category) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		ValueChecker vc = new ValueChecker();
		try {
			// 카테고리 이름 구별
			String categoryName = vc.getCategoryName(category);
			// 카테고리 상품 개수 조회
			int count = productDAO.selectCategoryProductCount(category);
			// 최근 해당 카테고리에 등록한 상품 4개를 조회한 데이터
			List<ProductVO> recentlyProducts = productService.selectRecentlyProduct(category);
			// 페이징 정보 입력
			PaginationInfo paginationInfo = new PaginationInfo(count, curPage);
			// 해당 카테고리에 등록한 모든 상품 조회 데이터
			List<ProductVO> productList = productService.selectCategoryProducts(productVO, paginationInfo);
			
			if(recentlyProducts.size() < 1) {
				mv.addObject("recentlyProductResult", "해당 카테고리에 최근 출시된 상품이 없습니다");
			}
			if(count < 1) {
				mv.addObject("totalProductResult", "해당 카테고리에 출시된 상품이 없습니다.");
			}
			mv.setViewName("contents/product/categories");
			mv.addObject("categoryName", categoryName);
			mv.addObject("categoryProductCount",count);
			mv.addObject("recentlyProducts", recentlyProducts);
			mv.addObject("productList", productList);
			mv.addObject("pagination", paginationInfo);
			
		} catch (NullPointerException npe) {
			logger.error("NullPointerException " + npe.getLocalizedMessage());
			mv.setViewName("contents/error");
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getLocalizedMessage());
			mv.setViewName("contents/error");
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			mv.setViewName("contents/error");
		}
		
		return mv;
	}

	
	/**
	 * productDetail.jsp 
	 * 특정 상품 상세 조회
	 * @param productVO
	 * @param product_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "products/{product_id}", method = RequestMethod.GET)
	public ModelAndView getProductDetail(ProductVO productVO, 
										@RequestParam(defaultValue = "1") int curPage,
										@PathVariable("product_id") int product_id) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		ValueChecker vc = new ValueChecker();
		String categoryName = ""; 
		
		try {
			if(product_id < 1) {
				logger.error("not exist product ID: " + product_id);
				mv.addObject("result", "상품 오류");
				mv.setViewName("contents/error");
				return mv;
			}
			
			// 선택한 상품의 정보들 조회
			ProductDetailVO pdVO = productDAO.selectProductDetail(product_id);
			// 선택한 상품의 점포 관련 물품들 조회
			List<ProductVO> listProduct = productDAO.selectSameStoreProduct(pdVO.getProductVO());
			// 선택한 상품의 댓글 수 조회
			int reply_count = replyDAO.selectProductReplyCount(product_id);
			// 상품의 평가 평균 점수 조회 (소수점 첫째 자리수 계산)
			float rating_avg = (Math.round(replyService.selectProductAvgRating(product_id)*10)/10.0f);
			// 해당 상품의 댓글 정보 조회 (페이징 적용)
			PaginationInfo paginationInfo = new PaginationInfo(reply_count, curPage);
			List<ProductReplyVO> listReply = replyService.selectProductReplies(product_id, paginationInfo);
			//카테고리 이름 치환
			categoryName = vc.getCategoryName(pdVO.getProductVO().getCategory());
			
			mv.setViewName("contents/product/productDetail");
			mv.addObject("listProduct", listProduct);
			mv.addObject("replyCount", reply_count);
			mv.addObject("rating_avg", rating_avg);
			mv.addObject("listReply", listReply);
			mv.addObject("pdVO", pdVO);
			mv.addObject("categoryName", categoryName);
			mv.addObject("pagination", paginationInfo);
			
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			mv.setViewName("contents/error");
		}
		
		return mv;
	}
}