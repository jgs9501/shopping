package com.junsoo.shopping.common.controller.contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.junsoo.shopping.common.service.contact.NoticeService;
import com.junsoo.shopping.common.service.contact.QnaService;
import com.junsoo.shopping.common.vo.NoticeVO;
import com.junsoo.shopping.common.vo.paging.PaginationInfo;

@RestController
public class ContactController {
	
	@Inject
	private NoticeService noticeService;
	@Inject
	private QnaService qnaService;
	

	@RequestMapping(value = "/contact", method = RequestMethod.GET) 
	public ModelAndView getContact(HttpServletRequest request,
									@RequestParam(defaultValue = "1") int curPage,
									@RequestParam(defaultValue = "10") int pageSize) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		List<NoticeVO> noticeVO_list = new ArrayList<NoticeVO>();
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
		
		mv.addObject("totalNoticeCount", totalNoticeCount);
		mv.addObject("pagination", paginationInfo);
		mv.addObject("noticeVO_list", noticeVO_list);
		mv.setViewName("contents/contact/contact");
		return mv;
	}
	
	// 공지사항 메소드 Ajax 통신
	// notice_boardTemplate.jsp -> contact.jsp html 형식 리턴
	@RequestMapping(value = "ajaxNoticeTemplate", method = RequestMethod.POST)
	public ModelAndView getNoticeTemplate(@RequestBody HashMap<String, Integer> param) throws Exception {
		
		int curPage = param.get("curPage");
		int pageSize = param.get("pageSize");
		ModelAndView mv = new ModelAndView();
		List<NoticeVO> noticeVO_list = new ArrayList<NoticeVO>();
		// 공지사항 전제수
		int totalNoticeCount = noticeService.selectAllNoticeCount();
		// 공지사항 페이징 처리를 위한 변수선언
		PaginationInfo paginationInfo = new PaginationInfo(totalNoticeCount, curPage);
		paginationInfo.setPageSize(pageSize);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("startIndex", paginationInfo.getStartIndex());
		hashMap.put("pageSize", paginationInfo.getPageSize());
		// 공지사항 페이징 리스트
		noticeVO_list = noticeService.selectAllNoticePaging(hashMap);
		
		mv.addObject("pagination", paginationInfo);
		mv.addObject("noticeVO_list", noticeVO_list);
		mv.setViewName("contents/contact/notice_boardTemplate");
		return mv;
	}
	
	// 특정 공지사항 데이터 호출 메소드
	@RequestMapping(value = "contact/{notice_id}", method = RequestMethod.GET)
	public ModelAndView getContactNotice(HttpServletRequest request,
											@PathVariable int notice_id) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		NoticeVO noticeVO = new NoticeVO();
		// 특정 공지사항 데이터 변수
		noticeVO = noticeService.selectNotice(notice_id);
		// 특정 공지사항 조회수 증가
		noticeService.updateViewsNotice(noticeVO);
		mv.addObject("noticeVO", noticeVO);
		mv.setViewName("contents/contact/notice_detail");
		return mv;
	}
	
}