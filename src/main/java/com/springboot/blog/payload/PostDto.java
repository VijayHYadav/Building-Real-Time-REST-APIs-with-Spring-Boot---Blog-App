package com.springboot.blog.payload;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostDto {
	private Long id;

	// title should not be null or empty
	// title should have at least 2 characters
	@NotEmpty
	@Size(min = 2, message = "Post title should have at least 2 characters")
	private String title;

	// post description should be null or empty
	// post description should have at least 10 characters
	@NotEmpty
	@Size(min = 10, message = "Post description should have at least 10 characters")
	private String description;

	// post content should not be null or empty
	@NotEmpty
	private String content;
	private Set<CommentDto> comments;
}
