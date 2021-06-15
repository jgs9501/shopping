package com.junsoo.shopping.common.dao.contact;

import java.util.HashMap;
import java.util.List;

import com.junsoo.shopping.common.vo.NoticeVO;

public interface NoticeDAO {

	/** Notice_board 테이블의 모든 컬럼수 반환 
	 *  @return int*/
	public int selectAllNoticeCount() throws Exception;
	
	/** Notice_board 테이블의 각각의 상담 종류 컬럼수 반환
	 *  @param String 
	 *  @return int*/
	public int selectTypeNoticeCount(String type) throws Exception;

	/**
	 * Notice_board 테이블의 특정 공지사항 정보 반환
	 * @return
	 */
	public NoticeVO selectNotice(int notice_id) throws Exception;
	
	/** Notice_board 테이블의  모든 컬럼의 데이터 반환
	 *  @return List<NoticeVO>*/
	public List<NoticeVO> selectAllNotice() throws Exception;
	
	/**
	 * Notice_board 테이블의 페이징 처리를 위한 데이터 반환
	 * @param hashmap
	 * @return
	 * @throws Exception
	 */
	public List<NoticeVO> selectAllNoticePaging(HashMap<String, Object> hashMap) throws Exception;
	
	/** Notice_board 테이블의 각각의 상담 종류 컬럼 반환
	 *  @param String type
	 *  @return List<NoticeVO>*/
	public List<NoticeVO> selectTypeNotice(String type) throws Exception;
	
	/** Notice_board 테이블의 데이터 삽입<br>
	 * (int seq_user_id, String title, String content, String content_img1, String content_img2, String content_img3, String type)
	 *  @param NoticeVO
	 *  @return int*/
	public int insertNotice(NoticeVO noticeVO) throws Exception;

	/** Notice_board 테이블의 데이터 수정<br>
	 * (String title, String content, String content_img1, String content_img2, String content_img3, String type)
	 *  @param NoticeVO
	 *  @return int*/
	public int updateNotice(NoticeVO noticeVO) throws Exception;
	
	/** Notice_board 테이블의 조회수 데이터 갱신
	 *  @param int notice_id
	 *  @return int*/
	public int updateViewsNotice(NoticeVO noticeVO) throws Exception;
	/** Notice_board 테이블의 데이터 삭제
	 *  @param int notice id
	 *  @return int*/
	public int deleteNotice(int notice_id) throws Exception;
}
