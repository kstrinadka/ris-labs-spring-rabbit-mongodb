package com.kstrinadka.managerproject.service.impl;

import com.kstrinadka.managerproject.dto.CrackDTO;
import com.kstrinadka.managerproject.dto.ResultDTO;
import com.kstrinadka.managerproject.exceptions.NoHashException;
import com.kstrinadka.managerproject.exceptions.NoMaxLengthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.kstrinadka.managerproject.dto.TicketIdDTO;
import com.kstrinadka.managerproject.dto.UpdateDTO;
import com.kstrinadka.managerproject.storage.Status;
import com.kstrinadka.managerproject.storage.Ticket;
import com.kstrinadka.managerproject.storage.TicketStorage;

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

        TicketIdDTO ticketIdDTO = ticketStorage.addNewTicket(dto);
        log.info("New ticket registered!");

        return ticketIdDTO;
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
