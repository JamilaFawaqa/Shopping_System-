package com.example.jlmart;

public class LogInSession {
    private  static  final LogInSession instance = new LogInSession();
    private int theUserID;
    private String username;


    public static LogInSession getInstance(){
        return  instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTheUserID(){
        return theUserID;
    }

    public void setTheUserID(int theUserID) {
        this.theUserID = theUserID;
    }

}
