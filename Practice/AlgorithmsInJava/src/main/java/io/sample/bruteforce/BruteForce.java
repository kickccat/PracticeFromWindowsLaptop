package io.sample.bruteforce;

import java.util.Arrays;

/**
 * Brute Force Algorithm
 *
 * It will search for a pattern in an array if the pattern will be found, it will return the index of the first found part.
 * If no match return -1
 */
public class BruteForce {
    
    public int firstMatch(char[] array, char[] pattern) {
        for (int a = 0; a <= array.length - pattern.length; a++) {
            for (int p = 0; p < pattern.length; p++) {
                if (array[a + p] != pattern[p]) {
                    break;
                }
                if (p == pattern.length - 1) {
                    return a;
                }
            }
        }
        return -1;
    }
    
    /**
     * This method searches for pattern in an array and return first index of the all matched found elements
     *
     * @param array
     * @param pattern
     * @return
     */
    public int[] everyMatch(char[] array, char[] pattern) {
        int[] found = new int[array.length];
        int index = 0;
        Arrays.fill(found, -1);
        
        for (int a = 0; a <= array.length - pattern.length; a++) {
            for (int p = 0; p < pattern.length; p++) {
                if (array[a + p] != pattern[p]) {
                    break;
                }
                if (p == pattern.length - 1) {
                    found[index++] = a;
                }
            }
        }
        return found;
    }
}
