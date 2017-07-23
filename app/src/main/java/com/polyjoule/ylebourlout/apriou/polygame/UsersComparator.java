package com.polyjoule.ylebourlout.apriou.polygame;

import java.util.Comparator;

/**
 * Created by Alexis on 21/07/2017.
 */

public class UsersComparator implements Comparator<UserInformation> {


    public int compare(UserInformation uI1, UserInformation uI2) {
        int result = new Integer(uI1.getHighScore()).compareTo(uI2.getHighScore());
        return -result;
    }
}
