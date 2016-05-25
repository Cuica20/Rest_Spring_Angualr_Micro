package org.javier.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public final class Door {

	private final DoorContent content;

    private final Long id;

    private final Object monitor = new Object();
    
    private volatile DoorStatus status = DoorStatus.CLOSED;
    
    public Door(Long id, DoorContent content) {
        this.id = id;
        this.content = content;
    }
    
    @JsonIgnore
    public Long getId() {
        return this.id;
    }
    
    public DoorContent getContent() {
        synchronized (this.monitor) {
            if (this.status == DoorStatus.OPENED) {
                return this.content;
            }
            return DoorContent.UNKNOWN;
        }
    }
    
    public DoorStatus getStatus() {
        synchronized (this.monitor) {
            return this.status;
        }
    }

    DoorContent peekContent() {
        return this.content;
    }

    void setStatus(DoorStatus status) {
        synchronized (this.monitor) {
            this.status = status;
        }
    }
}
