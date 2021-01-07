package com.example.order;

public class Order {

    private int id;
    private int idTable;

    public int getId() {
        return id;
    }

    public Order(int idTable) {
        this.idTable = idTable;
    }

    public Order() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public Order(int id, int idTable) {
        this.id = id;
        this.idTable = idTable;
    }
}
