package org.javier.rest.repository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public final class GameDoesNotExistException extends Exception{

	
	public GameDoesNotExistException(Long id) {

		super(String.format("Game '%d' does not exist", id));
		
	}
	
}
