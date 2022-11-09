package com.springblog.springblog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springblog.springblog.payload.PostDto;
import com.springblog.springblog.payload.PostResopnse;
import com.springblog.springblog.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	
	public PostController(PostService pService) {
		super();
		this.pService = pService;
	}

	private PostService pService;
	
	
	
	//create new post
	
	@PostMapping
	public ResponseEntity<PostDto> creatPost(@RequestBody PostDto postdto){
		
		//會被丟進來一個Body 包含了DTO
		
		// 回傳 狀態
		return new ResponseEntity<>(pService.createPost(postdto),HttpStatus.CREATED);
		
	}
	
	
	// get all posts
	@GetMapping
	public PostResopnse getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required =  false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id", required =  false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required =  false) String sortDir

			){
		
		//會被丟進來一個Body 包含了DTO
		
		// 回傳 狀態
		return pService.getAllPosts(pageNo, pageSize,sortBy, sortDir);
		
	}
	
	// get post by id
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
		
		return ResponseEntity.ok(pService.getPostById(id));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postdto,
											  @PathVariable(name = "id") long id) {
		
		PostDto postResponse = pService.updatePost(postdto, id);
		
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}
	

	@DeleteMapping("/{id}")
		public ResponseEntity<String> updatePost(
												  @PathVariable(name = "id") long id) {
			
			pService.deletePost(id);
			
			return new ResponseEntity<>("postRespons delete", HttpStatus.OK);
		}
	
	
	
	
	

}
