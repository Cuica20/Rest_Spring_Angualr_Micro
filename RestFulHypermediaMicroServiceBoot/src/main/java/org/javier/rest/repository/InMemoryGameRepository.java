package org.javier.rest.repository;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.javier.rest.model.Door;
import org.javier.rest.model.DoorContent;
import org.javier.rest.model.Game;
import org.springframework.stereotype.Component;

@Component
final class InMemoryGameRepository implements GameRepository{

	private final Map<Long, Game> games = new HashMap<>();

    private final AtomicLong idGenerator = new AtomicLong();

    private final Object monitor = new Object();

    private final SecureRandom random = new SecureRandom();
	
	@Override
	public Game create() {
		synchronized (this.monitor) {
            Long id = this.idGenerator.getAndIncrement();
            Game game = new Game(id, createDoors());

            this.games.put(id, game);

            return game;
        }
	}

	@Override
	public Game retrieve(Long id) throws GameDoesNotExistException {
		synchronized (this.monitor) {
            if (!this.games.containsKey(id)) {
                throw new GameDoesNotExistException(id);
            }

            return this.games.get(id);
        }
	}

	@Override
	public void remove(Long id) throws GameDoesNotExistException {
		synchronized (this.monitor) {
            if (!this.games.containsKey(id)) {
                throw new GameDoesNotExistException(id);
            }

            this.games.remove(id);
        }
		
	}
	
	private Collection<Door> createDoors() {
        int winner = this.random.nextInt(3);

        return IntStream.range(0, 3).mapToObj(index -> {
            Long id = this.idGenerator.getAndIncrement();
            DoorContent content = (index == winner) ? DoorContent.BICYCLE : DoorContent.SMALL_FURRY_ANIMAL;
            return new Door(id, content);
        }).collect(Collectors.toList());
    }

}
