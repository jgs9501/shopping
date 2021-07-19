package com.junsoo.shopping.common.dao.contact;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.QnaVO;

@Repository
public class QnaDAOImpl implements QnaDAO {

	private static final Logger logger = LoggerFactory.getLogger(QnaDAO.class);
	private static final String namespace = "com.mapper.qnaMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public int selectAllQnaCount() throws Exception {
		
		try {
			return sqlSession.selectOne(namespace + ".selectAllQnaCount");
		} catch (DataAccessException dae) {
			logger.error("Data access Exception", dae);
			return -1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -1;
		}
	}

	@Override
	public int selectTypeQnaCount(String type) throws Exception {
		
		try {
			return sqlSession.selectOne(namespace + ".selectTypeQnaCount", type);
		} catch (DataAccessException dae) {
			logger.error("Data access Exception", dae);
			return -1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -1;
		}
	}
	
	@Override
	public QnaVO selectQna(int qna_id) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectQna", qna_id);
		} catch (DataAccessException dae) {
			logger.error("Data access Exception", dae);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@Override
	public List<QnaVO> selectAllQna() {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectAllQna");
		} catch (DataAccessException dae) {
			logger.error("Data access Exception", dae);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<QnaVO> selectTypeQna(String type) throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectTypeQna", type);
		} catch (DataAccessException dae) {
			logger.error("Data access Exception", dae);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public int insertQna(QnaVO qnaVO) throws Exception {
		
		try {
			
			logger.info("QnA has been created. : " + qnaVO);
			sqlSession.insert(namespace + ".insertQna", qnaVO);
			return 200;
		} catch (DataAccessException dae) {
			logger.error("Data access Exception", dae);
			return -1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -1;
		}
		
	}

	@Override
	public int updateQna(QnaVO qnaVO) throws Exception {
		
		try {
			
			logger.info("QnA has been updated. : " + qnaVO);
			sqlSession.update(namespace + ".updateQna", qnaVO);
			return 200;
		} catch (DataAccessException dae) {
			logger.error("Data access Exception", dae);
			return -1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -1;
		}
	}

	@Override
	public int deleteQna(int qna_id) throws Exception {
		
		try {
			
			logger.info("QnA has been deleted. qna_id : " + qna_id);
			sqlSession.delete(namespace + ".deleteQna", qna_id);
			return 200;
		} catch (DataAccessException dae) {
			logger.error("Data access Exception", dae);
			return -1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -1;
		}
	}

}
