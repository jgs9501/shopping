package com.junsoo.shopping.common.controller.contact;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.junsoo.shopping.common.service.contact.NoticeService;
import com.junsoo.shopping.common.vo.NoticeVO;

@RestController
public class NoticeController {

	@Inject
	NoticeService noticeService;
	
	// 공지사항 등록 페이지 전환 메소드
	@RequestMapping(value = "/notice", method = RequestMethod.GET) 
	public ModelAndView getNotice() throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("contents/contact/notice_regist");
		return mv;
	}
	
	// 공지사항 등록 메소드
	@RequestMapping(value = "/notice", method = RequestMethod.POST)
	public ModelAndView postNotice(HttpServletRequest request,
									@ModelAttribute NoticeVO noticeVO,
									@RequestPart("uploadFile1") MultipartFile uploadFile1,
									@RequestPart("uploadFile2") MultipartFile uploadFile2,
									@RequestPart("uploadFile3") MultipartFile uploadFile3) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		List<MultipartFile> uploadFile_list = new ArrayList<MultipartFile>();
		
		uploadFile_list.add(uploadFile1);
		uploadFile_list.add(uploadFile2);
		uploadFile_list.add(uploadFile3);
		
		int service_flag = noticeService.insertNotice(noticeVO, uploadFile_list);
		if(service_flag == 200) {
			mv.addObject("result", "공지사항등록");
			mv.setViewName("contents/complete");
		} else {
			mv.addObject("result", "ERROR_" + service_flag);
			mv.setViewName("contents/error");
		}
		
		return mv;
	}
	
}
