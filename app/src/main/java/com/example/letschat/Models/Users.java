package com.example.letschat.Models;

public class Users {
    String profilepic, userName, mail,password,lastMessage,userid;

    public Users(String profilepic, String userName, String mail, String password, String lastMessage, String userid) {
        this.profilepic = profilepic;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.lastMessage = lastMessage;
        this.userid = userid;
    }
    public Users(){}
    public Users( String userName, String mail, String password) {

        this.userName = userName;
        this.mail = mail;
        this.password = password;}

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public String getUserName() {
        return userName;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getLastMessage() {
        return lastMessage;
    }



}
