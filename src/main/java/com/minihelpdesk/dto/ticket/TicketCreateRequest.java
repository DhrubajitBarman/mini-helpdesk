package com.minihelpdesk.dto.ticket;

import com.minihelpdesk.entity.enums.TicketPriority;

public class TicketCreateRequest {

	private String title;
	private String description;
	private TicketPriority priority;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TicketPriority getPriority() {
		return priority;
	}

	public void setPriority(TicketPriority priority) {
		this.priority = priority;
	}
}
