package com.kstrinadka.managerproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.kstrinadka.managerproject.controller.ClientController;
import com.kstrinadka.managerproject.controller.WorkerController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ManagerProjectApplicationTests
{
    @Autowired
    private ClientController clientController;
    @Autowired
    private WorkerController workerController;

    @Test
    void contextLoads()
    {
        assertThat(clientController).isNotNull();
        assertThat(workerController).isNotNull();
    }
}
