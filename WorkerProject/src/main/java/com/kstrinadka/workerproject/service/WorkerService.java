package com.kstrinadka.workerproject.service;

import com.rabbitmq.client.Channel;
import com.kstrinadka.workerproject.dto.TaskDTO;

public interface WorkerService
{
    void handleTask(TaskDTO taskDTO, Channel channel, long tag);
}
