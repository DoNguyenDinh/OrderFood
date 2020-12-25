package com.example.order;

public class Menu {
    private int id;
    private String nameFood;
    private String price;

    public void setId(int id) {
        this.id = id;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public Menu() {
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Menu(int id, String nameFood, String price) {
        this.id = id;
        this.nameFood = nameFood;
        this.price = price;
    }

    public Menu(String nameFood, String price) {
        this.nameFood = nameFood;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getNameFood() {
        return nameFood;
    }

    public String getPrice() {
        return price;
    }
}
