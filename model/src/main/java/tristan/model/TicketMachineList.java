/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tristan.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cgallen
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketMachineList {

    // only used by persistance layer
    private Integer lastEntityId = null;

    @XmlElementWrapper(name = "entities")
    @XmlElement(name = "entity")
    private List<TicketMachine> entities = new ArrayList<TicketMachine>();

    public List<TicketMachine> getEntities() {
        return entities;
    }

    public void setEntities(List<TicketMachine> entities) {
        this.entities = entities;
    }
    
    
    public Integer getLastEntityId() {
        return lastEntityId;
    }

    public void setLastEntityId(Integer lastEntityId) {
        this.lastEntityId = lastEntityId;
    }

    @Override
    public String toString() {
        return "TicketMachineList{" + "lastEntityId=" + lastEntityId + ", entities=" + entities + '}';
    }



}
