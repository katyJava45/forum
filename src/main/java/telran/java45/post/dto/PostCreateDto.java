package telran.java45.post.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class PostCreateDto {
	String title;
	String content;
	List<String> tags;
}
