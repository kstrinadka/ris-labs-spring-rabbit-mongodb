package com.kstrinadka.workerproject.service.impl;

import com.kstrinadka.workerproject.service.utils.Task;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.kstrinadka.workerproject.dto.TaskDTO;
import com.kstrinadka.workerproject.net.ManagerSender;
import com.kstrinadka.workerproject.service.WorkerService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class WorkerServiceImpl implements WorkerService
{
    private final ManagerSender managerSender;
    private final ExecutorService executorService;
    private static final Logger logger = LoggerFactory.getLogger(WorkerServiceImpl.class);

    public WorkerServiceImpl(@Qualifier("rabbitMQManagerSender") ManagerSender managerSender)
    {
        this.managerSender = managerSender;
        this.executorService = Executors.newFixedThreadPool(5);
    }

    @Override
    public void handleTask(TaskDTO taskDTO, Channel channel, long tag)
    {
        executorService.submit(() -> {
            var id = taskDTO.getTicketID();
            Task newTask = new Task(taskDTO);

            var res = newTask.run();
            managerSender.send(res, id, channel, tag);
        });

        logger.info("Task execution started...");
    }
}
