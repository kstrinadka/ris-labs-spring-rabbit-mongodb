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
@Qualifier("equal")
public class EqualPartsTicketSplitter extends AbstractTicketSplitter
{
    @Override
    public List<WorkerTaskPair> splitTicket(Ticket ticket)
    {
        initializeSplitting(ticket);
        List<WorkerTaskPair> tasks = new ArrayList<>();

        long wordCount = (int)(SYMBOLS_IN_ALPHABET*(Math.pow(SYMBOLS_IN_ALPHABET, ticket.getMaxLength()) - 1)/(SYMBOLS_IN_ALPHABET - 1));
        long wordsPerWorker = wordCount / Constants.WORKERS_NUMBER;
        long remainingWords = wordCount % Constants.WORKERS_NUMBER;
        long curStart = 1;

        for(int i = 1; i <= Constants.WORKERS_NUMBER; i++)
        {
            long curFinish;
            if (i > 1 && i == Constants.WORKERS_NUMBER)
            {
                curFinish = curStart + wordsPerWorker - 1 + remainingWords;
            } else {
                curFinish = curStart + wordsPerWorker - 1;
            }

            logger.info(String.format("Worker %d: start = %d, to_check = %d", i, curStart, curFinish-curStart));

            TaskDTO dto = makeTask(curStart, curFinish-curStart);
            tasks.add(new WorkerTaskPair(i, dto));

            ticket.setTasksNumber(ticket.getTasksNumber()+1);
            curStart = curFinish + 1;
        }

        return tasks;
    }
}
