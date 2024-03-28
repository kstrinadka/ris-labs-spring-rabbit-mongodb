package com.kstrinadka.managerproject.dto;

import lombok.Data;

@Data
public class TicketIdDTO
{
    private String requestId;

    public TicketIdDTO(String id)
    {
        this.requestId = id;
    }
}
