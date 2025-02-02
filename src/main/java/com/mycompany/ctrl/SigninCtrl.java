/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author trainer
 */
public class SigninCtrl {

    public boolean isLogedIn(HttpServletRequest request) {

        if (request.isUserInRole("chef") || request.isUserInRole("user")) {
            return true;
        }

        return false;
    }

    public static void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.getSession().invalidate(); // Destroy session
            request.logout(); // Logs out the user
            response.sendRedirect(request.getContextPath() + "/"); // Redirect after logout
        } catch (ServletException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/WEB-INF/error.jsp"); // Redirect in case of an error
        }
    }

}
