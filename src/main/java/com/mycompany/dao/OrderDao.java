/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.model.Order;
import com.mycompany.ctrl.DatabaseController;
import com.mycompany.model.FoodItem;
import com.mycompany.model.UserData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDao extends DatabaseController {

    public ArrayList<Order> getOrders() {
        ArrayList<Order> orders = new ArrayList<>();

        try (Connection con = getConnection()) {
            String query = "SELECT u.id as user_id, u.firstname, u.lastname, u.adress, p.id as order_id, p.status, "
                    + "oi.name, oi.amount, oi.price "
                    + "FROM user u "
                    + "JOIN pizzaorder p ON u.id = p.userID "
                    + "JOIN orderitem oi ON p.id = oi.orderID";

            try (PreparedStatement stmt = con.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

                Map<Integer, Order> orderMap = new HashMap<>();

                while (rs.next()) {
                    int orderId = rs.getInt("order_id");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String adress = rs.getString("adress");
                    String itemName = rs.getString("name");
                    int amount = rs.getInt("amount");
                    double price = rs.getDouble("price");

                    // Create UserData object
                    UserData customer = new UserData();
                    customer.setFirstname(firstname);
                    customer.setLastname(lastname);
                    customer.setAdress(adress);

                    Order order = orderMap.get(orderId);
                    if (order == null) {
                        order = new Order();
                        order.setCustomer(customer);
                        order.setOrderItems(new ArrayList<FoodItem>());
                        orderMap.put(orderId, order);

                    }

                    // Create FoodItem and add to the current Order's list of items
                    FoodItem foodItem = new FoodItem();
                    foodItem.setName(rs.getString("name"));
                    foodItem.setPrice(rs.getDouble("price"));
                    order.getOrderItems().add(foodItem);

                    // Update the order price
                    order.setOrderPrice(20.00);

                }
                orders.addAll(orderMap.values());

            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly
        }

        return orders;
    }

    public void save(Order order) {

        System.out.println("SAVE TO DB");

        UserData customer = order.getCustomer();
        int userId = 0;
        int orderId = 0;

        String query = "INSERT INTO USER (firstname, lastname, adress) VALUES (?, ?, ?)";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // Prepare the statement and enable returning generated keys
            pstmt = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // Set the parameters
            pstmt.setString(1, customer.getFirstname());
            pstmt.setString(2, customer.getLastname());
            pstmt.setString(3, customer.getAdress());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                userId = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        query = "INSERT INTO pizzaorder (userID, status) VALUES (?, ?)";
        try {
            // Prepare the statement and enable returning generated keys
            pstmt = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // Set the parameters
            pstmt.setInt(1, userId);
            pstmt.setString(2, "delivered");

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                orderId = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        ArrayList<FoodItem> orderItems = order.getOrderItems();
        query = "INSERT INTO orderitem (name, price, amount, orderID) VALUES (?, ?, ?, ?)";

        for (FoodItem orderItem : orderItems) {
            try {
                // Prepare the statement and enable returning generated keys
                pstmt = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                // Set the parameters
                pstmt.setString(1, orderItem.getName());
                pstmt.setDouble(2, orderItem.getPrice());
                pstmt.setInt(3, 1);
                pstmt.setInt(4, orderId);

                pstmt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pstmt != null) {
                        pstmt.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

}
