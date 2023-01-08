package com.springblog.springblog.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PostDto {
	

	private Long id;
	
	@NotEmpty
	@Size(min = 2, message = "title must have at least 2 chars")
	private String title;
	
	
	@NotEmpty
	@Size(min = 10, message = "description must have at least 10 chars")
	private String description;
	
	
	@NotEmpty
	@Size(min = 10, message = "content must have at least 10 chars")
	private String content;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	

}
