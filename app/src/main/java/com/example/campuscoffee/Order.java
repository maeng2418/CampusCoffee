package com.example.campuscoffee;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Order {
    public int id;
    public String created_at;
    public String updated_at;
    public String buyer;
    public int count;
    public String option;
    public String price;
    public boolean timer;
    public int store;
    public OrderMenu menu;


    public boolean getTimer() {
        return timer;
    }

    public void setTimer(boolean timer) {
        this.timer = timer;
    }

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

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public OrderMenu getMenu() {
        return menu;
    }
    public void setMenu(OrderMenu menu) {
        this.menu = menu;
    }


}
