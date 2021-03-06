package com.example.order;

public class Menu {
    private int id;
    private String nameFood;
    private String price;
    private int styleFood;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStyleFood() {
        return styleFood;
    }

    public void setStyleFood(int styleFood) {
        this.styleFood = styleFood;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    private byte[] img;

    public Menu(String nameFood, String price, int styleFood, byte[] img) {
        this.nameFood = nameFood;
        this.price = price;
        this.styleFood = styleFood;
        this.img = img;
    }

    public Menu() {
    }


}
