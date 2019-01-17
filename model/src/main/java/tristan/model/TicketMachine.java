package tristan.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.*;

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


    @XmlElementWrapper(name = "schedules")
    @XmlElement(name="schedule", required = true)
    protected List<Schedule> schedule;

    public List<Schedule> getSchedule() {
        if (schedule == null) {
            schedule = new ArrayList<Schedule>();
        }
        return this.schedule;
    }

    public void setSchedule(List<Schedule> schedules) {
        schedule = schedules;
    }

/*    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "scheduleID",
            "startTime",
            "hourlyRate"
    })*/

    //creates the Schedule class inside ticket machine class, can use . to access this class.
/*
    public static class Schedule {

        @XmlElement(required = true)
        @XmlSchemaType(name = "unsignedShort")
        public int scheduleID;

        @XmlElement(required = true)
        private Date startTime;

        @XmlElement(required = true)
        @XmlSchemaType(name = "unsignedShort")
        private double hourlyRate;

        public void setScheduleID(int scheduleID) {
            this.scheduleID = scheduleID;
        }

        public int getScheduleID() {
            return this.scheduleID;
        }
        public Date getStartTime() {
            return this.startTime;
        }

        public double getHourlyRate() {
            return this.hourlyRate;
        }
        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public void setHourlyRate(double hourlyRate) {
            this.hourlyRate = hourlyRate;
        }
    }
*/


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

    public void addSchedule(Schedule schedule) {
        List<Schedule>schedList = getSchedule();
        schedList.add(schedule);
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
