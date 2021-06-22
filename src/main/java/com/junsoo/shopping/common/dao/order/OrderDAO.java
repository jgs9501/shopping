package com.junsoo.shopping.common.dao.order;

import java.util.HashMap;
import java.util.List;

import com.junsoo.shopping.common.vo.OrderVO;

/**
 * order_tbl 관련 SQL호출 메소드
 * @author jjsoo
 *
 */
public interface OrderDAO {

	/**
	 * order_tbl 테이블의 모든 데이터 조회 메소드
	 * @param seq_user_id
	 * @return
	 * @throws Exception
	 */
	public List<OrderVO> selectAllOrder(int seq_user_id) throws Exception;
	
	/**
	 * order_tbl 테이블의 특정 주문번호 조회 메소드
	 * @param orderVO
	 * @return
	 * @throws Exception
	 */
	public OrderVO selectOrder(OrderVO orderVO) throws Exception;
	
	/**
	 * order_tbl 테이블의 결제완료시 데이터 추가 메소드
	 * @param orderVO
	 * @throws Exception
	 */
	public void insertOrder(OrderVO orderVO) throws Exception;
	
	/**
	 * order_tbl 테이블의 주문상품 상태 수정 메소드
	 * @param orderVO
	 * @throws Exception
	 */
	public void updateOrderStatus(OrderVO orderVO) throws Exception;
	
	/**
	 * order_tbl 테이블의 반품신청 시 총가격 갱신하는 메소드
	 * @param map
	 * @throws Exception
	 */
	public void updateOrderTotalPrice(HashMap<String, Object> map) throws Exception;
	
	/**
	 * order_tbl 주문 삭제 메소드
	 * @param orderVO
	 * @throws Exception
	 */
	public void deleteOrder(OrderVO orderVO) throws Exception;
	
	/**
	 * order_tbl 주문 삭제 메소드
	 * @param map
	 * @throws Exception
	 */
	public void deleteOrder(HashMap<String, Object> map) throws Exception;
}
