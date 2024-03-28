package com.kstrinadka.managerproject;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WorkerServiceTests
{
    /*@Autowired
    private WorkerServiceImpl workerServiceImpl;
    @MockBean
    private TicketStorage ticketStorage;
    @MockBean
    private HttpWorkerSender workerSender;
    @MockBean
    private ExecutorService executorService;

    @BeforeEach
    public void setUp()
    {
        reset(ticketStorage, workerSender);
        ticketStorage.deleteAllTickets();
    }

    @Test
    public void handleTicket_1WorkersTest()
    {
        workerServiceImpl.setWorkersNumber(1);

        UUID id = UUID.randomUUID();
        Ticket ticket = new Ticket(id, "abc", 3);
        when(ticketStorage.getTicket(anyString())).thenReturn(ticket);

        workerServiceImpl.handleTicket(id.toString());
        verify(ticketStorage).getTicket(eq(id.toString()));

        ArgumentCaptor<TaskDTO> argumentCaptor = ArgumentCaptor.forClass(TaskDTO.class);
        verify(workerSender, times(workerServiceImpl.getWorkersNumber())).sendTaskToWorker(argumentCaptor.capture(), anyInt());
    }

    /*@Test
    public void handleTicket_2WorkersTest()
    {
        workerServiceImpl.setWorkersNumber(2);

        UUID id = UUID.randomUUID();
        Ticket ticket = new Ticket(id, "hash", 3);
        when(ticketStorage.getTicket(anyString())).thenReturn(ticket);

        workerServiceImpl.handleTicket(id.toString());
        verify(ticketStorage).getTicket(eq(id.toString()));

        ArgumentCaptor<TaskDTO> argumentCaptor = ArgumentCaptor.forClass(TaskDTO.class);
        verify(workerSender, times(workerServiceImpl.getWorkersNumber())).sendTaskToWorker(argumentCaptor.capture(), anyInt());
    }

    @Test
    public void handleTicketRandomWorkersTest()
    {
        workerServiceImpl.setWorkersNumber(new Random().nextInt(1, 8));

        UUID id = UUID.randomUUID();
        Ticket ticket = new Ticket(id, "hash", 3);
        when(ticketStorage.getTicket(anyString())).thenReturn(ticket);

        workerServiceImpl.handleTicket(id.toString());
        verify(ticketStorage).getTicket(eq(id.toString()));

        ArgumentCaptor<TaskDTO> argumentCaptor = ArgumentCaptor.forClass(TaskDTO.class);
        verify(workerSender, times(workerServiceImpl.getWorkersNumber())).sendTaskToWorker(argumentCaptor.capture(), anyInt());
    }*/
}
