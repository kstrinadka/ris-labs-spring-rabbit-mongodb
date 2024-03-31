package com.kstrinadka.workerproject.service;

import com.kstrinadka.workerproject.dto.TaskDTO;

public interface WorkerService
{
    void handleTask(TaskDTO taskDTO);
}
