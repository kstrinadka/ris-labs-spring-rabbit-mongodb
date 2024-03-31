package ru.nsu.fit.g20202.vartazaryan.managerproject.service.strats;

import ru.nsu.fit.g20202.vartazaryan.managerproject.dto.TaskDTO;
import ru.nsu.fit.g20202.vartazaryan.managerproject.service.strats.util.WorkerTaskPair;
import ru.nsu.fit.g20202.vartazaryan.managerproject.storage.Ticket;

import java.util.List;

public interface TicketSplitter
{
    /**
     * @param ticket - заявка на взлом
     * @return - какому ворвкеру будет принадлежать данная заявка
     */
    List<WorkerTaskPair> splitTicket(Ticket ticket);
}
