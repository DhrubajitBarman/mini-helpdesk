package com.minihelpdesk.service;

import com.minihelpdesk.dto.ticket.TicketCreateRequest;
import com.minihelpdesk.dto.ticket.TicketResponse;
import com.minihelpdesk.dto.ticket.TicketAssignRequest;
import com.minihelpdesk.entity.enums.TicketStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketService {

	TicketResponse create(Long creatorId, TicketCreateRequest req);

	TicketResponse get(Long id);

	Page<TicketResponse> list(TicketStatus status, Long assigneeId, Long creatorId, Pageable pageable);

	TicketResponse assign(Long ticketId, TicketAssignRequest req);

	TicketResponse transition(Long ticketId, TicketStatus newStatus);
}
