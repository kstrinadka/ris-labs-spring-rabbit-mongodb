package com.kstrinadka.managerproject.dto;

import com.kstrinadka.managerproject.storage.Status;
import lombok.Data;

import java.util.List;

@Data
public class ResultDTO
{
    private Status status;
    private List<String> data;

    public ResultDTO(Status status, List<String> data)
    {
        this.status = status;
        this.data = data;
    }
}
