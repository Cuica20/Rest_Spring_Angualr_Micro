package org.javier.rest.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public final class DoorDoesNotExistException extends Exception{

	DoorDoesNotExistException(Long gameId, Long doorId) {
		super(String.format("Door '%d' in game '%d' does not exist", doorId, gameId));
	}
	
}
