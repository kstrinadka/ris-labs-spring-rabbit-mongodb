package com.kstrinadka.managerproject.storage;

import com.kstrinadka.managerproject.dto.CrackDTO;

import java.util.List;

public interface Storage
{
    Ticket addNewTicket(CrackDTO dto);
    Ticket getTicket(String id);
    void deleteTicket(String id);
    void deleteAllTickets();
    void updateTicket(String id, List<String> data);
    int getStorageSize();
}
