package io.sample.euclid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EuclidTest {
    
    private Euclid euclid = null;
    
    @BeforeEach
    void init() {
        euclid = new Euclid();
    }
    
    @Test
    void gcdTest() {
        Assertions.assertEquals(2, euclid.gcd(10, 2));
        Assertions.assertEquals(2, euclid.gcd(22, 6));
    }
    
    @Test
    void gcd2Test() {
        Assertions.assertEquals(2, euclid.gcd2(10, 2));
        Assertions.assertEquals(2, euclid.gcd2(22, 6));
    }
    
    @Test
    void gcdVsGcd2Test() {
        Assertions.assertEquals(euclid.gcd(182,74), euclid.gcd2(182,74));
        Assertions.assertEquals(euclid.gcd(22,6), euclid.gcd2(22,6));
        Assertions.assertEquals(euclid.gcd(10,2), euclid.gcd2(10,2));
    }
}
