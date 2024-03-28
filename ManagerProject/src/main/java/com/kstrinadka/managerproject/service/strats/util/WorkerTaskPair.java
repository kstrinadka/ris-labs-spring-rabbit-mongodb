package com.kstrinadka.managerproject.service.strats.util;

import com.kstrinadka.managerproject.dto.TaskDTO;
import lombok.Getter;

public class WorkerTaskPair
{
    @Getter
    private final int worker;
    @Getter
    private final TaskDTO task;

    public WorkerTaskPair(int worker, TaskDTO dto)
    {
        this.worker =  worker;
        this.task = dto;
    }
}
