package com.kstrinadka.managerproject.net;

import com.kstrinadka.managerproject.dto.TaskDTO;

public interface WorkerSender
{
    int sendTaskToWorker(TaskDTO task, int worker);
}
