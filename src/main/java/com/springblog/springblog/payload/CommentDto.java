package com.springblog.springblog.payload;

import lombok.Data;

//dto

@Data
public class CommentDto {
	
	private long id;
	private String name;
	private String email;
	private String body;

}
