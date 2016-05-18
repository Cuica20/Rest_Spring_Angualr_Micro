package xpadro.spring.ws.service;

import java.util.Date;

import xpadro.spring.ws.model.TicketConfirmation;

public interface TicketService {

	public TicketConfirmation order(String filmId, Date sessionDate, int quantity);
}
