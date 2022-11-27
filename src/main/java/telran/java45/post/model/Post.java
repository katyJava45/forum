package telran.java45.post.model;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Post {
	@Id
	String id;
	@Setter
	String title;
	@Setter
	String content;
	String author;
	LocalDateTime dateCreated;
	@Setter
	List<String> tags;
	@Setter
	Integer likes;
	@Setter
	List<String> comments;
	public Post( String title, String content, String author, LocalDateTime dateCreated, List<String> tags, Integer likes) {
		super();
	
		this.title = title;
		this.content = content;
		this.author = author;
		this.dateCreated = LocalDateTime.now();
		this.tags = tags;
		this.likes = 0;
	}
	
	public Integer addLike(String id) {
		return likes++;
	}
	
	public boolean addComment(String comment) {
		
		return comments.add(comment);
	}
	
	
}
