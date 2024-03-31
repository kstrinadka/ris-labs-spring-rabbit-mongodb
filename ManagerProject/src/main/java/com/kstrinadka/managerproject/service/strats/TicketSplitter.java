package com.kstrinadka.managerproject.service.strats;

import com.kstrinadka.managerproject.service.strats.util.WorkerTaskPair;
import com.kstrinadka.managerproject.storage.Ticket;

import java.util.List;

public interface TicketSplitter
{
    /**
     * @param ticket - заявка на взлом
     * @return - какому ворвкеру будет принадлежать данная заявка
     */
    List<WorkerTaskPair> splitTicket(Ticket ticket);
}
