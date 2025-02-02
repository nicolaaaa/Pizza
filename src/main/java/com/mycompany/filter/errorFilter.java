//package com.mycompany.filter;
//
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
// */
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import java.io.IOException;
//
//@WebFilter("/*")
//public class ErrorFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        try {
//            chain.doFilter(request, response);
//        } catch (Exception e) {
//            request.setAttribute("error", e);
//            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
//        }
//    }
//}
