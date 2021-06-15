package com.junsoo.shopping.common.dao.contact;

import java.util.List;

import com.junsoo.shopping.common.vo.QnaVO;

public interface QnaDAO {
	
	/** Qna_board 테이블의 모든 컬럼수 반환
	 *  @return int*/
	public int selectAllQnaCount() throws Exception;
	
	/** Qna_board 테이블의 각각의 상담 종류 컬럼수 반환
	 *  @param String type @return int */
	public int selectTypeQnaCount(String type) throws Exception;
	
	/**
	 * Qna_board 테이블의 특정 데이터 반환
	 * @return QnaVO*/
	public QnaVO selectQna(int qna_id) throws Exception;
	
	/** Qna_board 테이블의 모든 컬럼의 데이터 반환
	 *  @return List<QnaVO>*/
	public List<QnaVO> selectAllQna();
	
	/** Qna_board 테이블의 각각의 상담 종류 컬럼 반환
	 *  @param String type @return List<QnaVO>*/
	public List<QnaVO> selectTypeQna(String type) throws Exception;
	
	/** Qna_board 테이블 데이터 삽입
	 *  @param QnaVO(int seq_user_id, String title, String answer, String type) 
	 *  @return int*/
	public int insertQna(QnaVO qnaVO) throws Exception;
	
	/** Qna_board 테이블 데이터 수정
	 *  @param QnaVO(String title, String answer, String type) 
	 *  @return int*/
	public int updateQna(QnaVO qnaVO) throws Exception;
	
	/** Qna_board 테이블 데이터 삭제
	 *  @param int qna_id 
	 *  @return int*/
	public int deleteQna(int qna_id) throws Exception;
}
