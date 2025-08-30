package com.minihelpdesk.service;

import com.minihelpdesk.dto.comment.CommentCreateRequest;
import com.minihelpdesk.dto.ticket.TicketResponse;

public interface CommentService {
	TicketResponse addComment(Long ticketId, Long authorId, CommentCreateRequest req);
}
