package com.kstrinadka.managerproject.service.strats.impl;

import com.kstrinadka.managerproject.constants.Constants;
import com.kstrinadka.managerproject.dto.TaskDTO;
import com.kstrinadka.managerproject.service.strats.util.WorkerTaskPair;
import com.kstrinadka.managerproject.storage.Ticket;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.kstrinadka.managerproject.service.strats.AbstractTicketSplitter;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("fixed")
public class FixedTicketSplitter extends AbstractTicketSplitter
{
    private static final int WORDS_PER_TASK = 10_000_000;

    @Override
    public List<WorkerTaskPair> splitTicket(Ticket ticket)
    {
        initializeSplitting(ticket);
        List<WorkerTaskPair> tasks = new ArrayList<>();


        long wordCount = (int)(SYMBOLS_IN_ALPHABET*(Math.pow(SYMBOLS_IN_ALPHABET, ticket.getMaxLength()) - 1)/(SYMBOLS_IN_ALPHABET - 1));
        long curStart = 1;
        int curWorker = 0;

        while(wordCount > 0)
        {
            curWorker = (curWorker % Constants.WORKERS_NUMBER) + 1;
            if (wordCount < WORDS_PER_TASK)
            {
                TaskDTO newTask = makeTask(curStart, wordCount);
                curTicket.setTasksNumber(curTicket.getTasksNumber()+1);
                tasks.add(new WorkerTaskPair(curWorker, newTask));

                break;
            }
            else
            {
                TaskDTO newTask = makeTask(curStart, WORDS_PER_TASK);
                curTicket.setTasksNumber(curTicket.getTasksNumber()+1);
                tasks.add(new WorkerTaskPair(curWorker, newTask));

                wordCount -= WORDS_PER_TASK;
                curStart += WORDS_PER_TASK;
            }
        }

        logger.info("Splitting ticket "+ticket.getTicketId()+" : splitting finished");

        return tasks;
    }
}
