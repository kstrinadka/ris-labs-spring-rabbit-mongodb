package ru.nsu.fit.g20202.vartazaryan.managerproject.service.strats.util;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.nsu.fit.g20202.vartazaryan.managerproject.dto.TaskDTO;

/**
 * Определяет принадлежность заявки на взлом к определенному воркеру.
 */
@Getter
@RequiredArgsConstructor
@Builder
public class WorkerTaskPair
{
    private final int worker;
    private final TaskDTO task;
}
