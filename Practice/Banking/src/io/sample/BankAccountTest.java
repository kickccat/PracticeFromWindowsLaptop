package io.sample;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

class BankAccountTest {
    
    private BankAccount account;
    private static int count;
    
    @org.junit.jupiter.api.BeforeAll
    static void beforeClass() {
        System.out.println("This is executes before any test cases. Count = " + count++);
    }
    
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        account = new BankAccount("Joey", "Joy", 1000.00, BankAccount.CHECKING);
        System.out.println("Running a test...");
    }
    
    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.out.println("Count = " + count++);
    }
    
    @AfterAll
    static void afterClass() {
        System.out.println("This executes after any test cases. Count = " + count++);
    }
    
    @org.junit.jupiter.api.Test
    @DisplayName("Testing deposit...")
    void deposit() {
        double balance = account.deposit(200.00, true);
        Assertions.assertEquals(1200, balance);
    }
    
    @org.junit.jupiter.api.Test
    @DisplayName("Testing withdraw...")
    void withdraw() {
        double balance = account.withdraw(600.00, true);
        Assertions.assertEquals(400.00, balance);
    }
    
    @org.junit.jupiter.api.Test
    @DisplayName("Testing withdraw not at the branch...")
    void withdraw_NoBranch() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            double balance = account.withdraw(600.00, false);
            Assertions.assertEquals(400.00, balance);
        });
    }
    
    @org.junit.jupiter.api.Test
    @DisplayName("Testing get balance with deposit...")
    void getBalance_deposit() {
        account.deposit(200.00, true);
        Assertions.assertEquals(1200.00, account.getBalance());
    }
    
    @org.junit.jupiter.api.Test
    @DisplayName("Testing get balance with withdraw...")
    void getBalance_withdraw() {
        account.withdraw(200.00, true);
        Assertions.assertEquals(800.00, account.getBalance());
    }
    
    @org.junit.jupiter.api.Test
    void isChecking() {
        Assertions.assertTrue(account.isChecking(), "The account is not a checking account");
    }
}
