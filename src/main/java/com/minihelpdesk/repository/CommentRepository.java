package com.minihelpdesk.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.minihelpdesk.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByTicketId(Long ticketId);

	List<Comment> findByAuthorId(Long authorId);
}
