/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.util.ArrayList;
import com.mycompany.model.FoodItem;

/**
 *
 * @author Nicola
 */
public class Order {

    public Order() {
    }

    private ArrayList<FoodItem> OrderItems;
    private double OrderPrice;
    private UserData customer;

    public void setCustomer(UserData customer) {
        this.customer = customer;
    }

    public UserData getCustomer() {
        return customer;
    }

    public ArrayList<FoodItem> getOrderItems() {
        return OrderItems;
    }

    public void setOrderItems(ArrayList<FoodItem> OrderItems) {
        this.OrderItems = OrderItems;
    }

    @Override
    public String toString() {
        return "Order{" + "OrderItems=" + OrderItems + ", OrderPrice=" + OrderPrice + '}';
    }

    public double getOrderPrice() {
        return OrderPrice;
    }

    public void setOrderPrice(double OrderPrice) {
        this.OrderPrice = OrderPrice;
    }

}
