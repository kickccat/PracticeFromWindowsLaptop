package io.sample.kmp;

public class KnuthMorrisPratt {
    
    /**
     * This method returns the index for the first match of the pattern into the array
     * It will return -1 if not match
     *
     * <pre>
     *                     i
     *     0 1 2 3 4 5 6 7 8 9 10 11 12 13
     *     a b a z a c a b a b a  b  a  c
     *
     *                     a b a  b  a  c
     *                     0 0 1  2  3  0
     *                                  j
     * This sample returns index 8
     * </pre>
     *
     * @param array
     * @param pattern
     * @return
     */
    public int search(char[] array, char[] pattern) {
        int[] lsp = computeLSPTable(pattern);
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            while (j > 0 && array[i] != pattern[j]) {
                j = lsp[j - 1];
            }
            if (array[i] == pattern[j]) {
                j++;
                if (j == pattern.length) {
                    return i - (j - 1);
                }
            }
        }
        return -1;
    }
    
    /**
     * Compute the longest suffix and prefix on the pattern
     *
     * @param pattern
     * @return
     */
    int[] computeLSPTable(char[] pattern) {
        int[] lsp = new int[pattern.length];
        int j = 0;
        for (int i = 1; i < pattern.length; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = lsp[j - 1];
            }
            if (pattern[i] == pattern[j]) {
                lsp[i] = j + 1;
                j++;
            } else {
                lsp[i] = 0;
            }
        }
        return lsp;
    }
}
