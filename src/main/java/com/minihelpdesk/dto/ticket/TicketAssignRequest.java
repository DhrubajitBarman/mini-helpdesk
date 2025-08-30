package com.minihelpdesk.dto.ticket;

public class TicketAssignRequest {

	private Long ticketId;
	private Long assigneeId;

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Long getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(Long assigneeId) {
		this.assigneeId = assigneeId;
	}
}
