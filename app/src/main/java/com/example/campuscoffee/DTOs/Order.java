package com.example.campuscoffee.DTOs;


public class Order {
    public int id;
    public String buyer;
    public int count;
    public String option;
    public String price;
    public int store;
    public OrderMenu menu;
    public int progress;

    public int getProgress() {
        return progress;
    }
    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getBuyer() { return buyer; }
    public void setBuyer(String buyer) { this.buyer = buyer; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public String getOption() { return option; }
    public void setOption(String option) { this.option = option; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public int getStore() { return store; }
    public void setStore(int store) { this.store = store; }

    public OrderMenu getMenu() { return menu; }
    public void setMenu(OrderMenu menu) { this.menu = menu; }


}
