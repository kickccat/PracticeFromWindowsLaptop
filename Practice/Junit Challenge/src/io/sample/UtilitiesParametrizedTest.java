package io.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

class UtilitiesParametrizedTest {
    
    private static Utilities utilities;
    
    @BeforeAll
    static void setup() {
        utilities = new Utilities();
    }
    
    private static Collection<Object> testConditions() {
        return Arrays.asList(new Object[][]{
                {"ABCDEFF", "ABCDEF"},
                {"AB88EFFG", "AB8EFG"},
                {"112233445566", "123456"}
        });
    }
    
    @DisplayName("Parameter test...")
    @ParameterizedTest
    @MethodSource("testConditions")
    void removePairs(String input, String output) {
        Assertions.assertEquals(output, utilities.removePairs(input));
    }
}
