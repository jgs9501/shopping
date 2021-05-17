package com.junsoo.shopping.common.vo;

import java.util.List;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderDetailVO {
	@NonNull
	private String order_detail_id;
	@NonNull
	private int seq_user_id;
	@NonNull
	private int product_id;
	@NonNull
	private int amount;
	@NonNull
	private String order_id;
	@NonNull
	private String order_date;
	@NonNull
	private String mod_date;
	@NonNull
	private String product_thumbImg;
	@NonNull
	private int product_price;
	@NonNull
	private String product_name;
	
	private List<OrderDetailVO> orderDetailsVO;
}
