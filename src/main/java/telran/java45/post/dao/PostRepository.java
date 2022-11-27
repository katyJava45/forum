package telran.java45.post.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import telran.java45.post.model.Post;

public interface PostRepository extends CrudRepository<Post, String> {
	Stream<Post> findByAuthor(String Author);
	
	Stream<Post> findByTagsIn(List<String> tags);
	
	@Query("{post.dateCreated : {$gte:?1},{$lt:?2}")
	Stream<Post> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo);
}
