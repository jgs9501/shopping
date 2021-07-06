package com.junsoo.shopping.common.controller.contact;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.junsoo.shopping.common.service.contact.NoticeService;
import com.junsoo.shopping.common.vo.NoticeVO;
import com.junsoo.shopping.common.vo.UserVO;

@RestController
public class NoticeController {

	@Inject
	NoticeService noticeService;

	// 특정 공지사항 데이터 호출 메소드
	@RequestMapping(value = "/notice/{notice_id}", method = RequestMethod.GET)
	public ModelAndView getContactNotice(HttpServletRequest request, @PathVariable int notice_id) throws Exception {

		ModelAndView mv = new ModelAndView();
		NoticeVO noticeVO = new NoticeVO();
		// 특정 공지사항 데이터 변수
		noticeVO = noticeService.selectNotice(notice_id);
		// 특정 공지사항 조회수 증가
		noticeService.updateViewsNotice(noticeVO);
		noticeVO.setViews(noticeVO.getViews() + 1);

		mv.addObject("noticeVO", noticeVO);
		mv.setViewName("contents/contact/notice_detail");
		return mv;
	}

	// 공지사항 등록 페이지 전환 메소드
	@RequestMapping(value = "/admin/notice", method = RequestMethod.GET)
	public ModelAndView getNotice() throws Exception {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("contents/contact/notice_regist");
		return mv;
	}

	// 공지사항 등록 메소드
	@RequestMapping(value = "/admin/notice", method = RequestMethod.POST)
	public ModelAndView postNotice(HttpServletRequest request, @ModelAttribute NoticeVO noticeVO,
			@RequestPart("uploadFile1") MultipartFile uploadFile1,
			@RequestPart("uploadFile2") MultipartFile uploadFile2,
			@RequestPart("uploadFile3") MultipartFile uploadFile3) throws Exception {

		ModelAndView mv = new ModelAndView();
		List<MultipartFile> uploadFile_list = new ArrayList<MultipartFile>();

		uploadFile_list.add(uploadFile1);
		uploadFile_list.add(uploadFile2);
		uploadFile_list.add(uploadFile3);

		int notice_request = noticeService.insertNotice(noticeVO, uploadFile_list);
		if (notice_request == 200) {
			mv.addObject("result", "공지사항등록");
			mv.setViewName("contents/complete");
		} else {
			mv.addObject("result", "ERROR_" + notice_request);
			mv.setViewName("contents/error");
		}

		return mv;
	}

	// 공지사항 템플릿 호출 메소드
	@RequestMapping(value = "/admin/notice/{notice_id}/update", method = RequestMethod.GET)
	public ModelAndView getUpdateNotice(HttpServletRequest request, @PathVariable int notice_id) throws Exception {

		ModelAndView mv = new ModelAndView();
		NoticeVO noticeVO = new NoticeVO();
		noticeVO = noticeService.selectNotice(notice_id);
		noticeVO.setContent(noticeVO.getContent().replaceAll("<br>", "\n"));
		mv.addObject("noticeVO", noticeVO);
		mv.setViewName("contents/contact/notice_update");
		return mv;
	}

	// 공지사항 수정 메소드
	@RequestMapping(value = "/admin/notice/{notice_id}/update", method = RequestMethod.POST)
	public ModelAndView patchNotice(HttpServletRequest request, @ModelAttribute NoticeVO noticeVO,
			@PathVariable int notice_id, @RequestPart("uploadFile1") MultipartFile uploadFile1,
			@RequestPart("uploadFile2") MultipartFile uploadFile2,
			@RequestPart("uploadFile3") MultipartFile uploadFile3) throws Exception {

		ModelAndView mv = new ModelAndView();
		List<MultipartFile> uploadFile_list = new ArrayList<MultipartFile>();

		uploadFile_list.add(uploadFile1);
		uploadFile_list.add(uploadFile2);
		uploadFile_list.add(uploadFile3);

		int notice_request = noticeService.updateNotice(noticeVO, uploadFile_list);

		if (notice_request == 200) {
			mv.addObject("result", "공지사항 수정");
			mv.setViewName("contents/complete");
		} else {
			mv.addObject("result", "ERROR_" + notice_request);
			mv.setViewName("contents/error");
		}
		return mv;
	}

	// 공지사항 삭제 메소드
	@RequestMapping(value = "/admin/notice/{notice_id}/delete", method = RequestMethod.POST)
	public ModelAndView deleteNotice(HttpServletRequest request, @PathVariable int notice_id) throws Exception {

		ModelAndView mv = new ModelAndView();
		UserVO userVO = (UserVO) request.getSession().getAttribute("userVO");
		int auth = userVO.getAuth();
		// 유저 권한 체크
		if (auth != 3) {
			mv.addObject("result", "권한");
			mv.setViewName("contents/error");
			return mv;
		}

		int service_request = noticeService.deleteNotice(notice_id);
		if (service_request != 200) {
			mv.addObject("result", "ERROR_" + service_request);
			mv.setViewName("contents/error");
			return mv;
		}

		mv.addObject("result", "공지삭제");
		mv.setViewName("contents/complete");

		return mv;
	}
}
