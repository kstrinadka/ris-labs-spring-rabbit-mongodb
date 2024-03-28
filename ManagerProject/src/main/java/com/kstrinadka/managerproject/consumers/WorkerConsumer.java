package com.kstrinadka.managerproject.consumers;

import com.kstrinadka.managerproject.service.impl.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.kstrinadka.managerproject.dto.UpdateDTO;

@Component
@EnableRabbit
public class WorkerConsumer
{
    private final ClientService clientService;
    private static final Logger logger = LoggerFactory.getLogger(WorkerConsumer.class);

    public WorkerConsumer(ClientService clientService) {
        this.clientService = clientService;
    }

    @RabbitListener(queues = "manager_queue")
    public void handleWorkerResponse(UpdateDTO dto)
    {
        logger.info("Updating ticket. Ticket id: "+dto.getTicketID()+" data: "+dto.getResult());
        clientService.updateTicket(dto);
    }
}
