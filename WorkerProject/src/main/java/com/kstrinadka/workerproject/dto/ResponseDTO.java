package com.kstrinadka.workerproject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseDTO
{
    private String ticketID;
    private List<String> result;
}
