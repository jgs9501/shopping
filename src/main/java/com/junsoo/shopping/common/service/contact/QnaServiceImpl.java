package com.junsoo.shopping.common.service.contact;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junsoo.shopping.common.dao.contact.QnaDAO;
import com.junsoo.shopping.common.vo.QnaVO;
import com.junsoo.shopping.utils.checker.ValueChecker;

@Service
@Transactional
public class QnaServiceImpl implements QnaService {

	private static final Logger logger = LoggerFactory.getLogger(QnaService.class);
	
	@Inject
	QnaDAO qnaDAO;
	
	@Override
	public int selectAllQnaCount() throws Exception {
		
		try {
			return qnaDAO.selectAllQnaCount();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -1;
		}
	}

	@Override
	public int selectTypeQnaCount(String type) throws Exception {
		
		ValueChecker checker = new ValueChecker();
		try {
			if(!checker.isExistContactType(type)) {
				logger.error("type does not exist");
				return -1;
			}
			return qnaDAO.selectTypeQnaCount(type);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -1;
		}
	}

	@Override
	public QnaVO selectQna(int qna_id) throws Exception {
		
		try {
			QnaVO qnaVO = qnaDAO.selectQna(qna_id);
			qnaVO.setAnswer(qnaVO.getAnswer().replaceAll("<br>", "\n"));
			return qnaVO;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@Override
	public List<QnaVO> selectAllQna() {
		
		try {
			return qnaDAO.selectAllQna();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<QnaVO> selectTypeQna(String type) throws Exception {
		
		ValueChecker checker = new ValueChecker();
		try {
			if(!checker.isExistContactType(type)) {
				logger.error("type does not exist");
				return null;
			}
			return qnaDAO.selectTypeQna(type);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public int insertQna(QnaVO qnaVO) throws Exception {
		
		ValueChecker vc = new ValueChecker();
		try {
			// 자주묻는질문의 상담종류 존재 확인
			// 해당 항목이 없을 경우, 기타항목으로 설정
			if(!vc.isExistContactType(qnaVO.getType())) {
				logger.warn("The type does not exist, so register it as an ETC.");
				qnaVO.setType("ETC");
			}

			// 자주묻는질문의 제목입력 확인
			if(qnaVO.getTitle().isEmpty() || qnaVO.getTitle() == "") {
				logger.error("Does not exist title. qnaVO : " + qnaVO);
				return 4002;
			}
				
			// 자주묻는질문의 내용입력 확인
			if(qnaVO.getAnswer().isEmpty() || qnaVO.getAnswer() == "") {
				logger.error("Does not exist content. qnaVO : " + qnaVO);
				return 4003;
			}
			
			// 자주묻는질문의 textarea 줄바꿈 치환
			qnaVO.setAnswer(qnaVO.getAnswer().replaceAll("\n", "<br>"));
				
			qnaDAO.insertQna(qnaVO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return 4000;
		}
		return 200;
	}

	@Override
	public int updateQna(QnaVO qnaVO) throws Exception {
		
		try {
			// 자주묻는질문 고유값 확인
			if(qnaVO.getQna_id() < 1) {
				logger.error("Does not exist QnA. : " + qnaVO);
				return 4001;
			}
			// 자주묻는질문 제목 확인
			if(qnaVO.getTitle().isEmpty() || qnaVO.getTitle() == "") {
				logger.error("Does not exist title. : " + qnaVO);
				return 4002;
			}
			// 자주묻는질문 내용 확인
			if(qnaVO.getAnswer().isEmpty() || qnaVO.getAnswer() == "") {
				logger.error("Does not exist content. : " + qnaVO);
				return 4003;
			}
			
			qnaVO.setAnswer(qnaVO.getAnswer().replaceAll("\n", "<br>"));
			qnaDAO.updateQna(qnaVO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return 4000;
		}
		return 200;
	}

	@Override
	public int deleteQna(int qna_id) throws Exception {
		
		try {
			if(qna_id < 1) {
				logger.error("Does not exist QnA. qna_id : " + qna_id);
				return 4001;
			}
			
			qnaDAO.deleteQna(qna_id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return 4000;
		}
		return 200;
	}

}
