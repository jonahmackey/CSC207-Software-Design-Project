package java;

import java.util.ArrayList;

/**
 * Class student is a child class of User, it initializes an instance of
 * student user class, stores student data and profile, T card, unique ID
 *
 *
 *
 */

public class Student extends User{
    public Student(ArrayList<String> info) {
        super(info);
    }

    /**
     *   The first item in accesses should be the program of the student.
     */
    @Override
    public String profileDisplay() {
        return "User Name: " + this.profile.get(1) + "\n" + "Status: " + this.profile.get(2) + "\n"
                + "Program: " + this.accesses.get(0) + "\n" + "Qualification: " + this.accesses;
    }
    @Override
    public ArrayList<String> getProfile(){
        return this.profile;
    }

    @Override
    public String getId(){
        return this.profile.get(1);
    }
}
