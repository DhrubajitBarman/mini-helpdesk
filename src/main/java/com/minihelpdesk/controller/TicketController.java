package com.minihelpdesk.controller;

import com.minihelpdesk.dto.ticket.TicketCreateRequest;
import com.minihelpdesk.dto.ticket.TicketResponse;
import com.minihelpdesk.dto.ticket.TicketAssignRequest;
import com.minihelpdesk.entity.enums.TicketStatus;
import com.minihelpdesk.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

	private final TicketService ticketService;

	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@PostMapping
	public TicketResponse create(@RequestHeader("X-User-Id") Long creatorId,
			@Valid @RequestBody TicketCreateRequest req) {
		return ticketService.create(creatorId, req);
	}

	@GetMapping("/{id}")
	public TicketResponse get(@PathVariable Long id) {
		return ticketService.get(id);
	}

	@GetMapping
	public Page<TicketResponse> list(@RequestParam(required = false) TicketStatus status,
			@RequestParam(required = false) Long assigneeId, @RequestParam(required = false) Long creatorId,
			Pageable pageable) {
		return ticketService.list(status, assigneeId, creatorId, pageable);
	}

	@PostMapping("/{id}/assign")
	public TicketResponse assign(@PathVariable Long id, @Valid @RequestBody TicketAssignRequest req) {
		return ticketService.assign(id, req);
	}

	@PostMapping("/{id}/transition")
	public TicketResponse transition(@PathVariable Long id, @RequestParam TicketStatus status) {
		return ticketService.transition(id, status);
	}
}
