package com.kstrinadka.managerproject.service.impl;

import com.kstrinadka.managerproject.service.strats.util.WorkerTaskPair;
import com.kstrinadka.managerproject.storage.Ticket;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.kstrinadka.managerproject.net.WorkerSender;
import com.kstrinadka.managerproject.service.WorkerService;
import com.kstrinadka.managerproject.service.strats.AbstractTicketSplitter;
import com.kstrinadka.managerproject.storage.TicketStorage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class WorkerServiceImpl implements WorkerService
{
    private static final int THREAD_POOL_SIZE = 10;
    @Setter
    @Getter
    private int workersNumber;

    private final TicketStorage ticketStorage;
    private final ExecutorService threadPool;
    private final WorkerSender workerSender;
    private final AbstractTicketSplitter ticketSplitter;
    private static final Logger logger = LoggerFactory.getLogger(WorkerServiceImpl.class);

    @Autowired
    public WorkerServiceImpl(TicketStorage ticketStorage, @Qualifier("rabbitMQWorkerSender") WorkerSender workerSender, @Qualifier("equal") AbstractTicketSplitter ticketSplitter)
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
        ticketSplitter.splitTicket(curTicket).stream().forEach(this::sendTask);
    }

    private void sendTask(WorkerTaskPair dto)
    {
        threadPool.submit(() -> {
            workerSender.sendTaskToWorker(dto.getTask(), dto.getWorker());
        });
    }
}
