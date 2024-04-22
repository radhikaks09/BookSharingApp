package com.example.booksharingapp.ui.profile;

public class UserProfileData {
    private String username;
    private String emailId;
    public UserProfileData(String username, String emailId){
        this.username = username;
        this.emailId = emailId;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setEmailId(String emailId){
        this.emailId = emailId;
    }
    public String getUsername(){
        return this.username;
    }
    public String getEmailId(){
        return this.emailId;
    }
}
