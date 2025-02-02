/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.model.FoodItem;
import com.mycompany.model.Order;
import com.mycompany.model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Nicola
 */
public class OrderDaoTest {

    public OrderDaoTest() {
    }

    private OrderDao orderDao;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        orderDao = Mockito.spy(new OrderDao()); // Create a spy of OrderDao
        doReturn(mockConnection).when(orderDao).getConnection(); // Mock DB connection
    }

    @Test
    void testGetOrders() throws Exception {
        // Mock ResultSet
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true, false); // 1 row, then stop
        when(mockResultSet.getInt("order_id")).thenReturn(1);
        when(mockResultSet.getString("firstname")).thenReturn("John");
        when(mockResultSet.getString("lastname")).thenReturn("Doe");
        when(mockResultSet.getString("adress")).thenReturn("123 Main St");
        when(mockResultSet.getString("name")).thenReturn("Pizza");
        when(mockResultSet.getInt("amount")).thenReturn(2);
        when(mockResultSet.getDouble("price")).thenReturn(15.99);

        // Call method
        List<Order> orders = orderDao.getOrders();

        // Assertions
        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertEquals("John", orders.get(0).getCustomer().getFirstname());
        assertEquals("Pizza", orders.get(0).getOrderItems().get(0).getName());
    }

    @Test
    void testSave() throws Exception {
        // Prepare dummy order data
        Order order = new Order();
        UserData customer = new UserData();
        customer.setFirstname("Alice");
        customer.setLastname("Smith");
        customer.setAdress("456 Elm St");
        order.setCustomer(customer);

        FoodItem item = new FoodItem();
        item.setName("Burger");
        item.setPrice(10.99);
        ArrayList<FoodItem> items = new ArrayList<>();
        items.add(item);
        order.setOrderItems(items);

        // Mocking behavior for inserting user
        when(mockConnection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS)))
                .thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(100); // Mock user ID and order ID

        // Call method
        orderDao.save(order);

        // Verify statements were executed
        verify(mockPreparedStatement, atLeastOnce()).executeUpdate();
    }

}
