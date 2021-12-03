package usecases;

import entities.Student;
import entities.User;
import entities.Faculty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserCommands implements Serializable {
    private final User USER;

    /**
     * instantiate usercommands from the userList given, create a new user in UserCommands with the given user info
     * @param userList is a list of information for a certain user
     */
    public UserCommands(List<String> userList) {
        this.USER = this.createUser(userList);
    }

    /**
     * shows the profile of the user in this UserCommands
     * @return a string of the user's information
     */
    public String showProfile(){
        return this.USER.displayProfile();
    }

    /**
     * create a new user given the information passed in. if user is student, create new student, else create new
     * faculty member
     * @param userInfo an array list of the information of the user
     * @return a User object
     */
    public User createUser(List<String> userInfo) {
        if (userInfo.get(4).equals("student")) {
            return new Student(userInfo);
        }
        return new Faculty(userInfo);
    }

    /**
     * get the information of the user in an arraylist format
     * @return an array list of the user in this UserCommands
     */
    public List<String> getInfo(){
        return this.USER.getUserInfo();
    }

    /**
     * changes the password for this user in this UserCommands
     * @param oldPassword the old password of the user
     * @param newPassword the new password of the user that they want to change to
     */
    public void changePassword(String oldPassword, String newPassword){
        if (this.USER.checkPassword(oldPassword)){
            this.USER.changePassword(newPassword);
        }
    }

    public void changePicture(String newPicture){
        this.USER.changePicture(newPicture);
    }

    /**
     * get the user of this UserCommands
     * @return an User object
     */
    public User getUser(){
        return this.USER;
    }
}
