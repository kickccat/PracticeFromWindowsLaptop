package io.sample.rabinkarp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RabinKarpTest {
    
    private static RabinKarp rk = null;
    
    @BeforeAll
    static void init() {
        rk = new RabinKarp();
    }
    
    @Test
    void searchTest() {
        Assertions.assertEquals(3, rk.search("acbacc".toCharArray(), "acc".toCharArray()));
        Assertions.assertEquals(4, rk.search("learning".toCharArray(), "nin".toCharArray()));
        Assertions.assertEquals(-1, rk.search("learning".toCharArray(), "nina".toCharArray()));
        Assertions.assertEquals(-1, rk.search(null, "nina".toCharArray()));
    }
    
    @Test
    void matchTest() {
        Assertions.assertTrue(rk.match("learning".toCharArray(), "nin".toCharArray(), 4));
        Assertions.assertFalse(rk.match("learning".toCharArray(), "ing".toCharArray(), 4));
    }
    
    @Test
    void recalculateHashTest() {
        Assertions.assertEquals(18L, rk.recalculateHash(28L, 'a', 'a', 3));
    }
    
    @Test
    void calculateHashTest() {
        Assertions.assertEquals(28L, rk.calculateHash("acbacc".toCharArray(), 3));
    }
    
    @Test
    void charValTest() {
        Assertions.assertEquals(97, 'a');
        Assertions.assertEquals(1, rk.charVal('a'));
    }
}
