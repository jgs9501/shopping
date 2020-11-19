package com.junsoo.shopping.common.vo;

import org.springframework.lang.NonNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
	CREATE TABLE `USER_DATA`(							
		`seq_user_id` int(10) NOT NULL auto_increment primary key ,						
		`reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 					
		`mod_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,						
		`del_flg` char(1) NOT NULL DEFAULT 'N',						
		`user_id` char(13) NOT NULL,						
		`password` varchar(100) NOT NULL,						
		`user_name` char(10) NOT NULL,						
		`user_email` varchar(20) NOT NULL,
		`user_birthday` DATE NOT NULL,						
		`user_gender` char(1) NOT NULL,						
		`user_phone` char(11) NOT NULL,						
		`user_post` int(7) NOT NULL,
		`user_address` varchar(100) NOT NULL,
		`user_detail_address` varchar(100) NOT NULL,						
		`user_ipaddress` char(15),
		`auth` int(1) DEFAULT 1				
	)
 *
 * @author jjsoo
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class UserVO {
	
	@NonNull
	private int    seq_user_id;
	@NonNull
	private String reg_date;
	@NonNull
	private String mod_date;
	@NonNull
	private char   del_flg;
	@NonNull
	private String user_id;
	@NonNull
	private String password;
	@NonNull
	private String user_name;
	@NonNull
	private String user_email;
	@NonNull
	private String user_domain;
	@NonNull
	private String user_birthday;
	@NonNull
	private char   user_gender;
	@NonNull
	private String user_phone;
	@NonNull
	private int    user_post;
	@NonNull
	private String user_address;
	@NonNull
	private String user_detail_address;
	@NonNull
	private String user_ipaddress;
	@NonNull
	private int	   auth;
}
