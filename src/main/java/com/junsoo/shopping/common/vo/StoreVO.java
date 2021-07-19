package com.junsoo.shopping.common.vo;

import org.springframework.lang.NonNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 	seq_user_id int(10) NOT NULL ,
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    mod_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,						
	del_flg char(1) NOT NULL DEFAULT 'N',
    store_name varchar(100) NOT NULL,
    store_phone int (11) NOT NULL,
    ratio float(2,1),
    PRIMARY KEY(seq_user_id)
 * @author jjsoo
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class StoreVO {
	
	@NonNull
	private int seq_user_id;
	private String reg_date;
	private String mod_date;
	private char del_flg;
	@NonNull
	private String store_name;
	@NonNull
	private String store_phone;
	private float ratio;
}
