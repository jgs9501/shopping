package com.junsoo.shopping.common.dao.product;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.junsoo.shopping.common.vo.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	private static final Logger logger = LoggerFactory.getLogger(ProductDAO.class);
	private static final String namespace = "com.mapper.productMapper";
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public void insertProduct(ProductVO productVO) throws Exception {
		logger.info("insertProduct method called");
		sqlSession.insert(namespace + ".insertProduct", productVO);
	}

	@Override
	public List<ProductVO> selectRecentlyProduct(int category) throws Exception {
		
		logger.info("selectCategoryProduct method called");
		return sqlSession.selectList(namespace + ".selectRecentlyProduct", category);
	}

}
