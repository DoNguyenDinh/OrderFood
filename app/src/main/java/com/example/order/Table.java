package com.example.order;

public class Table {
    private int id;

    public Table(int id, String nameTable) {
        this.id = id;
        this.nameTable = nameTable;
    }

    public Table() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    private String nameTable;

    public int getId() {
        return id;
    }

    public String getNameTable() {
        return nameTable;
    }

    public Table(String nameTable) {
        this.nameTable = nameTable;
    }
}
