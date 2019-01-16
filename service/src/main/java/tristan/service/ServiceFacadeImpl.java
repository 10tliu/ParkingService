/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tristan.service;

import java.util.List;
import tristan.model.TicketMachine;
import tristan.model.TicketMachineDAO;
import tristan.model.ServiceFacade;

/**
 *
 * @author cgallen
 */
public class ServiceFacadeImpl implements ServiceFacade {
    
    TicketMachineDAO ticketMachineDAO = null;

    public void setTicketMachineDAO(TicketMachineDAO ticketMachineDAO) {
        this.ticketMachineDAO = ticketMachineDAO;
    }

    @Override
    public void deleteAllEntities() {
       ticketMachineDAO.deleteAllEntities();
    }

    @Override
    public TicketMachine createTicketMachine(TicketMachine ticketMachine) {
        return ticketMachineDAO.createTicketMachine(ticketMachine);
    }

    @Override
    public boolean deleteTicketMachine(Integer id) {
        return ticketMachineDAO.deleteTicketMachine(id);
    }

    @Override
    public TicketMachine retrieveTicketMachine(Integer id) {
        return ticketMachineDAO.retrieveTicketMachine(id);
    }

    @Override
    public List<TicketMachine> retrieveAllEntities() {
        return ticketMachineDAO.retrieveAllEntities();
    }

    @Override
    public List<TicketMachine> retrieveMatchingEntities(TicketMachine ticketMachineTempate) {
        return ticketMachineDAO.retrieveMatchingEntities(ticketMachineTempate);
    }

    @Override
    public TicketMachine updateTicketMachine(TicketMachine ticketMachine) {
        return ticketMachineDAO.updateTicketMachine(ticketMachine);
    }
    
}
