/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tristan.dao.jaxbimpl;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tristan.model.TicketMachine;
import tristan.model.TicketMachineDAO;
import tristan.model.TicketMachineList;

/**
 *
 * @author cgallen
 */
public class TicketMachineDAOJaxbImpl implements TicketMachineDAO {

    private static final Logger LOG = LoggerFactory.getLogger(TicketMachineDAOJaxbImpl.class);

    // jaxb context needs jaxb.index jaxbFile to be in same classpath
    // this path contains a list of Jaxb annotated classes for the context to parse
    private static final String CONTEXT_PATH = "tristan.model";

    // you must obtain lock before accessing objects and / or saving file
    public final Object Lock = new Object();

    private String dataFileLocation = null;

    private File jaxbFile = null;

    private TicketMachineList ticketMachineList = null;

    private JAXBContext jaxbContext = null;

    public TicketMachineDAOJaxbImpl(String dataFileLocation) {
        super();
        if (dataFileLocation == null) {
            throw new IllegalArgumentException("dataFile cannot be null");
        }
        this.dataFileLocation = dataFileLocation;
        load();
    }

    @Override
    public TicketMachine createTicketMachine(TicketMachine ticketMachine) {
        if (ticketMachine == null) {
            throw new IllegalArgumentException("ticketMachine cannot be null");
        }
        synchronized (Lock) {
            // set initial id if not set or increment by 1
            Integer id = (ticketMachineList.getLastEntityId() == null) ? 1 : ticketMachineList.getLastEntityId() + 1;

            ticketMachineList.setLastEntityId(id);
            TicketMachine ecopy = copy(ticketMachine);
            ecopy.setMachineId(id);
            ticketMachineList.getEntities().add(ecopy);
            save();
            return ecopy;
        }
    }

    @Override
    public boolean deleteTicketMachine(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        synchronized (Lock) {
            Iterator<TicketMachine> it = ticketMachineList.getEntities().iterator();
            while (it.hasNext()) {
                TicketMachine en = it.next();
                if (id.equals(en.getMachineId())) {
                    it.remove();
                    save();
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public void deleteAllEntities() {
        synchronized (Lock) {
            ticketMachineList.getEntities().clear();
        }
    }

    @Override
    public TicketMachine retrieveTicketMachine(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        synchronized (Lock) {
            for (TicketMachine en : ticketMachineList.getEntities()) {
                if (id.equals(en.getMachineId())) {
                    return copy(en);
                }
            }
        }
        return null;
    }

    @Override
    public List<TicketMachine> retrieveAllEntities() {
        synchronized (Lock) {
            List<TicketMachine> returnList = new ArrayList<TicketMachine>();
            for (TicketMachine ticketMachine : ticketMachineList.getEntities()) {
                returnList.add(copy(ticketMachine));
            };
            return returnList;
        }
    }

    /**
     * Returns a list of all Entities which match all of the set (i.e. not null)
     * fields of ticketMachineTemplate
     *
     * @param ticketMachineTemplate
     * @return
     */
    @Override
    public List<TicketMachine> retrieveMatchingEntities(TicketMachine ticketMachineTemplate) {
        if (ticketMachineTemplate == null) {
            throw new IllegalArgumentException("ticketMachineTemplate cannot be null");
        }
        List<TicketMachine> returnList = new ArrayList<TicketMachine>();
        synchronized (Lock) {
            for (TicketMachine ticketMachine : ticketMachineList.getEntities()) {
                boolean match = true;
                if (ticketMachineTemplate.getMachineId() != null) {
                    if (!ticketMachineTemplate.getMachineId().equals(ticketMachine.getMachineId())) {
                        match = false;
                    }
                };
                if (ticketMachineTemplate.getLocation() != null) {
                    if (!ticketMachineTemplate.getLocation().equals(ticketMachine.getLocation())) {
                        match = false;
                    }
                };
                if (ticketMachineTemplate.getStayType() != null) {
                    if (!ticketMachineTemplate.getStayType().equals(ticketMachine.getStayType())) {
                        match = false;
                    }
                };
                /*if (ticketMachineTemplate.getField_C() != null) {
                    if (!ticketMachineTemplate.getField_C().equals(ticketMachine.getField_C())) {
                        match = false;
                    }
                };*/
                if (match) {
                    returnList.add(copy(ticketMachine));
                }
            }
            return returnList;
        }
    }

    @Override
    public TicketMachine updateTicketMachine(TicketMachine ticketMachineTemplate) {
        if (ticketMachineTemplate == null) {
            throw new IllegalArgumentException("entity cannot be null");
        }
        synchronized (Lock) {
            for (TicketMachine en : ticketMachineList.getEntities()) {
                if (ticketMachineTemplate.getMachineId().equals(en.getMachineId())) {
                    boolean changedfield = false;

                    // update properties fields if only if ticketMachineTemplate field is set
                    if (ticketMachineTemplate.getLocation() != null) {
                        en.setLocation(ticketMachineTemplate.getLocation());
                        changedfield = true;
                    }
                    if (ticketMachineTemplate.getStayType() != null) {
                        en.setStayType(ticketMachineTemplate.getStayType());
                        changedfield = true;
                    }
                    /*if (ticketMachineTemplate.getField_C() != null) {
                        en.setField_C(ticketMachineTemplate.getField_C());
                        changedfield = true;
                    }*/
                    // save if anything changed
                    if (changedfield) {
                        save();
                    }
                    return copy(en);
                }

            }
        }
        return null; //entity not found
    }

    /**
     * copies new TicketMachine data transfer objects to create detached object and so
     * avoid problems with indirect object modification
     *
     * @param ticketMachine
     * @return independent copy of TicketMachine
     */
    private TicketMachine copy(TicketMachine ticketMachine) {
        try {
            StringWriter sw1 = new StringWriter();
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(ticketMachine, sw1);

            StringReader sr1 = new StringReader(sw1.toString());
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            TicketMachine newAccount = (TicketMachine) jaxbUnMarshaller.unmarshal(sr1);
            return newAccount;
        } catch (JAXBException ex) {
            throw new RuntimeException("problem copying jaxb object", ex);
        }
    }

    /**
     * loads jaxb file at beginning of program
     */
    private void load() {

        try {

            // jaxb context needs jaxb.index jaxbFile to be in same classpath
            // this contains a list of Jaxb annotated classes for the context to parse
            jaxbContext = JAXBContext.newInstance(CONTEXT_PATH);

            // try to load dataFileLocation
            jaxbFile = new File(dataFileLocation);
            LOG.debug("using dataFile:" + jaxbFile.getAbsolutePath());

            if (jaxbFile.exists()) {
                LOG.debug("dataFile exists loading:" + jaxbFile.getAbsolutePath());
                // load jaxbFile
                Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
                ticketMachineList = (TicketMachineList) jaxbUnMarshaller.unmarshal(jaxbFile);
            } else {
                // create annd save an empty jaxbFile
                LOG.debug("dataFile does not exist creating new " + jaxbFile.getAbsolutePath());

                ticketMachineList = new TicketMachineList();

                // make directories if dont exist
                jaxbFile.getParentFile().mkdirs();

                // save empty data to new file
                save();
            }

        } catch (JAXBException ex) {
            throw new RuntimeException("problem creating persistor", ex);
        }

    }

    /**
     * saves data to datafile on updates
     */
    private void save() {
        try {
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(ticketMachineList, jaxbFile);
            if (LOG.isDebugEnabled()) {
                StringWriter sw1 = new StringWriter();
                jaxbMarshaller.marshal(ticketMachineList, sw1);
                LOG.debug("saving xml to file:" + sw1.toString());
            }

        } catch (JAXBException ex) {
            throw new RuntimeException("problem persisting dao", ex);
        }
    }

}
