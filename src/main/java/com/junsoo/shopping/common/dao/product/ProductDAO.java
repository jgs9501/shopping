package com.junsoo.shopping.common.dao.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.junsoo.shopping.common.vo.ProductDetailVO;
import com.junsoo.shopping.common.vo.ProductVO;
import com.junsoo.shopping.common.vo.PurchaseInfoVO;

public interface ProductDAO {
	
	/**
	 * 최근 출시한 상품 조회 메소드
	 * @param category
	 * @return
	 * @throws Exception
	 */
	public List<ProductVO> selectRecentlyProduct(int category) throws Exception;
	
	/**
	 * 특정 상품 정보 조회 메소드
	 * @param product_id
	 * @return
	 * @throws Exception
	 */
	public ProductDetailVO selectProductDetail(int product_id) throws Exception;
	
	/**
	 * 상세상품 페이지에서 같은 점포가 출시한 상품 조회 메소드
	 * @param productVO
	 * @return
	 * @throws Exception
	 */
	public List<ProductVO> selectSameStoreProduct(ProductVO productVO) throws Exception;
	
	/**
	 * 특정 카테고리에 출시된 상품 개수 조회 메소드
	 * @param category
	 * @return
	 * @throws Exception
	 */
	public int selectCategoryProductCount(HashMap<String, Object> hashMap) throws Exception;
	
	/**
	 * 해당 카테고리의 상품 조회 메소드
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<ProductVO> selectCategoryProducts(HashMap<String, Object> hashMap) throws Exception;
	
	/**
	 * 자신이 출품한 상품 조회 (점포)
	 * @param seq_user_id
	 * @return
	 * @throws Exception
	 */
	public List<ProductVO> selectStoreProducts(int seq_user_id) throws Exception;
	
	/**
	 * 출품한 상품의 상세정보 조회 (점포)
	 * @param productVO
	 * @return
	 * @throws Exception
	 */
	public ProductVO selectStoreProduct(ProductVO productVO) throws Exception;
	
	/**
	 * 고객이 특정 상품을 구매했는지 확인하는 조회 메소드
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int selectBuyProduct(Map<String, Integer> map) throws Exception;
	
	/**
	 * 고객이 구매한 모든 상품 조회 메소드
	 * @param seq_user_id
	 * @return
	 * @throws Exception
	 */
	public List<PurchaseInfoVO> selectBuyProducts(int seq_user_id) throws Exception;
	
	/**
	 * 점포의 상품 출판 메소드
	 * @param productVO
	 * @throws Exception
	 */
	public void insertProduct(ProductVO productVO) throws Exception;
	
	/**
	 * 출품한 상품 수정  (점포)
	 * @param productVO
	 * @throws Exception
	 */
	public void updateProduct(ProductVO productVO) throws Exception;
	
}
