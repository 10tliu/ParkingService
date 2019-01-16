/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tristan.swingcient.test.manual;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.junit.Test;
import static org.junit.Assert.*;
import tristan.model.TicketMachine;
import tristan.model.ReplyMessage;
import tristan.web.rest.client.RestClientJerseyImpl;

/**
 *
 * @author cgallen
 */
public class RestClientJerseyImplTest {

    String baseUrl = "http://localhost:8680/";

    MediaType mediaType = MediaType.APPLICATION_XML_TYPE;

    @Test
    public void restClientRetreiveTest() {

        RestClientJerseyImpl restClient = new RestClientJerseyImpl(baseUrl, mediaType);

        // try to retreive an unknown ticketMachine
        ReplyMessage replyMessage = restClient.retrieveEntity(Integer.SIZE);
        assertNotNull(replyMessage);
        assertTrue(replyMessage.getTicketMachineList().getEntities().isEmpty());

        // try to retreive ticketMachine with id 1
        ReplyMessage replyMessage2 = restClient.retrieveEntity(1);
        assertNotNull(replyMessage2);
        assertEquals(1, replyMessage2.getTicketMachineList().getEntities().size());

        TicketMachine ticketMachine = replyMessage2.getTicketMachineList().getEntities().get(0);
        System.out.println("Received TicketMachine: " + ticketMachine);

    }

    @Test
    public void restClientRetreiveTemplateTest() {

        RestClientJerseyImpl restClient = new RestClientJerseyImpl(baseUrl, mediaType);

        TicketMachine ticketMachineTempate = new TicketMachine();
        ticketMachineTempate.setLocation("abcd");

        // try to retreive an unknown entity
        ReplyMessage replyMessage = restClient.retrieveMatchingEntites(ticketMachineTempate);
        assertNotNull(replyMessage);

        List<TicketMachine> ticketMachineList =  replyMessage.getTicketMachineList().getEntities();
        System.out.println("Received "
                + ticketMachineList.size()
                + " Entities");
        
       for(TicketMachine e: ticketMachineList){
           System.out.println("   "+ e);
       }
        
    }
}
