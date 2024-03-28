package com.kstrinadka.managerproject.net;

import com.kstrinadka.managerproject.dto.TaskDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQWorkerSender implements WorkerSender
{
    private final RabbitTemplate rabbitTemplate;
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQWorkerSender.class);

    @Autowired
    public RabbitMQWorkerSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public int sendTaskToWorker(TaskDTO task, int worker)
    {
        logger.info("Sending task to queue...");

        rabbitTemplate.convertAndSend("exchange","worker_routing_key", task);

        logger.info("Task was sent!");

        return 0;
    }
}
