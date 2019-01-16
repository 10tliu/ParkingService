package tristan.model;

public interface RestInterface {

    public ReplyMessage retrieveMatchingEntites(TicketMachine ticketMachineTempate);
    
    public ReplyMessage retrieveEntity(Integer id);
    
}
