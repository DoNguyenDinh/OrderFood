package com.example.order;

public class Staff {
    private int id;
    private String userName;
    private String password;
    private String nameStaff;

    public Staff(String userName, String password,String namestaff) {
        this.userName = userName;
        this.password = password;
        this.nameStaff=namestaff;
    }

    public int getId() {
        return id;
    }

    public String getNameStaff() {
        return nameStaff;
    }

    public void setNameStaff(String nameStaff) {
        this.nameStaff = nameStaff;
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

    public Staff(){

    }
}
