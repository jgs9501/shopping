package com.junsoo.shopping.common.controller.reply;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.junsoo.shopping.common.service.reply.ReplyService;
import com.junsoo.shopping.common.vo.ProductReplyVO;

@RestController
public class ReplyController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Inject
	ReplyService replyService;
	
	@RequestMapping(value = "postProductReply", method = RequestMethod.POST)
	public ProductReplyVO insertProductReply(@RequestBody ProductReplyVO prVO) throws Exception{
		
		int flag = replyService.insertProductReply(prVO);
		if(flag != 1) {
			return null;
		}
		return replyService.selectProductReply(prVO);
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
