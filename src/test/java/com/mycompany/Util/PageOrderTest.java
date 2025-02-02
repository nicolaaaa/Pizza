/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.Util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Nicola
 */
public class PageOrderTest {

    public PageOrderTest() {
    }

    /**
     * Test of computeNavImageClass method, of class PageOrder.
     */
    @org.junit.jupiter.api.Test
    public void NavImageClass_shouldOnlyBeActiveForFoodItems_whenGivenFoodItemsPage() {

        //GIVEN
        String currentPage = "foodItems";
        String navElement1 = "foodItems";
        String navElement2 = "userData";
        String navElement3 = "finish";

        //WHEN
        String result1 = PageOrder.computeNavImageClass(currentPage, navElement1);
        String result2 = PageOrder.computeNavImageClass(currentPage, navElement2);
        String result3 = PageOrder.computeNavImageClass(currentPage, navElement3);

        //THEN
        assertAll("Check all navItem classes",
                () -> assertEquals("active", result1),
                () -> assertEquals("disabled", result2),
                () -> assertEquals("disabled", result3)
        );

    }

    @org.junit.jupiter.api.Test
    public void NavImageClass_shouldOnlyBeActiveForuserData_whenGivenuserDataPage() {
        System.out.println("computeNavImageClass");

        //GIVEN
        String currentPage = "userData";
        String navElement1 = "foodItems";
        String navElement2 = "userData";
        String navElement3 = "finish";

        //WHEN
        String result1 = PageOrder.computeNavImageClass(currentPage, navElement1);
        String result2 = PageOrder.computeNavImageClass(currentPage, navElement2);
        String result3 = PageOrder.computeNavImageClass(currentPage, navElement3);

        //THEN
        assertAll("Check all navItem classes",
                () -> assertEquals("done", result1),
                () -> assertEquals("active", result2),
                () -> assertEquals("disabled", result3)
        );
    }

    @org.junit.jupiter.api.Test
    public void NavImageClass_shouldOnlyBeActiveForFinish_whenGivenFinishPage() {
        System.out.println("computeNavImageClass");

        //GIVEN
        String currentPage = "finish";
        String navElement1 = "foodItems";
        String navElement2 = "userData";
        String navElement3 = "finish";

        //WHEN
        String result1 = PageOrder.computeNavImageClass(currentPage, navElement1);
        String result2 = PageOrder.computeNavImageClass(currentPage, navElement2);
        String result3 = PageOrder.computeNavImageClass(currentPage, navElement3);

        //THEN
        assertAll("Check all navItem classes",
                () -> assertEquals("done", result1),
                () -> assertEquals("done", result2),
                () -> assertEquals("active", result3)
        );
    }

    @org.junit.jupiter.api.Test
    public void NavImageClass_shouldBeDoneForAll_whenGivenEndPage() {

        //GIVEN
        String currentPage = "end";
        String navElement1 = "foodItems";
        String navElement2 = "userData";
        String navElement3 = "finish";

        //WHEN
        String result1 = PageOrder.computeNavImageClass(currentPage, navElement1);
        String result2 = PageOrder.computeNavImageClass(currentPage, navElement2);
        String result3 = PageOrder.computeNavImageClass(currentPage, navElement3);

        //THEN
        assertAll("Check all navItem classes",
                () -> assertEquals("done", result1),
                () -> assertEquals("done", result2),
                () -> assertEquals("done", result3)
        );
    }

    /**
     * Test of computeNextPrevPage method, of class PageOrder.
     */
    @org.junit.jupiter.api.Test
    public void NextPrevPage_shouldBeNullUserData_whenGivenFoodItemsPage() {
        //GIVEN
        String currentPage = "foodItems";
        String directionPrev = "previous";
        String directionNext = "next";

        //WHEN
        String result1 = PageOrder.computeNextPrevPage(currentPage, directionPrev);
        String result2 = PageOrder.computeNextPrevPage(currentPage, directionNext);

        //THEN
        assertAll("Check all navPages",
                () -> assertEquals(null, result1),
                () -> assertEquals("userData", result2)
        );
    }

    @org.junit.jupiter.api.Test
    public void NextPrevPage_shouldBeFoodItemsFinish_whenGivenUserDataPage() {
        //GIVEN
        String currentPage = "userData";
        String directionPrev = "previous";
        String directionNext = "next";

        //WHEN
        String result1 = PageOrder.computeNextPrevPage(currentPage, directionPrev);
        String result2 = PageOrder.computeNextPrevPage(currentPage, directionNext);

        //THEN
        assertAll("Check all navPages",
                () -> assertEquals("foodItems", result1),
                () -> assertEquals("finish", result2)
        );
    }

    @org.junit.jupiter.api.Test
    public void NextPrevPage_shouldBeUserDataEnd_whenGivenFinishPage() {
        //GIVEN
        String currentPage = "finish";
        String directionPrev = "previous";
        String directionNext = "next";

        //WHEN
        String result1 = PageOrder.computeNextPrevPage(currentPage, directionPrev);
        String result2 = PageOrder.computeNextPrevPage(currentPage, directionNext);

        //THEN
        assertAll("Check all navPages",
                () -> assertEquals("userData", result1),
                () -> assertEquals("end", result2)
        );
    }
}
