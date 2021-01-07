package com.example.order;

public class OrderDetail {

    private int idOrder;

    public OrderDetail(int idOrder, int idFood, int quantityFood) {
        this.idOrder = idOrder;
        this.idFood = idFood;
        this.quantityFood = quantityFood;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public int getQuantityFood() {
        return quantityFood;
    }

    public void setQuantityFood(int quantityFood) {
        this.quantityFood = quantityFood;
    }

    private int idFood;
    private int quantityFood;

}
