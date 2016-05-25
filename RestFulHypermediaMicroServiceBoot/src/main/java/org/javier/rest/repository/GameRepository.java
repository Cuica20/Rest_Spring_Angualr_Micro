package org.javier.rest.repository;

import org.javier.rest.model.Game;

public interface GameRepository {

	Game create();
	
	Game retrieve(Long id) throws GameDoesNotExistException;
	
	void remove(Long id) throws GameDoesNotExistException;
	
}
