package telran.java45.forum.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import telran.java45.dto.exceptions.PostNotFoundExeption;
import telran.java45.post.dao.PostRepository;
import telran.java45.post.dto.PostCreateDto;
import telran.java45.post.dto.PostDto;
import telran.java45.post.model.Post;

@Component
@AllArgsConstructor
public class ForumServiceImpl implements ForumService {


	final PostRepository postRepository;
	
	final ModelMapper modelMapper;
	@Override
	public PostDto addPost(String author, PostCreateDto postCreateDto) {

		
		Post post = new Post(postCreateDto.getTitle(), postCreateDto.getContent(), author, LocalDateTime.now(), postCreateDto.getTags(), 0);
//////		post.setId("123");
//		PostDto postDto = new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getDateCreated(), post.getTags(), post.getLikes(), post.getComments());
//		Post post = modelMapper.map(postCreateDto, Post.class);
		PostDto postDto = new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getDateCreated(), post.getTags(), post.getLikes(), post.getComments());
//		PostDto postDto = modelMapper.map(post, PostDto.class);
		postRepository.save(post);
		return postDto;
	}

	@Override
	public PostDto findPostByID(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundExeption(id));
		PostDto postDto = new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getDateCreated(), post.getTags(), post.getLikes(), post.getComments());
		return postDto;
	}

	@Override
	public Integer addLike(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundExeption(id));
		post.addLike(id);
		postRepository.save(post);
		return post.getLikes();
	}

	@Override
	public List<PostDto> findPostsByAuthor(String author) {
		
		return postRepository.findByAuthor(author)
				.map(p -> new PostDto(p.getId(), p.getTitle(), p.getContent(), p.getAuthor(), p.getDateCreated(), p.getTags(), p.getLikes(), p.getComments()))
				.toList();
	}

	@Override
	public PostDto addComment(String message, String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundExeption(id));
		post.addComment(message);
		postRepository.save(post);
		PostDto postDto = new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getDateCreated(), post.getTags(), post.getLikes(), post.getComments());
		return postDto;
	}

	@Override
	public PostDto deletePost(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundExeption(id));
		postRepository.deleteById(id);
		return new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getDateCreated(), post.getTags(), post.getLikes(), post.getComments());
	}

	@Override
	public List<PostDto> findPostsByTags(List<String> tags) {
		
		return postRepository.findByTagsIn(tags)
				.map(p -> new PostDto(p.getId(), p.getTitle(), p.getContent(), p.getAuthor(), p.getDateCreated(), p.getTags(), p.getLikes(), p.getComments()))
				.toList();
	}

	@Override
	public List<PostDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo) {
		
		return postRepository.findPostsByPeriod(dateFrom, dateTo)
				.map(p -> new PostDto(p.getId(), p.getTitle(), p.getContent(), p.getAuthor(), p.getDateCreated(), p.getTags(), p.getLikes(), p.getComments()))
				.toList();
	}

	@Override
	public PostDto updatePost(String id, PostCreateDto postCreateDto) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundExeption(id));
		if(postCreateDto.getContent() != null) {
			post.setContent(postCreateDto.getContent());
		}
		if(postCreateDto.getTags()!=null) {
			post.setTags(postCreateDto.getTags());
		}
		if(postCreateDto.getTitle()!=null) {
			post.setTitle(postCreateDto.getTitle());
		}
		postRepository.save(post);
		
		return new PostDto(id, post.getTitle(), post.getContent(), post.getAuthor(), post.getDateCreated(), post.getTags(), post.getLikes(), post.getComments());
	}







}
