package com.kstrinadka.managerproject;

import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ClientServiceTest
{
    /*@Autowired
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
        assertThat(res).isEqualTo(new ResultDTO(Status.IN_PROGRESS, null));
    }

    @Test
    void getDataDoneTest()
    {
        Ticket mockTicket = createMockTicket(Status.DONE);
        when(ticketStorage.getTicket(anyString())).thenReturn(mockTicket);

        var res = clientService.getData("abcd");
        assertThat(res).isEqualTo(new ResultDTO(Status.DONE, new ArrayList<>(){{add("abs");}}));
    }

    @Test
    void getDataErrorTest()
    {
        Ticket mockTicket = createMockTicket(Status.ERROR);
        when(ticketStorage.getTicket(anyString())).thenReturn(mockTicket);

        var res = clientService.getData("abcd");
        assertThat(res).isEqualTo(new ResultDTO(Status.ERROR, null));
    }*/
}
