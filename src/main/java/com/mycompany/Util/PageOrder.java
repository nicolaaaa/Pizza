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
    public static String computeNavImageClass(String currentPage, String navElement) {

        int currentIndex = 0;
        if (currentPage == null || currentPage.equals("")) {
            currentIndex = 0;

        } else {
            currentIndex = pages.indexOf(currentPage);

        }
        int pageIndex = pages.indexOf(navElement);

        if (pageIndex == -1) {
            return "disabled"; // Page not found in the order
        }

        if (pageIndex < currentIndex) {
            return "done"; // Already visited
        } else if (pageIndex == currentIndex) {
            return "active"; // Current page
        } else {
            return "disabled"; // Not yet visited
        }
    }

    /**
     * Computes the previous or next page in the navigation order.
     *
     * @param currentPage the current page as a string.
     * @param direction the direction, either "previous" or "next".
     * @return the previous or next page, or null if there's no valid page.
     */
    public static String computeNextPrevPage(String currentPage, String direction) {
        int currentIndex = 0;
        if (currentPage == null || currentPage.equals("")) {
            currentIndex = 0;

        } else {
            currentIndex = pages.indexOf(currentPage);

        }

        // If currentPage is not in the list, return null
        if (currentIndex == -1) {
            return null;
        }

        switch (direction) {
            case "previous":
                if (currentIndex > 0) {
                    return pages.get(currentIndex - 1);
                }
                break;
            case "next":
                if (currentIndex < pages.size() - 1) {
                    return pages.get(currentIndex + 1);
                }
                break;
            default:
                // Invalid direction provided
                throw new IllegalArgumentException("Direction must be 'previous' or 'next'");
        }

        // No valid previous or next page
        return null;
    }

}
