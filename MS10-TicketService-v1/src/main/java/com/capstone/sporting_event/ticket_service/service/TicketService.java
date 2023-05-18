package com.capstone.sporting_event.ticket_service.service;

import com.capstone.sporting_event.ticket_service.dto.TicketRequestDTO;
import com.capstone.sporting_event.ticket_service.entity.Ticket;
import com.capstone.sporting_event.ticket_service.exceptions.TicketAlreadyExistException;
import com.capstone.sporting_event.ticket_service.exceptions.TicketNotFoundException;

import java.util.List;

public interface TicketService {
    public Ticket saveTicket(TicketRequestDTO ticketDTO) throws TicketAlreadyExistException;

    public List<Ticket> getAllTickets();
    public Ticket getTicket(int id) throws TicketNotFoundException;

    public Ticket updateTicket(int id, TicketRequestDTO ticketDTO) throws TicketNotFoundException;

    public void deleteTicket(int id) throws TicketNotFoundException;

}
