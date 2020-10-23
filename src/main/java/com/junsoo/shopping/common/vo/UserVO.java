package com.junsoo.shopping.common.vo;

import java.util.Date;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *  유저의 데이터를 선언한 클래스
	CREATE TABLE `USER_DATA`(							
		`seq_user_id` int(10) NOT NULL auto_increment primary key ,						
		`reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 					
		`mod_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,						
		`del_flg` char(1) NOT NULL DEFAULT 'N',						
		`user_id` char(13) NOT NULL,						
		`password` varchar(100) NOT NULL,						
		`user_name` char(10) NOT NULL,						
		`user_email` varchar(20) NOT NULL,		
		`user_domain` varchar(20) NOT NULL,
		`user_birthday` DATE NOT NULL,						
		`user_gender` char(1) NOT NULL,						
		`user_phone` char(11) NOT NULL,						
		`user_post` int(7) NOT NULL,
		`user_address` varchar(100) NOT NULL,
		`user_detail_address` varchar(100) NOT NULL,						
		`user_ipaddress` char(15) 					
	)
 *
 * @author jjsoo
 *
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserVO {
	
	@NonNull
	private int    seq_user_id;
	@NonNull
	private Date   reg_date;
	@NonNull
	private Date   mod_date;
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
}
