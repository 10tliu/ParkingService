package tristan.model;

import java.util.List;

public interface ServiceFacade extends TicketMachineDAO {

    public TicketMachine createTicketMachine(TicketMachine ticketMachine);

    public boolean deleteTicketMachine(Integer id);

    public void deleteAllEntities();

    public TicketMachine retrieveTicketMachine(Integer id);

    public List<TicketMachine> retrieveAllEntities();

    public List<TicketMachine> retrieveMatchingEntities(TicketMachine ticketMachineTempate);

    public TicketMachine updateTicketMachine(TicketMachine ticketMachine);
    
}
