package com.junsoo.shopping.common.vo;

import org.springframework.lang.NonNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * CREATE TABLE product_reply (
	`seq_user_id` int(10) NOT NULL,
    `product_id` int(10) NOT NULL,
    `user_name` char(13) NOT NULL,
	`reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    `mod_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,	
    `content` varchar(1000),
    `rating` float(2,1),
    primary key(seq_user_id),
    foreign key(product_id) references product(product_id)
)
 * @author jjsoo
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class ProductReplyVO {
	@NonNull
	private int seq_user_id;
	@NonNull
	private int product_id;
	@NonNull
	private String user_name;
	@NonNull
	private String reg_date;
	@NonNull
	private String mod_date;
	@NonNull
	private String content;
	@NonNull
	private float rating;
	@NonNull
	private String answer;
}
