package com.kstrinadka.managerproject.service;

import com.kstrinadka.managerproject.dto.TicketIdDTO;
import com.kstrinadka.managerproject.dto.CrackDTO;
import com.kstrinadka.managerproject.dto.ResultDTO;
import com.kstrinadka.managerproject.dto.UpdateDTO;

public interface ClientService
{
    TicketIdDTO processRequest(CrackDTO dto);
    ResultDTO getData(String id);
    void updateTicket(UpdateDTO dto);
}
