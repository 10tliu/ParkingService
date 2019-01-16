/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tristan.swingcient;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tristan.model.TicketMachine;
import tristan.model.TicketMachineDAO;

/**
 *
 * @author cgallen
 */
public class ModelControllerImpl implements ModelController {

    private static final Logger LOG = LoggerFactory.getLogger(ModelControllerImpl.class);

    private TicketMachineDAO ticketMachineDAO = null;

    private EntityListTableModel entityListTableModel = new EntityListTableModel();

    private EntityClientLoader entityClientLoader = null;

    public ModelControllerImpl(TicketMachineDAO ticketMachineDAO, EntityClientLoader entityClientLoader) {
        this.entityClientLoader = entityClientLoader;
        this.ticketMachineDAO = ticketMachineDAO;
        List<TicketMachine> entities = ticketMachineDAO.retrieveAllEntities();
        entityListTableModel.setEntities(entities);
    }

    @Override
    public EntityListTableModel getEntityListTableModel() {
        return entityListTableModel;
    }

    @Override
    public void clearSearch() {
        LOG.debug("clear search selected");

        List<TicketMachine> entities = ticketMachineDAO.retrieveAllEntities();
        entityListTableModel.setEntities(entities);
    }

    @Override
    public void findMatchingSearch(TicketMachine templateTicketMachine) {
        LOG.debug("find matching with templateTicketMachine=" + templateTicketMachine);

        List<TicketMachine> entities = ticketMachineDAO.retrieveMatchingEntities(templateTicketMachine);
        LOG.debug("found " + entities.size() + " matching with templateTicketMachine=" + templateTicketMachine);
        entityListTableModel.setEntities(entities);

    }

    @Override
    public boolean forceReloadData() {
        LOG.debug("forceReloadData called");
        boolean success = false;
        if (entityClientLoader != null) {
            success = entityClientLoader.restClientRetrieveAll();
        }
        LOG.debug("forceReloadData result=" + success);
        return success;
    }

}
