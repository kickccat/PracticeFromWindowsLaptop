package de.is2.mtext.soap.demo.Junit5;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestSuite1 {
    
    @BeforeClass
    static void beforeClass() {
        System.out.println("The before class method");
    }
    
    @BeforeEach
    void before() {
        System.out.println("The before method");
    }
    
    @DisplayName("Run the 1st test")
    @Test
    void test1() {
        System.out.println("The test 1");
    }
    
    @Test
    void test2() {
        System.out.println("The test 2");
    }
    
    @AfterEach
    void after() {
        System.out.println("The after method");
    }
    
    @AfterClass
    void afterClass() {
        System.out.println("The after class method");
    }
}
