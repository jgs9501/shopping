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
public class QnaVO {
	
	@NonNull
	private int seq_user_id;
	@NonNull
	private int qna_id;
	@NonNull
	private String title;
	@NonNull
	private String answer;
	@NonNull
	private String reg_date;
	@NonNull
	private String mod_date;
	@NonNull
	private String type;
}
