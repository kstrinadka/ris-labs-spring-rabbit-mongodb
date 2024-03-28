package com.kstrinadka.managerproject.controller;

import com.kstrinadka.managerproject.service.impl.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kstrinadka.managerproject.dto.UpdateDTO;

@RestController
@RequestMapping("/internal/api/manager/hash/crack/request")
@Slf4j
public class WorkerController
{
    private final ClientService clientService;

    @Autowired
    public WorkerController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PatchMapping
    public void updateTicket(@RequestBody UpdateDTO dto)
    {
        log.info("Updating ticket. Ticket id: "+dto.getTicketID()+" data: "+dto.getResult());

        clientService.updateTicket(dto);
    }
}
