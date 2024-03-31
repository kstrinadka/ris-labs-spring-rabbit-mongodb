package com.kstrinadka.managerproject.service.strats.util;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.kstrinadka.managerproject.dto.TaskDTO;

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
