package com.kstrinadka.managerproject.storage;

import com.kstrinadka.managerproject.dto.CrackDTO;
import jakarta.annotation.PostConstruct;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.kstrinadka.managerproject.dto.TicketIdDTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class TicketStorage implements Storage {
    private final ConcurrentHashMap<String, Ticket> ticketStorageHashMap = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Object updateLock = new Object();

    @PostConstruct
    public void init() {
        startPeriodicCheck();
    }

    @Synchronized("updateLock")
    private void startPeriodicCheck() {
        scheduler.scheduleAtFixedRate(() -> ticketStorageHashMap.forEach((key, ticket) -> {
            log.info("Checking tickets");

            if (Duration.between(ticket.getCreationTime(), LocalDateTime.now()).getSeconds() > 3600) {
                log.info(String.format("Ticket %s didn't change its status for more than 2 min. Changing status to error!", key));
                ticket.setStatus(Status.ERROR);
            }
        }), 0, 1, TimeUnit.MINUTES);
    }

    @Override
    @Synchronized("updateLock")
    public TicketIdDTO addNewTicket(CrackDTO dto) {
        UUID uuid = UUID.randomUUID();
        Ticket t = new Ticket(uuid, dto.getHash(), dto.getMaxLength());
        ticketStorageHashMap.put(uuid.toString(), t);

        return TicketIdDTO.builder().requestId(uuid.toString())
                .build();
    }

    @Override
    @Synchronized("updateLock")
    public Ticket getTicket(String id) {
        return ticketStorageHashMap.get(id);
    }

    @Override
    @Synchronized("updateLock")
    public void deleteTicket(String id) {
        ticketStorageHashMap.remove(id);
    }

    @Override
    @Synchronized("updateLock")
    public void deleteAllTickets() {
        ticketStorageHashMap.clear();
    }

    //TODO: отслеживать сколкьо воркеров вернуло ответ, чтобы всегда IN_PROGRESS не было
    // todo - ваще дичь какая-то
    @Override
    @Synchronized("updateLock")
    public void updateTicket(String id, List<String> data) {
        if (data == null)
            return;

        Ticket blankTicket = new Ticket(UUID.randomUUID(), "blank", 1);
        ticketStorageHashMap
                .merge(
                        id,
                        blankTicket,
                        ((ticket, ticket1) -> {
                            ticket.setTasksDone(ticket.getTasksDone() + 1);
                            log.info(String.format("Ticket %s: tasks done %d/%d",
                                    ticket.getTicketId().toString(),
                                    ticket.getTasksDone(),
                                    ticket.getTasksNumber())
                            );

                            if (!data.isEmpty() && ticket.getStatus() != Status.ERROR) {
                                ticket.setResult(data);
                                log.info(String.format("Ticket %s was successfully updated!!!",
                                        ticket.getTicketId().toString())
                                );
                            }

                            if (ticket.getTasksNumber() == ticket.getTasksDone()) {
                                ticket.setStatus(Status.DONE);
                                log.info(String.format("Ticket %s was successfully done!",
                                        ticket.getTicketId().toString())
                                );
                            }

                            return ticket;
                        })
                );
    }

    @Override
    @Synchronized("updateLock")
    public int getStorageSize() {
        return ticketStorageHashMap.size();
    }
}
