package com.minihelpdesk.controller;

import com.minihelpdesk.dto.comment.CommentCreateRequest;
import com.minihelpdesk.dto.ticket.TicketResponse;
import com.minihelpdesk.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/{ticketId}/add/{authorId}")
	public TicketResponse addComment(@PathVariable Long ticketId, @PathVariable Long authorId,
			@RequestBody CommentCreateRequest request) {
		return commentService.addComment(ticketId, authorId, request);
	}
}
