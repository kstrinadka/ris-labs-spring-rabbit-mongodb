package com.kstrinadka.managerproject.service.impl;

import com.kstrinadka.managerproject.service.strats.util.WorkerTaskPair;
import com.kstrinadka.managerproject.storage.Ticket;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.kstrinadka.managerproject.net.WorkerSender;
import com.kstrinadka.managerproject.service.strats.AbstractTicketSplitter;
import com.kstrinadka.managerproject.storage.TicketStorage;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class WorkerService
{
    private static final int THREAD_POOL_SIZE = 10;
    @Setter
    @Getter
    private int workersNumber;

    private final TicketStorage ticketStorage;
    private final ExecutorService threadPool;
    private final WorkerSender workerSender;
    private final AbstractTicketSplitter ticketSplitter;

    @Autowired
    public WorkerService(TicketStorage ticketStorage,
                         @Qualifier("rabbitMQWorkerSender") WorkerSender workerSender,
                         @Qualifier("equal") AbstractTicketSplitter ticketSplitter)
    {
        this.ticketSplitter = ticketSplitter;
        String workersNum = System.getenv("WORKERS_NUM");
        this.workerSender = workerSender;
        this.workersNumber = workersNum != null ? Integer.parseInt(workersNum) : 2;
        this.ticketStorage = ticketStorage;
        this.threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    public void handleTicket(String ticketID)
    {
        Ticket curTicket = ticketStorage.getTicket(ticketID);
        List<WorkerTaskPair> workerTaskPairs = ticketSplitter.splitTicket(curTicket);
        workerTaskPairs.forEach(this::sendTask);
    }

    private void sendTask(WorkerTaskPair dto)
    {
        threadPool.submit(() -> {
            workerSender.sendTaskToWorker(dto.getTask(), dto.getWorker());
        });
    }
}
