package com.minihelpdesk.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.minihelpdesk.dto.comment.CommentCreateRequest;
import com.minihelpdesk.dto.ticket.TicketResponse;
import com.minihelpdesk.entity.Comment;
import com.minihelpdesk.entity.Ticket;
import com.minihelpdesk.entity.User;
import com.minihelpdesk.exception.NotFoundException;
import com.minihelpdesk.mapper.TicketMapper;
import com.minihelpdesk.repository.CommentRepository;
import com.minihelpdesk.repository.TicketRepository;
import com.minihelpdesk.repository.UserRepository;
import com.minihelpdesk.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	private final CommentRepository comments;
	private final TicketRepository tickets;
	private final UserRepository users;

	public CommentServiceImpl(CommentRepository comments, TicketRepository tickets, UserRepository users) {
		this.comments = comments;
		this.tickets = tickets;
		this.users = users;
	}

	@Override
	@Transactional
	public TicketResponse addComment(Long ticketId, Long authorId, CommentCreateRequest req) {
		Ticket ticket = tickets.findById(ticketId).orElseThrow(() -> new NotFoundException("Ticket not found"));
		User author = users.findById(authorId).orElseThrow(() -> new NotFoundException("Author not found"));

		Comment c = new Comment();
		c.setMessage(req.getMessage());
		c.setAuthor(author);
		c.setTicket(ticket);
		comments.save(c);

		return TicketMapper.toResponse(ticket);
	}
}
