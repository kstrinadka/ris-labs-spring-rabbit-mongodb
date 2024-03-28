package com.kstrinadka.managerproject.service.impl;

import com.kstrinadka.managerproject.dto.CrackDTO;
import com.kstrinadka.managerproject.dto.TicketIdDTO;
import com.kstrinadka.managerproject.exceptions.NoHashException;
import com.kstrinadka.managerproject.exceptions.NoMaxLengthException;
import com.kstrinadka.managerproject.storage.Status;
import com.kstrinadka.managerproject.storage.Ticket;
import com.kstrinadka.managerproject.storage.TicketStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import com.kstrinadka.managerproject.dto.ResultDTO;
import com.kstrinadka.managerproject.dto.UpdateDTO;

@Service
public class ClientService
{
    private final TicketStorage ticketStorage;
    private final MongoTemplate mongoTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    public ClientService(TicketStorage ticketStorage, MongoTemplate mongoTemplate) {
        this.ticketStorage = ticketStorage;
        this.mongoTemplate = mongoTemplate;
    }

    public TicketIdDTO processRequest(CrackDTO dto) {
        if (dto.getHash() == null) {
            throw new NoHashException();
        }
        if (dto.getMaxLength() == 0) {
            throw new NoMaxLengthException();
        }

        var task = ticketStorage.addNewTicket(dto);
        logger.info("New ticket registered!");

        mongoTemplate.save(task);

        return new TicketIdDTO(task.getTicketId().toString());
    }

    public ResultDTO getData(String id) {
        Ticket ticket = ticketStorage.getTicket(id);
        switch (ticket.getStatus()) {
            case DONE -> {
                var res = ticket.getResult();
                ticketStorage.deleteTicket(id);

                return new ResultDTO(Status.DONE, res);
            }
            case IN_PROGRESS -> {
                return new ResultDTO(Status.IN_PROGRESS, null);
            }
            case ERROR -> {
                return new ResultDTO(Status.ERROR, null);
            }
        }

        return null;
    }

    public void updateTicket(UpdateDTO dto) {
        ticketStorage.updateTicket(dto.getTicketID(), dto.getResult());
    }
}
