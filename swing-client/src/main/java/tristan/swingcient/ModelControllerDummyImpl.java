/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tristan.swingcient;


import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tristan.model.TicketMachine;

/**
 *
 * @author cgallen
 */
public class ModelControllerDummyImpl implements ModelController {

    private static final Logger LOG = LoggerFactory.getLogger(ModelControllerDummyImpl.class);

    private EntityListTableModel entityListTableModel = null;

    private void initialiseTableModel() {

        entityListTableModel = new EntityListTableModel();
        List<TicketMachine> elist = new ArrayList<TicketMachine>();

        int ENTITY_NUMBER = 40;
        for (int intityId = 0; intityId < ENTITY_NUMBER; intityId++) {
            TicketMachine ticketMachine = new TicketMachine();
            ticketMachine.setMachineId(intityId);
            ticketMachine.setLocation("field_A_" + intityId);
            ticketMachine.setStayType("field_B_" + intityId);;
            //ticketMachine.setField_C("field_C_" + intityId);;
            elist.add(ticketMachine);
        }
        entityListTableModel.setEntities(elist);
    }

    @Override
    public EntityListTableModel getEntityListTableModel() {
        if (entityListTableModel == null) {
            synchronized (ModelControllerDummyImpl.class) {
                if (entityListTableModel == null) {
                    initialiseTableModel();
                }
            }
        }
        return entityListTableModel;

    }
    


    @Override
    public void clearSearch() {
        LOG.debug("clear search selected");
    }

    @Override
    public void findMatchingSearch(TicketMachine templateTicketMachine) {
        LOG.debug("find matching with templateTicketMachine="+ templateTicketMachine);
    }

    @Override
    public boolean forceReloadData() {
        return false;
    }



}
