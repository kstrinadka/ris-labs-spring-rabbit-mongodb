package com.kstrinadka.workerproject.dto;

import lombok.Data;

@Data
public class TaskDTO
{
    private String ticketID;
    private long start;
    private long checkAmount;
    private int maxLen;
    private String hash;
}
