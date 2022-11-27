package telran.java45.forum.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import telran.java45.forum.service.ForumService;
import telran.java45.post.dto.PostCreateDto;
import telran.java45.post.dto.PostDto;

@RestController
@AllArgsConstructor
public class ForumController {

	
	final ForumService forumService;
	
	@PostMapping("/forum/post/{author}")
	public PostDto addPost(@PathVariable String author, @RequestBody  PostCreateDto postCreateDto) {
		return forumService.addPost(author, postCreateDto);
	}
	
	@GetMapping("/forum/post/{id}")
	public PostDto findPostByID(@PathVariable String id) {
		return forumService.findPostByID(id);		
	}
	
	@PutMapping("/forum/post/{id}/like")
	public Integer addLike(@PathVariable String id) {
		return forumService.addLike(id);
	}
	
	@GetMapping("/forum/posts/author/{author}")
	public List<PostDto> findPostsByAuthor(@PathVariable String author){
		return forumService.findPostsByAuthor(author);
	}
	
	@PutMapping("/forum/post/{id}/comment/{author}")
	public PostDto addComment(@RequestBody String message,@PathVariable String id) {
		return forumService.addComment(message, id);
	}
	
	@DeleteMapping("/forum/post/{id}")
	public PostDto deletePost(@PathVariable String id) {
		return forumService.deletePost(id);
	}
	
	@PostMapping("/forum/posts/tags")
	public List<PostDto> findPostsByTags(@RequestBody List<String> tags) {
		return forumService.findPostsByTags(tags);
	}
	
	@PostMapping("forum/posts/period")
	public List<PostDto> findPostsByPeriod(@RequestBody LocalDate dateFrom, @RequestBody LocalDate dateTo){
		return forumService.findPostsByPeriod(dateFrom, dateTo);
	}
	
	@PutMapping("/forum/post/{id}")
	public PostDto updatePost(@PathVariable String id,@RequestBody PostCreateDto postCreateDto) {
		return forumService.updatePost(id, postCreateDto);
	}
}
