package io.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

class BankAccountParameterizedTest {
    
    private BankAccount account;

    @BeforeEach
    void setup() {
        account = new BankAccount("Joey", "Joy", 1000.00, BankAccount.CHECKING);
        System.out.println("Running a test...");
    }

    private static Collection<Object[]> testConditions() {
        return Arrays.asList(new Object[][] {
                {100.00, true, 1100.00},
                {200.00, true, 1200.00},
                {340.00, true, 1340.00},
                {489.33, true, 1489.33},
                {1000.00, true, 2000.00}
        });
    }

    @org.junit.jupiter.params.ParameterizedTest
    @MethodSource("testConditions")
    @DisplayName("Testing get balance with deposit...")
    void getBalance_deposit(double amount, boolean branch, double expected) {
        account.deposit(amount, branch);
        Assertions.assertEquals(expected, account.getBalance());
    }
}
