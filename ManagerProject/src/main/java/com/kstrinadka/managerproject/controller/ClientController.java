package com.kstrinadka.managerproject.controller;

import com.kstrinadka.managerproject.dto.TicketIdDTO;
import com.kstrinadka.managerproject.service.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kstrinadka.managerproject.dto.CrackDTO;
import com.kstrinadka.managerproject.dto.ResultDTO;
import com.kstrinadka.managerproject.service.ClientService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/hash")
public class ClientController
{
    private final ClientService clientService;
    private final WorkerService workerService;
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    public ClientController(ClientService clientService, WorkerService workerService)
    {
        this.clientService = clientService;
        this.workerService = workerService;
    }

    @PostMapping("/crack")
    public ResponseEntity<TicketIdDTO> crackHash(@RequestBody CrackDTO crackDTO)
    {
        logger.info("New crack hash request!");

        var ticketIdDTO = clientService.processRequest(crackDTO);

        logger.info("Registered new ticket. ID = "+ticketIdDTO.getRequestId());

        workerService.handleTicket(ticketIdDTO.getRequestId());

        return ResponseEntity.ok().body(ticketIdDTO);
    }

    @GetMapping("/status")
    public ResponseEntity<ResultDTO> getStatus(@RequestParam(name = "requestId") String id)
    {
        return ResponseEntity.ok().body(clientService.getData(id));
    }
}
