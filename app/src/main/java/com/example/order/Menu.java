package com.example.order;

public class Menu {
    private String nameFood;
    private int photo;
    private String price;

    public Menu(String nameFood, int photo, String price) {
        this.nameFood = nameFood;
        this.photo = photo;
        this.price = price;
    }

    public Menu() {
    }


    public String getNameFood() {
        return nameFood;
    }

    public int getPhoto() {
        return photo;
    }

    public String getPrice() {
        return price;
    }


}
