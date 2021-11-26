package entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class UCheck implements Serializable {

    private boolean ucheck; // is valid or not.
    private Date uchecktime; // Time of start.
    private final String utorid; // student number.

    /**
     * UCheck Initialization.
     */
    public UCheck(String utorid){
        this.ucheck = false;
        this.utorid= utorid;

    }
    /**
     * Initiates timer
     */
    public void setUCheckTime() {
        this.ucheck = true;
        this.uchecktime = new Date();
    }
    /**
     * @return current time of UCheck status.
     */
    public Date getUCheckTime(){
        return this.uchecktime;
    }
    /**
     * @return String representation of User's attached utorid ID.
     */
    public String getUTorId() {
        return utorid;
    }
    /**
     * @return boolean if its UCheck.
     */
    public boolean isUCheck() {
        return ucheck;
    }
    /**
     * @return true if the timer is not over.
     */
    public boolean uCheckValid() {
        final long HOUR = 3600 * 1000; // in milli-seconds.
        Date newDate = new Date(this.uchecktime.getTime() + 24 * HOUR);
        return (newDate.getTime() - this.uchecktime.getTime()) < (24 * HOUR);
    }
}