package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

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
		Post post = mapToEntity(postDto);
		// saving... post
		Post newPost = postRepository.save(post);
		// convert entity to DTO
		PostDto postResponse = mapToDto(newPost);
		return postResponse;
	}

	@Override
	public List<PostDto> getAllPosts() {
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
	}

	// convert Entity into DTO
	private PostDto mapToDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setDescription(post.getDescription());
		postDto.setContent(post.getContent());

		return postDto;
	}

	// convert DTO into Entity
	private Post mapToEntity(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());

		return post;
	}
}

//note: spring v4.3 onwards whenever we configure our class as spring been and that 
//class has only one constructor then we can omit @Autowire annotation
