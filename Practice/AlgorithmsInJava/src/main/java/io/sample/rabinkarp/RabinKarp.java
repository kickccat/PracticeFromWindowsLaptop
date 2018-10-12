package io.sample.rabinkarp;

/**
 * Rabin-Karp Algorithm
 * Using rolling hash function to compare the hashcode between pattern and array
 */
class RabinKarp {
    
    private final int prime = 3;
    
    int search(char[] array, char[] pattern) {
        if (array == null || pattern == null) {
            return -1;
        }
        int n = array.length;
        int m = pattern.length;
        int lastChar = n - m;
        long patternHash = calculateHash(pattern, m);
        long arrayHash = calculateHash(array, m);
    
        for (int i = 0; i <= lastChar; i++) {
            if (patternHash == arrayHash && match(array, pattern, i)) {
                return i;
            }
            if (i < lastChar) {
                arrayHash = recalculateHash(arrayHash, array[i], array[i + m], m);
            }
        }
        return -1;
    }
    
    boolean match(char[] array, char[] pattern, int index) {
        for (int i = 0; i < pattern.length; i++) {
            if (array[index + i] != pattern[i]) {
                return false;
            }
        }
        return true;
    }
    
    long recalculateHash(long oldHash, char oldChar, char newChar, int hashSize) {
        long hash = oldHash - charVal(oldChar);
        hash /= prime;
        hash += charVal(newChar) * Math.pow(prime, hashSize - 1);
        return hash;
    }
    
    long calculateHash(char[] text, int hashSize) {
        long hash = 0;
        for (int i = 0; i < hashSize; i++) {
            hash += charVal(text[i]) * Math.pow(prime, i);
        }
        return hash;
    }
    
    int charVal(char c) {
        return c - 96;
    }
}
