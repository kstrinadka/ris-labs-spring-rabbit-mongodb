package com.kstrinadka.managerproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kstrinadka.managerproject.dto.UpdateDTO;
import com.kstrinadka.managerproject.service.impl.ClientService;

@RestController
@RequestMapping("/internal/api/manager/hash/crack/request")
@RequiredArgsConstructor
public class WorkerController
{
    // todo - мб лучше пусть WorkerController дергает WorkerService???
    private final ClientService clientService;

    @PatchMapping
    public void updateTicket(@RequestBody UpdateDTO dto)
    {
        System.out.println("Updating ticket. Ticket id: " + dto.getTicketID());
        System.out.println("Received data: " + dto.getResult());
        clientService.updateTicket(dto);
    }
}
