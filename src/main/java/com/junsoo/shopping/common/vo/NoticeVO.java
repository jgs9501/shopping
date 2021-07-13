package com.junsoo.shopping.common.vo;

import org.springframework.lang.NonNull;

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
public class NoticeVO {

	@NonNull
	private int notice_id;
	@NonNull
	private String title;
	private String content;
	private String content_img1;
	private String content_img2;
	private String content_img3;
	private int views;
	@NonNull
	private String reg_date;
	@NonNull
	private String mod_date;
	@NonNull
	private String type;
}
