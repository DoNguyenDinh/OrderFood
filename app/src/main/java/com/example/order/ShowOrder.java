package com.example.order;

public class ShowOrder {
    int soluong;
    String tenmonan;

    public ShowOrder(int soluong, String tenmonan, int thanhtien) {
        this.soluong = soluong;
        this.tenmonan = tenmonan;
        this.thanhtien = thanhtien;
    }

    public ShowOrder(){

    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getTenmonan() {
        return tenmonan;
    }

    public void setTenmonan(String tenmonan) {
        this.tenmonan = tenmonan;
    }

    public int getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }

    int thanhtien;
}
