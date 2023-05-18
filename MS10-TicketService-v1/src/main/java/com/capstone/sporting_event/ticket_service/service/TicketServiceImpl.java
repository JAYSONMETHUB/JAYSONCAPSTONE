package com.capstone.sporting_event.ticket_service.service;

import com.capstone.sporting_event.ticket_service.dto.TicketMapper;
import com.capstone.sporting_event.ticket_service.dto.TicketRequestDTO;
import com.capstone.sporting_event.ticket_service.entity.Ticket;
import com.capstone.sporting_event.ticket_service.exceptions.TicketAlreadyExistException;
import com.capstone.sporting_event.ticket_service.exceptions.TicketNotFoundException;
import com.capstone.sporting_event.ticket_service.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepo;

    @Autowired
    TicketMapper ticketMapper;

    public Ticket saveTicket(TicketRequestDTO ticketDTO){



       return ticketRepo.save(ticketMapper.toEntity(ticketDTO));
    }


    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }



    public Ticket getTicket(int id) {

        Optional<Ticket> ticket = ticketRepo.findById(id);

        if(ticket.isEmpty()){
            throw new TicketNotFoundException();
        }

        return ticket.get();
    }


    public Ticket updateTicket(int id, TicketRequestDTO ticketDTO){

        Optional<Ticket> ticket = ticketRepo.findById(id);

        if(ticket.isEmpty()){
            throw new TicketNotFoundException();
        }

        Ticket updatedTicket = ticket.get();
        updatedTicket.setTicketPrice(ticketDTO.getTicketPrice());
        updatedTicket.setCustomerName(ticketDTO.getCustomerName());
        updatedTicket.setMatchId(ticketDTO.getMatchId());

        return ticketRepo.save(updatedTicket);
    }

    public void deleteTicket(int id) {
        Optional<Ticket> ticket = ticketRepo.findById(id);

        if(ticket.isEmpty()) {
            throw new TicketNotFoundException();
        }

        Ticket deletedTicket = ticket.get();

        deletedTicket.setActive(false);
        ticketRepo.save(deletedTicket);
    }


}
