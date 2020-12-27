package com.example.order;

public class Staff {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Staff(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Staff(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    private String userName;
    private String password;

    public Staff(){

    }
}
