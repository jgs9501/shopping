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
public class UserpointVO {

	@NonNull
	private String user_id;
	@NonNull
	private String user_name;
	@NonNull
	private int    point;
}
