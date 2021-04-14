package com.junsoo.shopping.common.vo;

import org.springframework.lang.NonNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * `cart_id` int(10) NOT NULL auto_increment primary key,
	`seq_user_id` int(10) NOT NULL ,
    `product_id` int(10) NOT NULL,
	`amount` int(10) NOT NULL,
    `reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    `mod_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,						
	`del_flg` char(1) NOT NULL DEFAULT 'N',
    FOREIGN KEY(seq_user_id) REFERENCES user_data(seq_usecartr_id),
    FOREIGN KEY(product_id) REFERENCES product(product_id)
 * @author jjsoo
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class CartVO {

	@NonNull
	private int cart_id;
	@NonNull
	private int seq_user_id;
	@NonNull
	private int product_id;
	@NonNull
	private int amount;
	@NonNull
	private String reg_date;
	@NonNull
	private String mod_date;
	@NonNull
	private char del_flg;
	
}
