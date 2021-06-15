package com.junsoo.shopping.common.service.contact;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.junsoo.shopping.common.vo.NoticeVO;

public interface NoticeService {

	/**
	 * 공지사항의 모든 컬럼수 반환
	 * @return int
	 * @throws Exception
	 */
	public int selectAllNoticeCount() throws Exception;
	
	/**
	 * 공지사항 각각의 상담종류 컬럼수 반환
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public int selectTypeNoticeCount(String type) throws Exception;
	
	/**
	 * 특정 공지사항의 모든 데이터 반환
	 * @param notice_id
	 * @return
	 */
	public NoticeVO selectNotice(int notice_id) throws Exception;
	
	/**
	 * 공지사항 전체 컬럼의 데이터 반환
	 * @return
	 */
	public List<NoticeVO> selectAllNotice();
	
	/**
	 * 공지사항 해당 상담종류의 전체 데이터 반환
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<NoticeVO> selectTypeNotice(String type) throws Exception;
	
	/**
	 * 공지사항 페이징처리 데이터 반환
	 * @param hashmap
	 * @return
	 * @throws Exception
	 */
	public List<NoticeVO> selectAllNoticePaging(HashMap<String, Object> hashMap) throws Exception;
	/**
	 * 공지사항 등록 및 데이터 검사
	 * @param noticeVO
	 * @param uploadFile_list
	 * @return
	 * @throws Exception
	 */
	public int insertNotice(NoticeVO noticeVO, List<MultipartFile> uploadFile_list) throws Exception;
	
	/**
	 * 공지사항 수정
	 * @param noticeVO
	 * @return
	 * @throws Exception
	 */
	public int updateNotice(NoticeVO noticeVO, List<MultipartFile> uploadFile_list) throws Exception;
	
	/**
	 * 해당 공지사항의 조회수 증가 메소드
	 * @param noticeVO
	 * @return
	 * @throws Exception
	 */
	public int updateViewsNotice(NoticeVO noticeVO) throws Exception;
	
	/**
	 * 공지사항 삭제
	 * @param notice_id
	 * @return
	 * @throws Exception
	 */
	public int deleteNotice(int notice_id) throws Exception;
}
