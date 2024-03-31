package ru.nsu.fit.g20202.vartazaryan.managerproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.g20202.vartazaryan.managerproject.dto.CrackDTO;
import ru.nsu.fit.g20202.vartazaryan.managerproject.dto.ResultDTO;
import ru.nsu.fit.g20202.vartazaryan.managerproject.dto.TicketIdDTO;
import ru.nsu.fit.g20202.vartazaryan.managerproject.service.impl.ClientService;
import ru.nsu.fit.g20202.vartazaryan.managerproject.service.impl.WorkerService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/hash")
@RequiredArgsConstructor
@Slf4j
public class ClientController
{
    private final ClientService clientService;
    private final WorkerService workerService;

    @PostMapping("/crack")
    public ResponseEntity<TicketIdDTO> crackHash(@RequestBody CrackDTO crackDTO) {
        log.info("New crack hash request!");
        var ticketIdDTO = clientService.processRequest(crackDTO);
        log.info("Registered new ticket. ID = " + ticketIdDTO.getRequestId());

        workerService.handleTicket(ticketIdDTO.getRequestId());

        return ResponseEntity.ok().body(ticketIdDTO);
    }

    @GetMapping("/status")
    public ResponseEntity<ResultDTO> getStatus(@RequestParam(name = "requestId") String id) {

        return ResponseEntity.ok().body(clientService.getData(id));
    }
}
