/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.ctrl;

import com.mycompany.model.FoodItem;
import com.mycompany.model.Order;
import com.mycompany.model.UserData;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.github.javafaker.Faker;
import com.mycompany.dao.orderDao;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author Nicola
 */
@WebServlet(name = "Controller", urlPatterns = {"", "/admin"})
public class Controller extends HttpServlet {

    private orderDao orderDao;

    @Override
    public void init() throws ServletException {

        super.init();
        orderDao = new orderDao();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String page = request.getParameter("page");

        if (page == null || page.equals("foodItems")) {

            if (getServletContext().getAttribute("menu") == null || (request.getParameter("action") != null && request.getParameter("action").equals("changeMenu"))) {
                getServletContext().setAttribute("menu", this.getMenu());
            }

            this.getFoodItemsFromOrder(request);

            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/foodItems.jsp");
            dispatcher.forward(request, response);
        } else if (page.equals("userData")) {

            this.addFoodItemsToOrder(request);

            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/userData.jsp");
            dispatcher.forward(request, response);
        } else if (page.equals("finish")) {

            this.addUserDataToOrder(request);

            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/finish.jsp");
            dispatcher.forward(request, response);
        } else if (page.equals("end")) {

            orderDao.save((Order) request.getSession().getAttribute("order"));
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/end.jsp");
            dispatcher.forward(request, response);
        } else if (page.equals("orderOverview")) {

            ArrayList<Order> orders = orderDao.getOrders();
            request.getSession().setAttribute("orders", orders);
            out.print(orders.toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/orderOverview.jsp");
            dispatcher.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Aufgabe Java Web Grundlagen 1. Woche PizzaService\n"
                + "Erstellen Sie eine Reihe von Webformularen, welche zur Essensbestellung über einen Pizzadienst\n"
                + "genutzt werden können. Erstellen Sie dazu ein dynamisches Startformular, und fügen Sie später\n"
                + "über eine JSP einzelne Positionen in eine Bestellung ein. Dann lösen Sie den Bestellvorgang aus.\n"
                + "Sie erhalten danach eine Rechnung, die wiederum dynamisch erzeugt wird.\n"
                + "Es sollte ein Katalog von mindestens 6 verschiedenen Gerichten auf dem Eingangsformular\n"
                + "bereitgestellt werden(Pizza Napoli, Pizza Frutti di Mare, etc.). Die Anzahl der jeweiligen Gerichte\n"
                + "können als Texteingabefelder realisiert werden.\n"
                + "Beispiel: 2 x Pizza Napoli und 3 x Spaghetti sollen an die Adresse\n"
                + "Hans Wurst\n"
                + "Senterweg 22\n"
                + "1234 Lindendorf\n"
                + "zum Gesamtpreis von 12,60 geliefert werden.\n"
                + "Die Daten sollen in eine Collection gespeichert werden. Sie benötigen ein Objekt für den Kunden in\n"
                + "dem die vollständigen Adressdaten angegeben werden.Ein Objekt für die Speisen, als auch ein\n"
                + "Objekt für die Bestellungen in dem, neben den bestellten Speise(n) auch Ip-Adresse und Session-Id\n"
                + "und Kunde mit abgelegt werden.";
    }// </editor-fold>

    public ArrayList<FoodItem> getMenu() {
        Faker faker = new Faker(new Locale("de"));
        ArrayList<FoodItem> menu = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            FoodItem item = new FoodItem(faker.food().dish().toString(), Double.parseDouble(faker.commerce().price(5, 15)));
            menu.add(item);
        }
        return menu;
    }

    private FoodItem getSingleItem() {
        Faker faker = new Faker(new Locale("de"));
        FoodItem item = new FoodItem(faker.food().dish().toString(), Double.parseDouble(faker.commerce().price()));
        return item;
    }

    public static String trimToLength(String input, int maxLength) {
        if (input == null) {
            return null;
        }
        return input.length() > maxLength ? input.substring(0, maxLength) : input;
    }

    private void addFoodItemsToOrder(HttpServletRequest request) {
        if (request.getParameter("foodItems[].name") != null && request.getParameter("foodItems[].name").length() > 0) {
            Order currentOrder = new Order();
            String[] names = request.getParameterValues("foodItems[].name");
            String[] prices = request.getParameterValues("foodItems[].price");
            String[] counts = request.getParameterValues("foodItems[].count");

            String total = request.getParameter("total");

            ArrayList<FoodItem> orderItems = new ArrayList<>();

            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                String price = prices[i];
                int count = Integer.parseInt(counts[i]);

                for (int j = 0; j < count; j++) {
                    orderItems.add(new FoodItem(name, Double.parseDouble(price)));
                }

            }

            currentOrder.setOrderItems(orderItems);
            currentOrder.setOrderPrice(Double.parseDouble(total));
            request.getSession().setAttribute("order", currentOrder);
        }
    }

    private void getFoodItemsFromOrder(HttpServletRequest request) {
        Order currentOrder = (Order) request.getSession().getAttribute("order");
        Map<String, Integer> itemCounts = new HashMap<>();
        if (currentOrder != null) {
            for (FoodItem item : currentOrder.getOrderItems()) {
                itemCounts.put(item.getName(), itemCounts.getOrDefault(item.getName(), 0) + 1);
            }
        }
        request.getSession().setAttribute("itemCounts", itemCounts);
    }

    private void addUserDataToOrder(HttpServletRequest request) {
        if (request.getParameter("firstname") != null && !request.getParameter("firstname").trim().isEmpty()) {

            UserData currentUser = new UserData(request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("adress"), request.getSession().getId(), request.getRemoteAddr());

            Order order = (Order) request.getSession().getAttribute("order");
            order.setCustomer(currentUser);

            request.getSession().setAttribute("currentUser", currentUser);
        }
    }

    private void forwardToJSP(HttpServletRequest request, HttpServletResponse response, String pageName) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/" + pageName);
        dispatcher.forward(request, response);
    }
}
