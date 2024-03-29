package com.junsoo.shopping.common.vo;

import org.springframework.lang.NonNull;

import com.junsoo.shopping.common.vo.paging.PaginationInfo;

import lombok.Getter;
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
    weight varchar(10),
    attention varchar(1000),
    valid_date varchar(20),
    use_info varchar(1000),
    country varchar(50),
    PRIMARY KEY(product_id),
    FOREIGN KEY(seq_user_id) REFERENCES user_data(seq_user_id)
    );
 * @author jjsoo
 *
 */
@Getter
@Setter
@ToString
public class ProductVO {

	private PaginationInfo paginationInfo;
	
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
	private String sale;
	@NonNull
	private int discount;
	@NonNull
	private String weight;
	@NonNull
	private String attention;
	@NonNull
	private String valid_date;
	@NonNull
	private String use_info;
	@NonNull
	private String country;
	
	// 댓글 테이블과 LEFT JOIN 으로 인해 선언 (product 테이블의 컬럼은 존재안함)
	private float rating;
	
}
