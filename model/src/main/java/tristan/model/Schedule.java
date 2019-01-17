package tristan.model;
import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Schedule {


    private int scheduleID;

    private Date startTime;

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