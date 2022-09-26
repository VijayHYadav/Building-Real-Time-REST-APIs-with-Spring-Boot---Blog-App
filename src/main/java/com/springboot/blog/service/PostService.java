package com.springboot.blog.service;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;

public interface PostService {

	/**
	 * This is used to create post
	 * 
	 * @param postDto
	 * @return PostDto
	 */
	PostDto createPost(PostDto postDto);

	/**
	 * This is used to create post
	 * 
	 * @param postDto
	 * @return PostDto
	 */
	PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

	PostDto getPostById(long id);

	PostDto updatePost(PostDto postDto, long id);

	void deletePostById(long id);
}
