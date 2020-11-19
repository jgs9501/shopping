package com.junsoo.shopping.common.vo;

import org.springframework.lang.NonNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * CREATE TABLE product (
	seq_user_id int(10) NOT NULL,
    reg_date DATETIME NOT NULL DEFAULT current_timestamp,
    mod_date DATETIME NOT NULL DEFAULT current_timestamp,
    product_id int auto_increment NOT NULL,
    product_name VARCHAR(50) NOT NULL,
    product_cnt  int(10),
    product_price int(10) DEFAULT 0 NOT NULL, 
    product_desc VARCHAR(500) NOT NULL,
    product_img VARCHAR(500) NOT NULL,
    product_thumbImg VARCHAR(500) NOT NULL,
    category int(10) NOT NULL,
    sale char(1) DEFAULT 'Y',
    discount int(10) DEFAULT 0 NOT NULL,
    PRIMARY KEY(product_id),
    FOREIGN KEY(seq_user_id) REFERENCES user_data(seq_user_id)
    );
 * @author jjsoo
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class ProductVO {

	@NonNull
	private int seq_user_id;
	@NonNull
	private String reg_date;
	@NonNull
	private String mod_date;
	@NonNull
	private int product_id;
	@NonNull
	private String product_name;
	@NonNull
	private int product_cnt;
	@NonNull
	private int product_price;
	@NonNull
	private String product_desc;
	@NonNull
	private String product_img;
	@NonNull
	private String product_thumbImg;
	@NonNull
	private int category;
	@NonNull
	private char sale;
	@NonNull
	private int discount;
}
