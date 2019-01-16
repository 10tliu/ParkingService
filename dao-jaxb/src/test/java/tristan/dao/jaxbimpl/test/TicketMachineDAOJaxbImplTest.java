/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tristan.dao.jaxbimpl.test;

import java.io.File;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tristan.dao.jaxbimpl.TicketMachineDAOJaxbImpl;
import tristan.model.TicketMachine;
import tristan.model.TicketMachineDAO;

/**
 * tests for entityDao.createTicketMachine(entity) entityDao.deleteTicketMachine(Id) entityDao.retrieveAllEntities() entityDao.retrieveTicketMachine(Id)
 * entityDao.retrieveMatchingEntites(entityTempate) entityDao.updateTicketMachine(entity)
 *
 * @author cgallen
 */
public class TicketMachineDAOJaxbImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(TicketMachineDAOJaxbImplTest.class);

    public final String TEST_DATA_FILE_LOCATION = "target/testDaofile.xml";

    @Test
    public void testDestinationsDAOJaxb() {

        // delete test file at start of test
        File file = new File(TEST_DATA_FILE_LOCATION);
        file.delete();
        assertFalse(file.exists());

        // create dao
        TicketMachineDAO ticketMachineDao = new TicketMachineDAOJaxbImpl(TEST_DATA_FILE_LOCATION);

        // check that new file created
        assertTrue(file.exists());

        // check there are no entities
        assertTrue(ticketMachineDao.retrieveAllEntities().isEmpty());

        // add a 3 entities
        int ENTITY_NUMBER = 4;
        for (int intityId = 0; intityId < ENTITY_NUMBER; intityId++) {
            TicketMachine ticketMachine = new TicketMachine();
            ticketMachine.setLocation("field_A_" + intityId);
            ticketMachine.setStayType("field_B_" + intityId);;
            //ticketMachine.setField_C("field_C_" + intityId);;

            LOG.debug("adding ticketMachine:" + ticketMachine);
            TicketMachine e = ticketMachineDao.createTicketMachine(ticketMachine);
            assertNotNull(e);
        }

        // check 3 entities added
        assertTrue(ENTITY_NUMBER == ticketMachineDao.retrieveAllEntities().size());

        // check return false for delete unknown entity
        assertFalse(ticketMachineDao.deleteTicketMachine(Integer.SIZE));

        // find an entity to delete
        List<TicketMachine> elist = ticketMachineDao.retrieveAllEntities();
        Integer idToDelete = elist.get(1).getMachineId();
        LOG.debug("deleting  entity:" + idToDelete);

        // check found and deleted
        assertTrue(ticketMachineDao.deleteTicketMachine(idToDelete));

        // check no longer found after deletion
        assertNull(ticketMachineDao.retrieveTicketMachine(idToDelete));

        // check entities size decremeted
        List<TicketMachine> elist2 = ticketMachineDao.retrieveAllEntities();
        assertTrue(ENTITY_NUMBER - 1 == elist2.size());

        // update entity
        TicketMachine ticketMachineToUpdate = elist2.get(1);
        LOG.debug("updating entity: " + ticketMachineToUpdate);

        // add 3 newProperties for entity
        ticketMachineToUpdate.setLocation("field_A_Update");
        ticketMachineToUpdate.setStayType("field_B_Update");
        //ticketMachineToUpdate.setField_C(null); // do not update field C
        LOG.debug("update template: " + ticketMachineToUpdate);

        TicketMachine updatedTicketMachine = ticketMachineDao.updateTicketMachine(ticketMachineToUpdate);
        LOG.debug("updated entity: " + updatedTicketMachine);
        assertNotNull(updatedTicketMachine);

        // check entity updated
        TicketMachine retrievedTicketMachine = ticketMachineDao.retrieveTicketMachine(updatedTicketMachine.getMachineId());
        LOG.debug("retreived entity: " + retrievedTicketMachine);
        assertEquals(ticketMachineToUpdate.getLocation(), retrievedTicketMachine.getLocation());
        assertEquals(ticketMachineToUpdate.getLocation(), retrievedTicketMachine.getLocation());
        //assertNotEquals(ticketMachineToUpdate.getField_C(), retrievedTicketMachine.getField_C());

        // test retrieve matching entities
        List<TicketMachine> ticketMachineList = ticketMachineDao.retrieveAllEntities();
        TicketMachine searchfor = ticketMachineList.get(2);
        LOG.debug("searching for: " + searchfor);

        TicketMachine template = new TicketMachine();
        template.setStayType(searchfor.getStayType());
        LOG.debug("using template : " + template);

        List<TicketMachine> retrievedList = ticketMachineDao.retrieveMatchingEntities(template);
        assertEquals(1, retrievedList.size());

        LOG.debug("found : " + retrievedList.get(0));
        assertEquals(searchfor, retrievedList.get(0));

    }

}
