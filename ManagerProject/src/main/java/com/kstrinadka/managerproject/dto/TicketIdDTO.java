package com.kstrinadka.managerproject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TicketIdDTO
{
    private UUID requestId;
}
