package com.springblog.springblog.service;

import java.util.List;

import com.springblog.springblog.payload.PostDto;
import com.springblog.springblog.payload.PostResopnse;



public interface PostService {
	
	PostDto createPost(PostDto postDto);
	
	PostResopnse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
	
	PostDto getPostById(Long id);
	
	PostDto updatePost(PostDto postDto, Long id);
	
	void deletePost(Long id);

}
