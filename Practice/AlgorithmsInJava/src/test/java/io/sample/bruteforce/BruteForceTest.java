package io.sample.bruteforce;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class BruteForceTest {
    
    private BruteForce bruteForce = null;
    private char[] array;
    
    @BeforeEach
    void init() {
        bruteForce = new BruteForce();
        String s = "io.sample.kickccat.joey.yizhou";
        array = s.toCharArray();
    }
    
    @Test
    void firstMatchTest() {
        Assertions.assertEquals(0, bruteForce.firstMatch(array, new char[]{'i'}));
        Assertions.assertEquals(10, bruteForce.firstMatch(array, new char[]{'k'}));
        Assertions.assertEquals(15, bruteForce.firstMatch(array, new char[]{'c', 'a', 't', '.', 'j'}));
        Assertions.assertEquals(-1, bruteForce.firstMatch(array, new char[]{'c', 'o', 't', '.', 'y'}));
    }
    
    @Test
    void everyMatchTest() {
        int[] expect = new int[array.length];
        resetExpect(expect);
        expect[0] = 0;
        expect[1] = 11;
        expect[2] = 25;
        int[] found = bruteForce.everyMatch(array, new char[]{'i'});
        Assertions.assertArrayEquals(expect, found);
    }
    
    private void resetExpect(int[] expect) {
        Arrays.fill(expect, -1);
    }
}
