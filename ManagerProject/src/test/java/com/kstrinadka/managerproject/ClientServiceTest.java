package com.kstrinadka.managerproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.kstrinadka.managerproject.dto.ResultDTO;
import com.kstrinadka.managerproject.service.impl.ClientService;
import com.kstrinadka.managerproject.storage.Status;
import com.kstrinadka.managerproject.storage.Ticket;
import com.kstrinadka.managerproject.storage.TicketStorage;

import java.util.ArrayList;
import java.util.UUID;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientServiceTest
{
    @Autowired
    private ClientService clientService;
    @MockBean
    private TicketStorage ticketStorage;

    private Ticket createMockTicket(Status status)
    {
        Ticket mockTicket = new Ticket(UUID.randomUUID(), "abc9-01zf", 3);

        if (status == Status.DONE)
        {
            mockTicket.setStatus(status);
            mockTicket.setResult(new ArrayList<>(){{
                add("abs");
            }});
        }
        if (status == Status.ERROR)
            mockTicket.setStatus(status);

        return mockTicket;
    }

    @Test
    void getDataInProgressTest()
    {
        Ticket mockTicket = createMockTicket(Status.IN_PROGRESS);
        when(ticketStorage.getTicket(anyString())).thenReturn(mockTicket);

        var res = clientService.getData("abcd");
        assertThat(res).isEqualTo(ResultDTO.builder().status(Status.IN_PROGRESS).data(null)
                .build()
        );
    }

    @Test
    void getDataDoneTest()
    {
        Ticket mockTicket = createMockTicket(Status.DONE);
        when(ticketStorage.getTicket(anyString())).thenReturn(mockTicket);

        var res = clientService.getData("abcd");
        assertThat(res).isEqualTo(ResultDTO.builder().status(Status.DONE).data(new ArrayList<>(){{add("abs");}})
                .build()
        );
    }

    @Test
    void getDataErrorTest()
    {
        Ticket mockTicket = createMockTicket(Status.ERROR);
        when(ticketStorage.getTicket(anyString())).thenReturn(mockTicket);

        var res = clientService.getData("abcd");
        assertThat(res).isEqualTo(ResultDTO.builder().status(Status.ERROR).data(null)
                .build()
        );
    }
}
