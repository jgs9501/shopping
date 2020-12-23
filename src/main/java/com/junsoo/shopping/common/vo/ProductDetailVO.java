package com.junsoo.shopping.common.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class ProductDetailVO {

	private ProductVO productVO;
	private StoreVO storeVO;
}
