package com.kstrinadka.managerproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateDTO
{
    private String ticketID;
    private List<String> result;
}
