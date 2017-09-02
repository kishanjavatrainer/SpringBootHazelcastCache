package com.infotech.book.ticket.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.infotech.book.ticket.app.dao.TicketBookingDao;
import com.infotech.book.ticket.app.entities.Ticket;

@Service
public class TicketBookingService {

	@Autowired
	private TicketBookingDao ticketBookingDao;
	
	@Cacheable(value="ticketsCache",key="#ticketId",unless="#result==null")
	public Ticket getTicketById(Integer ticketId) {
		return ticketBookingDao.findOne(ticketId);
	}
	
	@CacheEvict(value="ticketsCache",key="#ticketId")
	public void deleteTicket(Integer ticketId) {
		ticketBookingDao.delete(ticketId);
	}
	
	@CachePut(value="ticketsCache",key="#ticketId")
	public Ticket updateTicket(Integer ticketId, String newEmail) {
		Ticket upadedTicket = null;
		Ticket ticketFromDb = ticketBookingDao.findOne(ticketId);
		if(ticketFromDb != null){
			ticketFromDb.setEmail(newEmail);
			upadedTicket = ticketBookingDao.save(ticketFromDb);	
		}
		return upadedTicket;
	}
}
