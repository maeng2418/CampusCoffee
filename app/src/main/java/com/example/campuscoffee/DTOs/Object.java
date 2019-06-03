package com.example.campuscoffee.DTOs;

public class Object {
    public int store;
    public String menu;
    public int menuId;
    public int price;
    public int count;
    public String option;

    public Object(int store, String menu, int menuId, int price, int count, String option){
        this.store = store;
        this.menu = menu;
        this.menuId = menuId;
        this.price = price;
        this.option = option;
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public void setOption(String option) {
        this.option = option;
    }
}
