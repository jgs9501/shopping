package com.junsoo.shopping.common.controller.contact;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.junsoo.shopping.common.service.contact.QnaService;
import com.junsoo.shopping.common.vo.QnaVO;
import com.junsoo.shopping.utils.checker.SecurityAuthorities;

@RestController
public class QnaController {

	@Inject
	private QnaService qnaService;
	
	// 자주묻는질문 등록페이지 호출하는 메소드
	@RequestMapping(value = "/admin/qna", method = RequestMethod.GET) 
	public ModelAndView getQna(HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		// 세션에 입력된 유저 데이터 취득
		// UserVO userVO = (UserVO)request.getSession().getAttribute("userVO");
		SecurityAuthorities securityAuthorities = new SecurityAuthorities();
		// 유저권한 체크, 관리자가 아닐 경우 에러페이지로 이동
		if(!securityAuthorities.hasRoleAdmin()) {
			mv.addObject("result", "유저 권한 실패");
			mv.setViewName("contents/error");
		} else {
			mv.setViewName("contents/contact/qna_regist");
		}

		return mv;
	}
	
	// 자주묻는질문 등록페이지에서 등록할 경우, DB로 입력처리하는 메소드
	@RequestMapping(value = "/admin/qna", method = RequestMethod.POST) 
	public ModelAndView postQna(HttpServletRequest request,
									@ModelAttribute QnaVO qnaVO) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		SecurityAuthorities securityAuthorities = new SecurityAuthorities();
		// 세션에 입력된 유저 데이터 취득
		// UserVO userVO = (UserVO)request.getSession().getAttribute("userVO");
		// qnaVO.setSeq_user_id(seq_user_id);
		
		// 유저권한 체크, 관리자가 아닐 경우 에러페이지로 이동
		if(!securityAuthorities.hasRoleAdmin()) {
			mv.addObject("result", "유저 권한 실패");
			mv.setViewName("contents/error");
			return mv;
		} 
		// DB 입력확인
		int flag = qnaService.insertQna(qnaVO);
		if(flag != 200) {
			mv.addObject("result", "ERROR_" + flag);
			mv.setViewName("contents/error");
			return mv;
		}
		mv.addObject("result", "자주묻는질문 등록");
		mv.setViewName("contents/complete");
		
		return mv;
	}
	
	// 자주묻는질문 수정 페이지 호출 메소드
	@RequestMapping(value = "/admin/qna/{qna_id}", method = RequestMethod.GET)
	public ModelAndView getQnaUpdate(HttpServletRequest request,
									@PathVariable int qna_id) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		QnaVO qnaVO = new QnaVO();
		//UserVO userVO = (UserVO)request.getSession().getAttribute("userVO");
		SecurityAuthorities securityAuthorities = new SecurityAuthorities();
		// 유저 권한 체크
		if(!securityAuthorities.hasRoleAdmin()) {
			mv.addObject("result", "유저 권한 실패");
			mv.setViewName("contents/error");
			return mv;
		}
		
		qnaVO = qnaService.selectQna(qna_id);
		mv.addObject("qnaVO", qnaVO);
		mv.setViewName("contents/contact/qna_update");
		return mv;
	}
	
	@RequestMapping(value = "/admin/qna/{qna_id}/update", method = RequestMethod.POST)
	public ModelAndView patchQnaUpdate(HttpServletRequest request,
									@ModelAttribute QnaVO qnaVO) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		//UserVO userVO = (UserVO)request.getSession().getAttribute("userVO");
		SecurityAuthorities securityAuthorities = new SecurityAuthorities();
		// 유저 권한 체크
		if(!securityAuthorities.hasRoleAdmin()) {
			mv.addObject("result", "유저 권한 실패");
			mv.setViewName("contents/error");
			return mv;
		}
		
		int service_request = qnaService.updateQna(qnaVO);
		if(service_request != 200) {
			mv.addObject("result", "ERROR_" + service_request);
			mv.setViewName("contents/error");
			return mv;
		}
		
		mv.addObject("result", "자주묻는질문 수정");
		mv.setViewName("contents/complete");
		return mv;
	}
	
	@RequestMapping(value = "/admin/qna/{qna_id}/delete", method = RequestMethod.POST)
	public ModelAndView deleteQna(HttpServletRequest request,
								@PathVariable int qna_id) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		// UserVO userVO = (UserVO)request.getSession().getAttribute("userVO");
		SecurityAuthorities securityAuthorities = new SecurityAuthorities();
		// 유저 권한 체크
		if(!securityAuthorities.hasRoleAdmin()) {
			mv.addObject("result", "유저 권한 실패");
			mv.setViewName("contents/error");
			return mv;
		}
		
		int service_request = qnaService.deleteQna(qna_id);
		if(service_request != 200) {
			mv.addObject("result", "ERROR_" + service_request);
			mv.setViewName("contents/error");
			return mv;
		}
		
		mv.addObject("result", "자주묻는질문 삭제");
		mv.setViewName("contents/complete");
		return mv;
	}
}
