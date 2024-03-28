package com.kstrinadka.managerproject.storage;

import org.springframework.stereotype.Component;
import com.kstrinadka.managerproject.dto.CrackDTO;

import java.util.List;

@Component
public class MongoStorage implements Storage {
    @Override
    public Ticket addNewTicket(CrackDTO dto) {
        return null;
    }

    @Override
    public Ticket getTicket(String id) {
        return null;
    }

    @Override
    public void deleteTicket(String id) {

    }

    @Override
    public void deleteAllTickets() {

    }

    @Override
    public void updateTicket(String id, List<String> data) {

    }

    @Override
    public int getStorageSize() {
        return 0;
    }
}
