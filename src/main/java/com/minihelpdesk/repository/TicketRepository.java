package com.minihelpdesk.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.minihelpdesk.entity.Ticket;
import com.minihelpdesk.entity.enums.TicketStatus;

public interface TicketRepository extends JpaRepository<Ticket, Long>, JpaSpecificationExecutor<Ticket> {

	List<Ticket> findByStatus(TicketStatus status);

	List<Ticket> findByAssignedToId(Long userId);

	List<Ticket> findByCreatedById(Long userId);
}
