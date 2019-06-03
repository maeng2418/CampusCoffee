package com.example.campuscoffee.DTOs;

public class Menu {
    public int id;
    public String created_at;
    public String updated_at;
    public int count;
    public String option;
    public int price;
    public int creator;
    public int store;
    public int menu;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public String getOption() {
        return option;
    }
    public void setOption(String option) {
        this.option = option;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) { this.price = price; }

    public int getCreator() { return creator; }
    public void setCreator(int creator) { this.creator = creator; }

    public int getStore() { return store; }
    public void setStore(int store) { this.store = store; }

    public int getMenu() { return menu; }
    public void setMenu(int menu) { this.menu = menu; }

}
