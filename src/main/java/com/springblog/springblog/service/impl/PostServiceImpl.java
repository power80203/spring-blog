package com.springblog.springblog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springblog.springblog.entity.Post;
import com.springblog.springblog.exception.ResourceNotFoundException;
import com.springblog.springblog.payload.PostDto;
import com.springblog.springblog.payload.PostResopnse;
import com.springblog.springblog.repository.PostRepository;
import com.springblog.springblog.service.PostService;
import com.springblog.springblog.payload.PostResopnse;

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;
	
	private ModelMapper mapper;
	

	public PostServiceImpl(com.springblog.springblog.repository.PostRepository postRepository,
			ModelMapper mapper) {
		super();
		this.postRepository = postRepository;
		this.mapper = mapper;
	}


	@Override
	public PostDto createPost(PostDto postDto) {
		
		//DTO to entity
		Post post = mapToEntity(postDto);
		
		// Save to Db
		Post newPost =postRepository.save(post);
		
		
		//entity to DTO
		PostDto postRes = mapToDto(newPost);
		
		return postRes;
	}


	@Override
	public PostResopnse getAllPosts(int pageNo, int pageSize, String sortyBy, String sortyDir) {
		
		
		// 假設sort 等於 asc就順排 否則逆排
		Sort sort = sortyDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortyBy).ascending()
				: Sort.by(sortyBy).descending();
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		Page<Post> posts = postRepository.findAll(pageable);
		
		
		List<Post> listOfPosts = posts.getContent();
		
		
		//List<Post> posts = postRepository.findAll();
		
		// java  8 的語法
				// 針對傳入的posts進行maptoDto 然後再把資料轉換為list回傳
				//Stream.collect()的輸入參數為Collector，可使用java.util.stream.Collectors.toList()，此方法會將filter後的Stream中的元素放入新的ArrayList中。
		List<PostDto> contentDtos = listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
		
		
		PostResopnse psPostResopnse = new PostResopnse();
		psPostResopnse.setContent(contentDtos);
		psPostResopnse.setPageNo(posts.getNumber());// Page長度
		psPostResopnse.setPageSize(posts.getSize());
		psPostResopnse.setTotalElements(posts.getTotalElements());
		psPostResopnse.setTotalPages(posts.getTotalPages());
		psPostResopnse.setLast(posts.isLast());
		
		
		
		
		return psPostResopnse;
		}
	
	// convert entity to Dto
	
	private PostDto mapToDto(Post post) {
		
//		PostDto postdto = new PostDto();
//		postdto.setId(Post.getId());
//		postdto.setContent(Post.getContent());
//		postdto.setTitle(Post.getTitle());
//		postdto.setDescription(Post.getDescription());
		
		//引入mapper之後的寫法
		PostDto postdto = mapper.map(post, PostDto.class);


		return postdto;
	}
	
	private Post mapToEntity(PostDto postDto) {
		
//		Post post = new Post();
//		post.setTitle(postDto.getTitle());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());
		
		//引入mapper之後的寫法
		Post post = mapper.map(postDto, Post.class);
		
		return post;
	}


	@Override
	public PostDto getPostById(Long id) {
		
		Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
				
		
		return mapToDto(post);
	}


	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
				
		
		
		postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
		Post post = mapToEntity(postDto);
		post.setId(id);
		postRepository.save(post);			
				
		return mapToDto(post);
	}


	@Override
	public void deletePost(Long id) {
		
		Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
		
		postRepository.delete(post);
		
	}
	


}
