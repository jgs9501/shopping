package com.junsoo.shopping.common.vo;

import org.springframework.lang.NonNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * CREATE TABLE payment (
	`payment_id` varchar(20) NOT NULL primary key,
	`seq_user_id` int(10) NOT NULL ,
    `product_id` int(10) NOT NULL,
    `reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    `mod_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,						
	`order_flg` char(1) NOT NULL DEFAULT 'N',
	`request` varchar(50),
    FOREIGN KEY(seq_user_id) REFERENCES user_data(seq_user_id),
    FOREIGN KEY(product_id) REFERENCES product(product_id)
    
);
 * @author jjsoo
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class OrderVO {
	@NonNull
	private String order_id;
	@NonNull
	private int seq_user_id;
	@NonNull
	private String order_date;
	@NonNull
	private String mod_date;
	@NonNull
	private char order_status;

	private String request;
	@NonNull
	private String pay_type;
	@NonNull
	private int total_price;
}
