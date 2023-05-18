package com.capstone.sporting_event.ticket_service.controller;

import com.capstone.sporting_event.ticket_service.dto.TicketDisplayDTO;
import com.capstone.sporting_event.ticket_service.dto.TicketMapper;
import com.capstone.sporting_event.ticket_service.dto.TicketRequestDTO;
import com.capstone.sporting_event.ticket_service.models.TicketResponse;
import com.capstone.sporting_event.ticket_service.models.TicketResponseWithBody;
import com.capstone.sporting_event.ticket_service.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.capstone.sporting_event.ticket_service.constants.Mapping.VERSION_1;
import static com.capstone.sporting_event.ticket_service.constants.MessageMap.*;


@RestController
@RequestMapping(value=VERSION_1)
public class TicketController {
    
    @Autowired
    TicketService ticketService;
    @Autowired
    TicketMapper ticketMapper;

    @GetMapping("/ticket/id/{ticketId}")
    public TicketDisplayDTO getTicketById(@PathVariable int ticketId) {
        return ticketMapper.toDisplayDTO(ticketService.getTicket(ticketId));
    }



    @GetMapping("/tickets")
    public List<TicketDisplayDTO> getTickets() {
        return ticketService.getAllTickets().stream().map(user -> ticketMapper.toDisplayDTO(user)).collect(Collectors.toList());
    }


    @PostMapping("/ticket")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TicketResponseWithBody> addTicket(@RequestBody TicketRequestDTO ticketDTO){

        TicketResponseWithBody saveTicketResponse = TicketResponseWithBody.builder()
                .status(201)
                .message(SAVE_TICKET_SUCCESS)
                .body(ticketMapper.toDisplayDTO(ticketService.saveTicket(ticketDTO)))
                .build();



        return new ResponseEntity<>(saveTicketResponse, HttpStatus.CREATED);
    }

    @PutMapping("/ticket/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TicketResponseWithBody> updateTicket(@PathVariable int ticketId, @Valid @RequestBody TicketRequestDTO ticketRequestDTO){

        TicketResponseWithBody updateTicketResponse = TicketResponseWithBody.builder()
                .status(204)
                .message(UPDATE_TICKET_SUCCESS)
                .body(ticketMapper.toDisplayDTO(ticketService.updateTicket(ticketId, ticketRequestDTO)))
                .build();

        return new ResponseEntity<>(updateTicketResponse, HttpStatus.OK);
    }

    @DeleteMapping("/ticket/{ticketId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<TicketResponse> deleteTicket(@PathVariable("ticketId") int ticketId){

        ticketService.deleteTicket(ticketId);

        TicketResponse deleteTicketResponse = TicketResponse.builder()
                .status(202)
                .message(DELETE_TICKET_SUCCESS)
                .build();

        return new ResponseEntity<>(deleteTicketResponse, HttpStatus.ACCEPTED);
    }

}
