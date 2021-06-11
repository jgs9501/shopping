package com.junsoo.shopping.common.service.contact;

import java.util.List;

import com.junsoo.shopping.common.vo.QnaVO;

public interface QnaService {
	
	/**
	 * 자주묻는질문의 모든 컬럼수 반환
	 * @return
	 * @throws Exception
	 */
	public int selectAllQnaCount() throws Exception;
	
	/**
	 * 자주묻는질문 각각의 상담종류 컬럼수 반환
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public int selectTypeQnaCount(String type) throws Exception;
	
	/**
	 * 자주묻는질문 전체 컬럼의 데이터 반환
	 * @return
	 */
	public List<QnaVO> selectAllQna();
	
	/**
	 * 자주묻는질문 해당 상담종류의 전체 데이터 반환
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<QnaVO> selectTypeQna(String type) throws Exception;
	
	/**
	 * 자주묻는질문 등록 및 데이터 검사
	 * @param qnaVO
	 * @return
	 * @throws Exception
	 */
	public int insertQna(QnaVO qnaVO) throws Exception;
	
	/**
	 * 자주묻는질문 수정
	 * @param qnaVO
	 * @return
	 * @throws Exception
	 */
	public int updateQna(QnaVO qnaVO) throws Exception;
	
	/**
	 * 자주묻는질문 삭제
	 * @param qna_id
	 * @return
	 * @throws Exception
	 */
	public int deleteQna(int qna_id) throws Exception;
}
