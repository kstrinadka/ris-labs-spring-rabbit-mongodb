package com.kstrinadka.managerproject.dto;

import lombok.Builder;
import lombok.Data;
import com.kstrinadka.managerproject.storage.Status;

import java.util.List;

@Data
@Builder
public class ResultDTO {
    private Status status;
    private List<String> data;
}
