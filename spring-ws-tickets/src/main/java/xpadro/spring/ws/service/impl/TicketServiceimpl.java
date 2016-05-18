package xpadro.spring.ws.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import xpadro.spring.ws.model.TicketConfirmation;
import xpadro.spring.ws.service.TicketService;

@Service
public class TicketServiceimpl implements TicketService {

	@Override
	public TicketConfirmation order(String filmId, Date sessionDate, int quantity) {
		float amount = 5.95f * quantity;
		TicketConfirmation confirmation = new TicketConfirmation(filmId, sessionDate, quantity, amount);
		
		return confirmation;
	}
}
