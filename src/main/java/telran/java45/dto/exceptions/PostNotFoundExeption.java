package telran.java45.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostNotFoundExeption extends RuntimeException {

	private static final long serialVersionUID = -5538738419262048782L;
	public PostNotFoundExeption() {}
	
	public PostNotFoundExeption(String id) {
		super("post with id " + id + " not found");
	}
}
