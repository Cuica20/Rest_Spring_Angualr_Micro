package org.javier.rest.model;

import java.util.Map;

public enum DoorStatus {
	
	CLOSED,
	OPENED,
	SELECTED;
	
	private static final String STATUS_KEY = "status";
	
	public static DoorStatus parse(Map<String, String> payload){
		String value = payload.get(STATUS_KEY);

		if(value == null) {
			throw new IllegalArgumentException("Payload is missing key status");
		}
		
		try {
			return DoorStatus.valueOf(value.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(
					String.format("'%s' is an illegal value for key '%s'", value, STATUS_KEY), e);
		}
	}

}
