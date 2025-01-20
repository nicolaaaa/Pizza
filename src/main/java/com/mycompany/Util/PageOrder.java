/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Util;

import java.util.List;
import java.util.Arrays;

public class PageOrder {

    private static final List<String> pages = Arrays.asList("foodItems", "userData", "finish", "end");

    /**
     * Computes the class for a given page based on the current page.
     *
     * @param currentPage The current active page.
     * @param page The page to compute the class for.
     * @return The computed CSS class (active, done, or disabled).
     */
    public static String computeClass(String currentPage, String page) {
        return "active";
//        int currentIndex = pages.indexOf(currentPage);
//        int pageIndex = pages.indexOf(page);
////        System.out.println(pageIndex);
////        System.out.println(currentIndex);
//
//        if (pageIndex == -1) {
//            return "disabled"; // Page not found in the order
//        }
//
//        if (pageIndex < currentIndex) {
//            return "done"; // Already visited
//        } else if (pageIndex == currentIndex) {
//            return "active"; // Current page
//        } else {
//            return "disabled"; // Not yet visited
//        }
    }
}
