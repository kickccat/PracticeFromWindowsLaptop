package io.sample.euclid;

class Euclid {
    
    /**
     * Implementation using recursion
     *
     * @param number
     * @param divisor
     * @return
     */
    int gcd(int number, int divisor) {
        int remaining = number % divisor;
        
        if (remaining != 0) {
            return gcd(divisor, remaining);
        } else {
            return divisor;
        }
    }
    
    /**
     * Implementation without recursion
     *
     * @param number
     * @param divisor
     * @return
     */
    int gcd2(int number, int divisor) {
        while (divisor != 0) {
            int temp = divisor;
            divisor = number % divisor;
            number = temp;
        }
        return number;
    }
}
