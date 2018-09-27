package io.sample.kmp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KnuthMorrisPrattTest {
    
    private KnuthMorrisPratt kmp = null;
    
    @BeforeEach
    void setUp() {
        kmp = new KnuthMorrisPratt();
    }
    
    @Test
    void computeLSPTable() {
        int[] actual = kmp.computeLSPTable(new char[]{'a', 'b', 'a', 'b', 'a', 'c'});
        int[] expect = new int[]{0, 0, 1, 2, 3, 0};
        Assertions.assertArrayEquals(expect, actual);
    
        actual = kmp.computeLSPTable(new char[]{'a', 'a', 'b', 'a', 'c'});
        expect = new int[]{0, 1, 0, 1, 0};
        Assertions.assertArrayEquals(expect, actual);
        
        actual = kmp.computeLSPTable(new char[]{'a', 'b', 'c', 'd', 'a', 'b', 'c', 'a'});
        expect = new int[]{0, 0, 0, 0, 1, 2, 3, 1};
        Assertions.assertArrayEquals(expect, actual);
    }
    
    @Test
    void search() {
        String array = "abazacabababac";
        String pattern = "ababac";
    
        int index = kmp.search(array.toCharArray(), pattern.toCharArray());
        Assertions.assertEquals(8, index);
    }
}
