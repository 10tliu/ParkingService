/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tristan.swingcient;

import tristan.model.TicketMachine;

/**
 *
 * @author cgallen
 */
public interface ModelController {

    void clearSearch();

    void findMatchingSearch(TicketMachine templateTicketMachine);

    EntityListTableModel getEntityListTableModel();
    
    boolean forceReloadData();
    
}
