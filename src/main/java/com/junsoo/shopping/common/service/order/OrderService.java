package com.junsoo.shopping.common.service.order;

import java.util.HashMap;
import java.util.List;

import com.junsoo.shopping.common.vo.OrderVO;

/**
 * 주문/결제 상세 메소드<br>
 * 결제할 경우 모든 상품관련 비즈니스 처리 메소드
 * @author jjsoo
 *
 */
public interface OrderService {

	/**
	 * 결제한 모든 상품들 조회 메소드
	 * @param seq_user_id
	 * @return
	 * @throws Exception
	 */
	public List<OrderVO> selectAllOrder(int seq_user_id) throws Exception;
	
	/**
	 * 결제한 특정 상품의 order_id로 조회 메소드
	 * @param orderVO
	 * @return
	 * @throws Exception
	 */
	public OrderVO selectOrder(OrderVO orderVO) throws Exception;
	
	/**
	 * 결제 완료시 입력 메소드
	 * @param orderVO
	 * @return
	 * @throws Exception
	 */
	public int insertOrder(OrderVO orderVO) throws Exception;
	
	/**
	 * 상품 상태 갱신 메소드
	 * @param orderVO
	 * @return
	 * @throws Exception
	 */
	public int updateOrderStatus(OrderVO orderVO) throws Exception;
	
	/**
	 * 결제 상품 반품관련 시 계산 가격 반환 메소드
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateOrderTotalPrice(HashMap<String, Object> map) throws Exception;
	
	/**
	 * 결제한 상품 삭제 메소드
	 * @param orderVO
	 * @return
	 * @throws Exception
	 */
	public int deleteOrder(OrderVO orderVO) throws Exception;
	
	/**
	 * 결제한 상품 삭제 메소드
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteOrder(HashMap<String, Object> map) throws Exception;
}
