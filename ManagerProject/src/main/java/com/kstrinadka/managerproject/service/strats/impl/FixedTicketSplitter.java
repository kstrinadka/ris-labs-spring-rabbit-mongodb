package com.kstrinadka.managerproject.service.strats.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.kstrinadka.managerproject.dto.TaskDTO;
import com.kstrinadka.managerproject.service.strats.AbstractTicketSplitter;
import com.kstrinadka.managerproject.service.strats.util.WorkerTaskPair;
import com.kstrinadka.managerproject.storage.Ticket;

import java.util.ArrayList;
import java.util.List;

import static com.kstrinadka.managerproject.constants.Constants.WORKERS_NUMBER;

/**
 *
 */
@Component
@Qualifier("fixed")
public class FixedTicketSplitter extends AbstractTicketSplitter
{
    private static final int WORDS_PER_TASK = 10_000_000;

    /**
     * @param ticket - заявка на взлом
     * @return - список задач, каждая из которых уже привязана к определенному номеру воркера.
     */
    @Override
    public List<WorkerTaskPair> splitTicket(Ticket ticket)
    {
        initializeSplitting(ticket);
        List<WorkerTaskPair> tasks = new ArrayList<>();

        // кол-во букв, которые нужно перебрать
        long wordCount = (int) (SYMBOLS_IN_ALPHABET*(Math.pow(SYMBOLS_IN_ALPHABET, ticket.getMaxLength()) - 1)/(SYMBOLS_IN_ALPHABET - 1));

        //
        long curStart = 1;

        // название ноды воркера (номер ноды воркера)
        int curWorker = 0;

        // отправляем воркерам их задачи
        // не больше WORDS_PER_TASK слов идет в одну задачу
        while(wordCount > 0)
        {
            // Выбрали какой воркер будет обрабатывать след. задачу
            curWorker = (curWorker % WORKERS_NUMBER) + 1;

            // обработка оставшихся слов
            if (wordCount < WORDS_PER_TASK)
            {
                TaskDTO newTask = makeTask(curStart, wordCount);
                curTicket.setTasksNumber(curTicket.getTasksNumber() + 1);
                tasks.add(
                        WorkerTaskPair.builder()
                                .worker(curWorker)
                                .task(newTask)
                                .build()
                );

                break;
            }


            else
            {
                TaskDTO newTask = makeTask(curStart, WORDS_PER_TASK);
                curTicket.setTasksNumber(curTicket.getTasksNumber()+1);
                tasks.add(
                        WorkerTaskPair.builder()
                                .worker(curWorker)
                                .task(newTask)
                                .build()
                );

                wordCount -= WORDS_PER_TASK;
                curStart += WORDS_PER_TASK;
            }
        }

        logger.info("Splitting ticket "+ticket.getTicketId()+" : splitting finished");

        return tasks;
    }
}
