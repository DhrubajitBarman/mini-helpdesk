package com.minihelpdesk.repository.spec;

import java.util.UUID;
import org.springframework.data.jpa.domain.Specification;
import com.minihelpdesk.entity.Ticket;
import com.minihelpdesk.entity.enums.TicketStatus;

public final class TicketSpecifications {
	private TicketSpecifications() {
	}

	public static Specification<Ticket> hasStatus(TicketStatus status) {
		return status == null ? null : (root, q, cb) -> cb.equal(root.get("status"), status);
	}

	public static Specification<Ticket> assignedTo(UUID assigneeId) {
		return assigneeId == null ? null : (root, q, cb) -> cb.equal(root.get("assignedTo").get("id"), assigneeId);
	}

	public static Specification<Ticket> createdBy(UUID creatorId) {
		return creatorId == null ? null : (root, q, cb) -> cb.equal(root.get("createdBy").get("id"), creatorId);
	}

	public static Specification<Ticket> and(Specification<Ticket> a, Specification<Ticket> b) {
		if (a == null)
			return b;
		if (b == null)
			return a;
		return a.and(b);
	}
}
