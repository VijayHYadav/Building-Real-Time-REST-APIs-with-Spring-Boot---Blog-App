package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.payload.CommentDto;

public interface CommentService {
	/**
	 * Use to create post.
	 * 
	 * @param postId
	 * @param commentDto
	 * @return CommentDto
	 */
	CommentDto createComment(long postId, CommentDto commentDto);

	/**
	 * Use to get comments by post id.
	 * 
	 * @param postId
	 * @return List<CommentDto>
	 */
	List<CommentDto> getCommentsByPostId(long postId);

	/**
	 * Use to get comments by post id and comment id
	 * 
	 * @param postId
	 * @param commentId
	 * @return CommentDto
	 */
	CommentDto getCommentById(Long postId, Long commentId);

	/**
	 * Use to update comment
	 * 
	 * @param postId
	 * @param commentId
	 * @param commentRequest
	 * @return CommentDto
	 */
	CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest);

	/**
	 * Use to delete comment
	 * 
	 * @param postId
	 * @param commentId
	 */
	void deleteComment(Long postId, Long commentId);
}
