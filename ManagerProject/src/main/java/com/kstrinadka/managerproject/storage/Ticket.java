package com.kstrinadka.managerproject.storage;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Заявка на злом слова по его хешу.
 */
@Data
@RequiredArgsConstructor
public class Ticket
{
    private final UUID ticketId;
    private final String hash;
    private final int maxLength;

    private int tasksNumber;
    private int tasksDone;

    private Status status = Status.IN_PROGRESS;
    private LocalDateTime creationTime = LocalDateTime.now();
    private List<String> result = new ArrayList<>();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Ticket ticket = (Ticket) obj;

        return maxLength == ticket.maxLength &&
                Objects.equals(ticketId, ticket.ticketId) &&
                Objects.equals(hash, ticket.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, hash, maxLength);
    }
}
