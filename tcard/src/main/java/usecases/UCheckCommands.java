package usecases;

import entities.UCheck;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class UCheckCommands implements Serializable {

    private final String USER;
    private final UCheck UCHECK;

    public UCheckCommands(String utorid) {
        this.USER = utorid;
        this.UCHECK = new UCheck(this.USER);

    }
    /**
     * @param status if User passes UCheck self-assessment, update it's corresponding UCheck information.
     */
    public void setUCheck(boolean status) {
        if (status){
           this.UCHECK.setUCheckTime();
        }
    }
    /**
     * @return Date is current time of User's UCheck for UI display.
     */
    public Date getUCheckTime(){
        return this.UCHECK.getUCheckTime();
    }

    /**
     * This method interacts with UCheckScrollingActivity's results of the self assessment. If it's true, additional
     * methods will call to form successful completion of self-assessment questionnaire.
     *
     * @param responses is a String of responses completed by the self-assessment.
     * @return boolean on whether the self-assessment is success or failure.
     */
    public boolean ucheckResponses(ArrayList<String> responses){
        if (Objects.equals(responses.get(0), "yes")){
             for (int i = 1; i < 8; i++)
                 if(Objects.equals(responses.get(i), "no")){
                     return true;
            }
        }
        return false;
    }
}