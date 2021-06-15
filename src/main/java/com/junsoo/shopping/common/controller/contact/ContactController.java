package com.junsoo.shopping.common.controller.contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.junsoo.shopping.common.service.contact.NoticeService;
import com.junsoo.shopping.common.service.contact.QnaService;
import com.junsoo.shopping.common.vo.NoticeVO;
import com.junsoo.shopping.common.vo.QnaVO;
import com.junsoo.shopping.common.vo.paging.PaginationInfo;

@RestController
public class ContactController {
	
	@Inject
	private NoticeService noticeService;
	
	@Inject
	private QnaService qnaService;
	
	// 고객센터 호출 메소드
	@RequestMapping(value = "/contact", method = RequestMethod.GET) 
	public ModelAndView getContact(HttpServletRequest request,
									@RequestParam(defaultValue = "1") int curPage,
									@RequestParam(defaultValue = "10") int pageSize) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		List<NoticeVO> noticeVO_list = new ArrayList<NoticeVO>();
		List<QnaVO> qnaVO_list = new ArrayList<QnaVO>();
		// 공지사항 전제수
		int totalNoticeCount = noticeService.selectAllNoticeCount();
		// 공지사항 첫페이지 리스트
		PaginationInfo paginationInfo = new PaginationInfo(totalNoticeCount, curPage);
		paginationInfo.setPageSize(pageSize);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("startIndex", paginationInfo.getStartIndex());
		hashMap.put("pageSize", paginationInfo.getPageSize());
		// 공지사항 페이징 리스트
		noticeVO_list = noticeService.selectAllNoticePaging(hashMap);
		
		// 자주묻는질문 모든 데이터를 받는 변수
		qnaVO_list = qnaService.selectAllQna();
		
		mv.addObject("totalNoticeCount", totalNoticeCount);
		mv.addObject("pagination", paginationInfo);
		mv.addObject("noticeVO_list", noticeVO_list);
		mv.addObject("qnaVO_list", qnaVO_list);
		mv.setViewName("contents/contact/contact");
		return mv;
	}
	
	// 특정 공지사항 데이터 호출 메소드
	@RequestMapping(value = "contact/notice/{notice_id}", method = RequestMethod.GET)
	public ModelAndView getContactNotice(HttpServletRequest request,
											@PathVariable int notice_id) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		NoticeVO noticeVO = new NoticeVO();
		// 특정 공지사항 데이터 변수
		noticeVO = noticeService.selectNotice(notice_id);
		// 특정 공지사항 조회수 증가
		noticeService.updateViewsNotice(noticeVO);
		noticeVO.setViews(noticeVO.getViews()+1);
		
		mv.addObject("noticeVO", noticeVO);
		mv.setViewName("contents/contact/notice_detail");
		return mv;
	}
	
	
	
}