package com.junsoo.shopping.common.dao.contact;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.NoticeVO;

@Repository
public class NoticeDAOImpl implements NoticeDAO {

	private static final Logger logger = LoggerFactory.getLogger(NoticeDAO.class);
	private static final String namespace = "com.mapper.noticeMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public int selectAllNoticeCount() throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectAllNoticeCount");
		} catch (DataAccessException dae) {
			logger.error("Data access Exception", dae);
			return -1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -1;
		}
	}

	@Override
	public int selectTypeNoticeCount(String type) throws Exception {

		try {
			
			return sqlSession.selectOne(namespace + ".selectTypeNoticeCount", type);
		} catch (DataAccessException dae) {
			logger.error("Data access Exception", dae);
			return -1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return -1;
		}
	}

	@Override
	public NoticeVO selectNotice(int notice_id) throws Exception {
		
		try {
			
			return sqlSession.selectOne(namespace + ".selectNotice", notice_id);
		} catch (DataAccessException dae) {
			logger.error("Data access Exception", dae);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@Override
	public List<NoticeVO> selectAllNotice() throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectAllNotice");
		} catch (DataAccessException dae) {
			logger.error("Data access Exception", dae);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<NoticeVO> selectTypeNotice(String type) throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectTypeNotice", type);
		} catch (DataAccessException dae) {
			logger.error("Data access Exception", dae);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<NoticeVO> selectAllNoticePaging(HashMap<String, Object> hashMap) throws Exception {
		
		try {
			
			return sqlSession.selectList(namespace + ".selectAllNoticePaging", hashMap);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@Override
	public int insertNotice(NoticeVO noticeVO) throws Exception {
		
		try {
			
			logger.info("Notice has been created. : " + noticeVO);
			sqlSession.insert(namespace + ".insertNotice", noticeVO);
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
	public int updateNotice(NoticeVO noticeVO) throws Exception {
		
		try {
			
			logger.info("Notice has been updated. : " + noticeVO);
			sqlSession.update(namespace + ".updateNotice", noticeVO);
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
	public int updateViewsNotice(NoticeVO noticeVO) throws Exception {

		try {
			
			sqlSession.update(namespace + ".updateViewsNotice", noticeVO);
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
	public int deleteNotice(int notice_id) throws Exception {
		
		try {
			
			logger.info("Notice has been deleted. notice_id: " + notice_id);
			sqlSession.delete(namespace + ".deleteNotice", notice_id);
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
