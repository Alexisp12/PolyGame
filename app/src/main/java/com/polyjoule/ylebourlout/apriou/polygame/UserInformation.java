package com.polyjoule.ylebourlout.apriou.polygame;

/**
 * Created by Alexis on 20/07/2017.
 */

public class UserInformation {
    public String pseudo=null;
    public int highScore=-1;
    public String email;

    public UserInformation(){

    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String getPseudo(){
        //if(pseudo==null) return null;
        return pseudo;
    }
    public int getHighScore(){
        return highScore;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }


    public int compare(UserInformation uI1, UserInformation uI2) {
        return new Integer(uI1.getHighScore()).compareTo(uI2.getHighScore());
    }
}
