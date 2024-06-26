package com.kstrinadka.workerproject.controller;

import com.kstrinadka.workerproject.dto.TaskDTO;
import com.kstrinadka.workerproject.service.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/api/worker/hash/crack/task")
public class ManagerController
{
    private final WorkerService workerService;
    private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    public ManagerController(WorkerService workerService)
    {
        this.workerService = workerService;
    }

    @PostMapping
    public void handleNewTask(@RequestBody TaskDTO taskDTO)
    {
        String info = String.format("""
                New task received!
                Task info:
                ID= %s
                Start= %d
                Checks num = %d
                Hash= %s
                Max length= %d
                """, taskDTO.getTicketID(), taskDTO.getStart(), taskDTO.getCheckAmount(), taskDTO.getHash(), taskDTO.getMaxLen());
        logger.info(info);

        workerService.handleTask(taskDTO);
    }
}
