package ru.nsu.fit.g20202.vartazaryan.managerproject.dto;

import lombok.Builder;
import lombok.Data;
import ru.nsu.fit.g20202.vartazaryan.managerproject.storage.Status;

import java.util.List;

@Data
@Builder
public class ResultDTO {
    private Status status;
    private List<String> data;
}
