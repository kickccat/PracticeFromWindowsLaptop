package io.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilitiesTest {
    
    private static Utilities utilities;
    
    @org.junit.jupiter.api.BeforeAll
    static void setUp() {
        utilities = new Utilities();
        System.out.println("Running a test...");
    }
    
    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
    
    @org.junit.jupiter.api.Test
    void everyNthChar() {
        char[] output = utilities.everyNthChar(new char[]{'h', 'e', 'l', 'l', 'o'}, 2);
        Assertions.assertArrayEquals(new char[]{'e', 'l'}, output);
        char[] output1 = utilities.everyNthChar(new char[]{'h', 'e', 'l', 'l', 'o'}, 8);
        Assertions.assertArrayEquals(new char[]{'h', 'e', 'l', 'l', 'o'}, output1);
    }
    
    @Test
    void removePairs() {
        Assertions.assertEquals("ABCDEF", utilities.removePairs("AABCDDEEFF"));
        Assertions.assertNull(utilities.removePairs(null), "Did not get the null when argument passed null");
        Assertions.assertEquals("A", utilities.removePairs("A"));
        Assertions.assertEquals("", utilities.removePairs(""));
    }
    
    @Test
    void converter() {
        Assertions.assertEquals(300, utilities.converter(10, 5));
    }
    
    @Test
    void converter_arithmeticException() {
        Assertions.assertThrows(ArithmeticException.class, () -> utilities.converter(10, 0));
    }
    
    @Test
    void nullIfOddLength() {
        Assertions.assertNull(utilities.nullIfOddLength("odd"));
        Assertions.assertNotNull(utilities.nullIfOddLength("even"));
    }
}
