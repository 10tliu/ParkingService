package tristan.model;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple example entity with 3 fields
 *
 * @author cgallen
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketMachine {

    private Integer machineId;

    private String location = null;

    private String stayType;

    //not necessary
    //private String field_C = null;

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStayType() {
        return stayType;
    }

    public void setStayType(String stayType) {
        this.stayType = stayType;
    }

   /* public String getField_C() {
        return field_C;
    }*/

    /*public void setField_C(String field_C) {
        this.field_C = field_C;
    }*/

    @Override
    public String toString() {
        return "TicketMachine{" + "machineId=" + machineId
                + ", location=" + location
                + ", stayType=" + stayType + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TicketMachine other = (TicketMachine) obj;
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.stayType, other.stayType)) {
            return false;
        }
        /*if (!Objects.equals(this.field_C, other.field_C)) {
            return false;
        }*/
        if (!Objects.equals(this.machineId, other.machineId)) {
            return false;
        }
        return true;
    }

}
