package com.minihelpdesk.mapper;

import com.minihelpdesk.dto.ticket.TicketResponse;
import com.minihelpdesk.entity.Ticket;

public final class TicketMapper {
	private TicketMapper() {
	}

	public static TicketResponse toResponse(Ticket t) {
		TicketResponse r = new TicketResponse();
		r.setId(t.getId());
		r.setTitle(t.getTitle());
		r.setDescription(t.getDescription());
		r.setStatus(t.getStatus() != null ? t.getStatus().name() : null);
		r.setPriority(t.getPriority() != null ? t.getPriority().name() : null);
		r.setCreatedById(t.getCreatedBy() != null ? t.getCreatedBy().getId() : null);
		r.setAssignedToId(t.getAssignedTo() != null ? t.getAssignedTo().getId() : null);
		r.setCreatedAt(t.getCreatedAt());
		r.setUpdatedAt(t.getUpdatedAt());
		return r;
	}
}
