package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;

	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {

		Comment comment = mapToEntity(commentDto);

		// retrieve post entity by id
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		// set post to comment entity
		comment.setPost(post);

		// save comment entity to the database
		Comment newComment = commentRepository.save(comment);

		return mapToDto(newComment);
	}

	@Override
	public List<CommentDto> getCommentsByPostId(long postId) {
		// retrieve comments by postId
		List<Comment> comments = commentRepository.findByPostId(postId);

		// convert list of comment entities to list of comment dto's
		return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {

		// retrieve post entity by id
		Post post = getPostById(postId);

		// retrieve comment by id
		Comment comment = getCommentById(commentId);

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}

		return mapToDto(comment);
	}

	@Override
	public CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest) {

		// retrieve post entity by id
		Post post = getPostById(postId);

		// retrieve comment by id
		Comment comment = getCommentById(commentId);

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
		}

		comment.setName(commentRequest.getName());
		comment.setEmail(commentRequest.getEmail());
		comment.setBody(commentRequest.getBody());

		Comment updateComment = commentRepository.save(comment);
		return mapToDto(updateComment);
	}

	@Override
	public void deleteComment(Long postId, Long commentId) {
		// retrieve post entity by id
		Post post = getPostById(postId);

		// retrieve comment by id
		Comment comment = getCommentById(commentId);

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
		}
//		Comment comment = getPostAndCommentByItsId(postId, commentId);
		commentRepository.delete(comment);
	}

	private Post getPostById(Long postId) {
		return postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
	}

	private Comment getCommentById(Long commentId) {
		return commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
	}

	private CommentDto mapToDto(Comment comment) {
		CommentDto commentDto = new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setName(comment.getName());
		commentDto.setEmail(comment.getEmail());
		commentDto.setBody(comment.getBody());

		return commentDto;
	}

	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment = new Comment();
		comment.setId(commentDto.getId());
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());

		return comment;
	}

}
