package ru.nsu.fit.g20202.vartazaryan.managerproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.fit.g20202.vartazaryan.managerproject.dto.CrackDTO;
import ru.nsu.fit.g20202.vartazaryan.managerproject.dto.TicketIdDTO;
import ru.nsu.fit.g20202.vartazaryan.managerproject.dto.ResultDTO;
import ru.nsu.fit.g20202.vartazaryan.managerproject.dto.UpdateDTO;
import ru.nsu.fit.g20202.vartazaryan.managerproject.exceptions.NoHashException;
import ru.nsu.fit.g20202.vartazaryan.managerproject.exceptions.NoMaxLengthException;
import ru.nsu.fit.g20202.vartazaryan.managerproject.storage.Status;
import ru.nsu.fit.g20202.vartazaryan.managerproject.storage.Ticket;
import ru.nsu.fit.g20202.vartazaryan.managerproject.storage.TicketStorage;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final TicketStorage ticketStorage;

    public TicketIdDTO processRequest(CrackDTO dto) {
        if (dto.getHash() == null)
            throw new NoHashException();
        if (dto.getMaxLength() == 0)
            throw new NoMaxLengthException();
        String taskId = ticketStorage.addNewTicket(dto);
        log.info("New ticket registered!");

        return new TicketIdDTO(taskId);
    }

    public ResultDTO getData(String id) {
        Ticket ticket = ticketStorage.getTicket(id);

        switch (ticket.getStatus()) {
            case DONE -> {
                ticketStorage.deleteTicket(id);

                return ResultDTO.builder().status(Status.DONE).data(ticket.getResult())
                        .build();
            }
            case IN_PROGRESS -> {

                return ResultDTO.builder().status(Status.IN_PROGRESS).data(null)
                        .build();
            }
            case ERROR -> {

                return ResultDTO.builder().status(Status.ERROR).data(null)
                        .build();
            }
        }

        return null;
    }

    public void updateTicket(UpdateDTO dto) {
        ticketStorage.updateTicket(dto.getTicketID(), dto.getResult());
    }
}
