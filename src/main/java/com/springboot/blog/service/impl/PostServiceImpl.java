package com.springboot.blog.service.impl;

import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;

	public PostServiceImpl(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}

	@Override
	public PostDto createPost(PostDto postDto) {

		// convert DTO to Entity
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());

		// saving... post
		Post newPost = postRepository.save(post);

		// convert entity to DTO
		PostDto postResponse = new PostDto();
		postResponse.setId(newPost.getId());
		postResponse.setTitle(newPost.getTitle());
		postResponse.setDescription(newPost.getDescription());
		postResponse.setContent(newPost.getContent());

		return postDto;
	}

}

//note: spring v4.3 onwards whenever we configure our class as spring been and that 
//class has only one constructor then we can omit @Autowire annotation
