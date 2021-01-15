package com.example.order;

public class TypeFood {
    public TypeFood(String nameFoodStyle) {
        this.nameFoodStyle = nameFoodStyle;
    }

    public TypeFood(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameFoodStyle() {
        return nameFoodStyle;
    }

    public void setNameFoodStyle(String nameFoodStyle) {
        this.nameFoodStyle = nameFoodStyle;
    }

    private int id;
    private String nameFoodStyle;
}
