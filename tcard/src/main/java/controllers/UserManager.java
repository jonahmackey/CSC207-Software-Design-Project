package controllers;

import entities.User;
import usecases.RegisterUseCase;
import usecases.UserCommands;
import usecases.UserReadWriter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class UserManager implements Serializable {
    private UserCommands myUserCommands;
    final Map<UserCommands.PasswordUpdateResult, String> PASSWORD_UPDATE_MESSAGES = new EnumMap<>(UserCommands.PasswordUpdateResult.class);

    /**
     * create a new UserManager creates a new UserCommands
     * @param userInfo an arraylist of the user's information to make the UserCommands
     */
    public UserManager(List<String> userInfo){
        this.myUserCommands = new UserCommands(userInfo);
        PASSWORD_UPDATE_MESSAGES.put(UserCommands.PasswordUpdateResult.EMPTY_FIELD, "Please fill all the fields");
        PASSWORD_UPDATE_MESSAGES.put(UserCommands.PasswordUpdateResult.OLD_PASSWORD_WRONG, "Your old password is wrong");
        PASSWORD_UPDATE_MESSAGES.put(UserCommands.PasswordUpdateResult.NEW_SAME_AS_OLD, "Your new password is the same as the old one");
        PASSWORD_UPDATE_MESSAGES.put(UserCommands.PasswordUpdateResult.NEW_ATTEMPTS_DONT_MATCH, "Your new password attempts don't match");
        PASSWORD_UPDATE_MESSAGES.put(UserCommands.PasswordUpdateResult.SUCCESS, "Password successfully changed");

    }

    /**
     * get the info for the UserCommands for the user
     * @return an arraylist of the user's information
     */
    public List<String> getInfo(){
        return this.myUserCommands.getInfo();
    }

    /**
     * get the user for the UserCommands for this UserManager
     * @return a User object
     */
    public User getUser(){
        return myUserCommands.getUser();
    }

    public String changePassword(String oldPass, String newPass, String newPassReEntry) {
        UserCommands.PasswordUpdateResult passwordUpdateResult = this.myUserCommands.changePassword(oldPass, newPass, newPassReEntry);
        return PASSWORD_UPDATE_MESSAGES.get(passwordUpdateResult);
    }

    public void changePicture(String newPic) {
        this.myUserCommands.changePicture(newPic);
    }
}
