package com.capstone.sporting_event.ticket_service.repository;

import com.capstone.sporting_event.ticket_service.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {



}