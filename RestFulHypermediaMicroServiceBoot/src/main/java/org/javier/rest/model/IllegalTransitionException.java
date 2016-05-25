package org.javier.rest.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public final class IllegalTransitionException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	IllegalTransitionException(Long gameId, Long doorId,DoorStatus from, DoorStatus to){
		super(String.format("It is illegal to transition door '%d' in game '%d' from '%s' to '%s'",
                doorId, gameId, from, to));
	}
	
	IllegalTransitionException(Long gameId, Long doorId, DoorStatus to) {
        super(String.format("It is illegal to transition door '%d' in game '%d' to '%s'", doorId, gameId, to));
    }

	IllegalTransitionException(Long gameId, GameStatus from, GameStatus to) {
        super(String.format("It is illegal to transition game '%d' from '%s' to '%s'", gameId, from, to));
    }
}
