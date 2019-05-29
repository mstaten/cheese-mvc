package org.launchcode.cheesemvc.models;

import java.util.ArrayList;

public class UserData {

    static ArrayList<User> users = new ArrayList<>();

    // get all
    public static ArrayList<User> getAll() {
        return users;
    }

    // add
    public static void add(User newUser) {
        users.add(newUser);
    }

    // get by id
    public static User getById(int id) {
        User theUser = null;

        for (User aUser : users) {
            if (aUser.getUserId() == id) {
                theUser = aUser;
            }
        }

        return theUser;
    }


}
