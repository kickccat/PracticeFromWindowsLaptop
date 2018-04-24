package sample;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Challenge7 {
    
    public static void main(String[] args) {
        NewBankAccount account1 = new NewBankAccount(500.00, "12345-678");
        NewBankAccount account2 = new NewBankAccount(1000.00, "456789-123");
        
        new Thread(new Transfer(account1, account2, 10.00), "Transfer1").start();
        new Thread(new Transfer(account2, account1, 55.88), "Transfer2").start();
    }
}

class NewBankAccount {
    
    private double balance;
    private String accountNumber;
    private Lock lock = new ReentrantLock();
    
    NewBankAccount(double initialBalance, String accountNumber) {
        this.balance = initialBalance;
        this.accountNumber = accountNumber;
    }
    
    private boolean deposit(double amount) {
        if (lock.tryLock()) {
            try {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance += amount;
                System.out.printf("%s: Deposited %f\n", Thread.currentThread().getName(), amount);
                return true;
            } finally {
                lock.unlock();
            }
        }
        return false;
    }
    
    private boolean withdraw(double amount) {
        if (lock.tryLock()) {
            try {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance -= amount;
                System.out.printf("%s: Withdraw %f\n", Thread.currentThread().getName(), amount);
                return true;
            } finally {
                lock.unlock();
            }
        }
        return false;
    }
    
    boolean transfer(NewBankAccount destinationAccount, double amount) {
        if (withdraw(amount)) {
            if (destinationAccount.deposit(amount)) {
                return true;
            } else {
                System.out.printf("%s: Destination account busy. Refunding money\n", Thread.currentThread().getName());
                deposit(amount);
            }
        }
        return false;
    }
    
    private String getAccountNumber() {
        return accountNumber;
    }
    
    public void printAccountNumber() {
        System.out.println("Account number: " + getAccountNumber());
    }
    
}

class Transfer implements Runnable {
    
    private NewBankAccount sourceAccount, destinationAccount;
    private double amount;
    
    Transfer(NewBankAccount sourceAccount, NewBankAccount destinationAccount, double amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }
    
    @Override
    public void run() {
        while (!sourceAccount.transfer(destinationAccount, amount)) {
            continue;
        }
        System.out.printf("%s completed\n", Thread.currentThread().getName());
    }
}
