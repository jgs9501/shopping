package com.junsoo.shopping.common.controller.contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
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
	public ModelAndView getContact(HttpServletRequest request, @RequestParam(defaultValue = "1") int curPage,
			@RequestParam(defaultValue = "10") int pageSize) throws Exception {

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

	// 자주묻는질문 Ajax 통신
	// 해당 종류 리스트 호출 메소드
	@RequestMapping(value = "/contact/ajaxQnaList", method = RequestMethod.POST)
	public List<QnaVO> ajaxQnaList(@RequestBody HashMap<String, Object> hashMap) throws Exception {

		List<QnaVO> list = new ArrayList<QnaVO>();
		String type = String.valueOf(hashMap.get("type"));
		list = qnaService.selectTypeQna(type);

		return list;
	}

	// 공지사항 메소드 Ajax 통신
	// notice_boardTemplate.jsp -> contact.jsp html 형식 리턴
	@RequestMapping(value = "/contact/ajaxNoticeTemplate", method = RequestMethod.POST)
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

}