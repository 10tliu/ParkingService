/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tristan.swingcient.test.manual;

import java.io.File;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tristan.dao.jaxbimpl.TicketMachineDAOJaxbImpl;
import tristan.model.TicketMachine;
import tristan.model.TicketMachineDAO;
import tristan.swingcient.EntityClientLoader;

/**
 *
 * @author cgallen
 */
public class TicketMachineSingleClientLoaderTest {

    private static final Logger LOG = LoggerFactory.getLogger(TicketMachineSingleClientLoaderTest.class);

    public static final String TEST_DATA_FILE_LOCATION = "target/testDaofile.xml";

    public static final String TEST_BASE_URL = "http://localhost:8680/";

    @Test
    public void testSingleClientLoader() {

        // delete test file at start of test
        File file = new File(TEST_DATA_FILE_LOCATION);
        file.delete();
        assertFalse(file.exists());

        // create dao
        TicketMachineDAO ticketMachineDAO = new TicketMachineDAOJaxbImpl(TEST_DATA_FILE_LOCATION);
        assertTrue(file.exists());

        List<TicketMachine> list = ticketMachineDAO.retrieveAllEntities();
        assertTrue(list.isEmpty());

        String baseUrl = "http://localhost:8680/";

        EntityClientLoader entityClientLoader = new EntityClientLoader(ticketMachineDAO, baseUrl);

        // try to load from service
        boolean success = entityClientLoader.restClientRetrieveAll();
        assertTrue(success);

        list = ticketMachineDAO.retrieveAllEntities();
        LOG.debug("retrieved enties = " + list.size());

    }

}
