package org.javier.rest.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public final class Game {

	private final Map<Long, Door> doors;

	private final Long id;

    private final Object monitor = new Object();

    private volatile GameStatus status;
    
    public Game(Long id, Collection<Door> doors) {

    	this.id = id;
    	
    	this.doors = new HashMap<>();
    	for(Door door : doors){
    		this.doors.put(door.getId(), door);
    	}
    	
    	this.status = GameStatus.AWAITING_INITIAL_SELECTION;
    	
    }
    
    public void transition(Long doorId, DoorStatus status) throws DoorDoesNotExistException,IllegalTransitionException{
    	synchronized (this.monitor) {
		
    		if (DoorStatus.SELECTED == status) {
                select(doorId);
            } else if (DoorStatus.OPENED == status) {
                open(doorId);
            } else {
                throw new IllegalTransitionException(this.id, doorId, status);
            }
    		
		}
    }
    
    @JsonIgnore
    public Long getId() {
        synchronized (this.monitor) {
            return this.id;
        }
    }

    @JsonIgnore
    public Collection<Door> getDoors() {
        return this.doors.values();
    }

    public GameStatus getStatus() {
        synchronized (this.monitor) {
            return this.status;
        }
    }
    
    private Door getDoor(Long doorId) throws DoorDoesNotExistException {
        if (this.doors.containsKey(doorId)) {
            return this.doors.get(doorId);
        }

        throw new DoorDoesNotExistException(this.id, doorId);
    }
    
    private void open(Long doorId) throws IllegalTransitionException, DoorDoesNotExistException {
        if (this.status != GameStatus.AWAITING_FINAL_SELECTION) {
            throw new IllegalTransitionException(this.id, this.status, GameStatus.WON);
        }

        Door door = getDoor(doorId);
        if (DoorStatus.OPENED == door.getStatus()) {
            throw new IllegalTransitionException(this.id, doorId, door.getStatus(), DoorStatus.OPENED);
        }

        door.setStatus(DoorStatus.OPENED);

        if (DoorContent.BICYCLE == door.getContent()) {
            this.status = GameStatus.WON;
        } else {
            this.status = GameStatus.LOST;
        }
    }
    
    private void openHintDoor() {
        for (Door door : getDoors()) {
            if ((DoorStatus.CLOSED == door.getStatus()) && (DoorContent.SMALL_FURRY_ANIMAL == door.peekContent())) {
                door.setStatus(DoorStatus.OPENED);
                break;
            }
        }
    }
    
    private void select(Long doorId) throws IllegalTransitionException, DoorDoesNotExistException {
        if (this.status != GameStatus.AWAITING_INITIAL_SELECTION) {
            throw new IllegalTransitionException(this.id, this.status, GameStatus.AWAITING_FINAL_SELECTION);
        }

        Door door = getDoor(doorId);
        door.setStatus(DoorStatus.SELECTED);

        openHintDoor();

        this.status = GameStatus.AWAITING_FINAL_SELECTION;
    }
    
    
}
