package com.junsoo.shopping.common.vo.regist;

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
/**
	CREATE TABLE `USER_DATA`(							
		`seq_user_id` int(10) NOT NULL auto_increment primary key ,						
	    `reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 					
	    `mod_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,						
	    `del_flg` char(1) NOT NULL DEFAULT 'N',						
	    `user_id` char(13) NOT NULL,						
	    `password` char(32) NOT NULL,						
	    `user_name` char(10) NOT NULL,						
	    `user_email` varchar(20) NOT NULL,		
        `user_domain` varchar(20) NOT NULL,
	    `user_birthday` DATE NOT NULL,						
	    `user_gender` char(1) NOT NULL,						
	    `user_phone` char(11) NOT NULL,						
	    `user_postNum` int(7) NOT NULL,
        `user_post` varchar(100) NOT NULL,
	    `user_postDetail` varchar(100) NOT NULL,						
	    `user_ipaddress` char(15) 					
	)		
 *
 * @author jjsoo
 *
 */
public class UserVO {
	
	private int    seq_user_id;
	private String reg_date;
	private String mod_date;
	private char   del_flg;
	private String user_id;
	private String password;
	private String user_name;
	private String user_email;
	private String user_domain;
	private String user_birthday;
	private char   user_gender;
	private String user_phone;
	private int    user_postNum;
	private String user_post;
	private String user_postDetail;
	private String user_ipaddress;
}
