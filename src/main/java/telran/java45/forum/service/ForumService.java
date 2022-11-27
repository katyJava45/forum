package telran.java45.forum.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import telran.java45.post.dto.PostCreateDto;
import telran.java45.post.dto.PostDto;

public interface ForumService {
	PostDto addPost(String author, PostCreateDto postCreateDto);
	
	PostDto findPostByID(String id);
	
	Integer addLike(String id);
	
	List<PostDto> findPostsByAuthor(String author);
	
	PostDto addComment(String message, String id);
	
	PostDto deletePost(String id);
	
	List<PostDto> findPostsByTags(List<String> tags);
	
	List<PostDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo);
	
	PostDto updatePost(String id, PostCreateDto postCreateDto);


}
