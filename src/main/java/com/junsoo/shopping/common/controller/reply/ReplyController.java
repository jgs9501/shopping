package com.junsoo.shopping.common.controller.reply;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.junsoo.shopping.common.dao.product.ProductDAO;
import com.junsoo.shopping.common.dao.reply.ReplyDAO;
import com.junsoo.shopping.common.service.reply.ReplyService;
import com.junsoo.shopping.common.vo.ProductDetailVO;
import com.junsoo.shopping.common.vo.ProductReplyVO;
import com.junsoo.shopping.common.vo.paging.PaginationInfo;

@RestController
public class ReplyController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Inject
	private ReplyService replyService;
	@Inject
	private ReplyDAO replyDAO;
	@Inject 
	private ProductDAO productDAO;
	
	@RequestMapping(value = "postProductReply", method = RequestMethod.POST)
	public ProductReplyVO insertProductReply(@RequestBody ProductReplyVO prVO) throws Exception {
		
		int flag = replyService.insertProductReply(prVO);
		if(flag != 1) {
			return null;
		}
		return replyService.selectProductReply(prVO);
	}
	
	@RequestMapping(value = "ajaxProductReply", method = RequestMethod.POST)
	public ModelAndView ajaxProductReply(@RequestBody HashMap<String, Object> param) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		int product_id = Integer.parseInt(String.valueOf(param.get("product_id")));
		int curPage = Math.round(Float.parseFloat(String.valueOf(param.get("curPage"))));
		
		ProductDetailVO pdVO = productDAO.selectProductDetail(product_id);
		// 선택한 상품의 댓글 수 조회
		int reply_count = replyDAO.selectProductReplyCount(product_id);
		// 해당 상품의 댓글 정보 조회 (페이징 적용)
		PaginationInfo paginationInfo = new PaginationInfo(reply_count, curPage);
		List<ProductReplyVO> listReply = replyService.selectProductReplies(product_id, paginationInfo);
		
		mv.addObject("listReply", listReply);
		mv.addObject("pagination", paginationInfo);
		mv.addObject("pdVO", pdVO);
		mv.setViewName("contents/product/productDetail_reply");
		return mv;
	}
	
	@RequestMapping(value = "postProductReplyAnswer", method = RequestMethod.POST)
	public ProductReplyVO updateProductReplyAnswer(@RequestBody ProductReplyVO prVO) throws Exception {
		
		try {
			int flag = replyService.updateProductReplyAnswer(prVO);
			if(flag != 1) {
				logger.error("failed answer comment " + prVO.toString());
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		return replyService.selectProductReply(prVO);
	}
	
	@RequestMapping(value = "postProductReplyAnswerDelete", method = RequestMethod.POST)
	public int deleteProductReplyAnswer(@RequestBody ProductReplyVO prVO) throws Exception {
		
		try {
			replyService.deleteProductReplyAnswer(prVO);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return 0;
		}
		return 1;
	}
}
