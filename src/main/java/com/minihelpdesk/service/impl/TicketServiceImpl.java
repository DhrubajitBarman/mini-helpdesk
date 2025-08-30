package com.minihelpdesk.service.impl;

import com.minihelpdesk.dto.ticket.TicketAssignRequest;
import com.minihelpdesk.dto.ticket.TicketCreateRequest;
import com.minihelpdesk.dto.ticket.TicketResponse;
import com.minihelpdesk.entity.Ticket;
import com.minihelpdesk.entity.User;
import com.minihelpdesk.entity.enums.TicketStatus;
import com.minihelpdesk.exception.NotFoundException;
import com.minihelpdesk.mapper.TicketMapper;
import com.minihelpdesk.repository.TicketRepository;
import com.minihelpdesk.repository.UserRepository;
import com.minihelpdesk.service.TicketService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public TicketResponse create(Long creatorId, TicketCreateRequest req) {
        User creator = userRepository.findById(creatorId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Ticket ticket = new Ticket();
        ticket.setTitle(req.getTitle());
        ticket.setDescription(req.getDescription());
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setPriority(req.getPriority());
        ticket.setCreatedBy(creator);

        Ticket saved = ticketRepository.save(ticket);
        return TicketMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public TicketResponse get(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ticket not found"));
        return TicketMapper.toResponse(ticket);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TicketResponse> list(TicketStatus status, Long assigneeId, Long creatorId, Pageable pageable) {
        return ticketRepository.findAll(pageable)
                .map(TicketMapper::toResponse);
    }

    @Override
    @Transactional
    public TicketResponse assign(Long ticketId, TicketAssignRequest req) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NotFoundException("Ticket not found"));

        User agent = userRepository.findById(req.getAssigneeId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        ticket.setAssignedTo(agent);
        Ticket saved = ticketRepository.save(ticket);
        return TicketMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public TicketResponse transition(Long ticketId, TicketStatus newStatus) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NotFoundException("Ticket not found"));

        ticket.setStatus(newStatus);
        Ticket saved = ticketRepository.save(ticket);
        return TicketMapper.toResponse(saved);
    }
}
