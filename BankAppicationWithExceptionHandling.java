package BankApplicationException;

import java.util.Scanner;

public class BankAppicationWithExceptionHandling {
    public static void main(String[] args) {
        int balance, option;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter balance to create account");
        balance = sc.nextInt();
        Account acc = new Account(balance);
        System.out.println("Enter\n1 to withdraw amount\n2 to deposit amount\n0 to exit");
        option = sc.nextInt();
        while (option == 1 || option == 2) {
            if (option == 1)
                acc.withdraw();
            else if (option == 2)
                acc.deposit();
            System.out.println("Enter\n1 to withdraw amount\n2 to deposit amount\n0 to exit");
            option = sc.nextInt();
        }
    }
}

class Account {
    int balance;
    Scanner sc = new Scanner(System.in);

    public Account(int balance) {
        this.balance = balance;
    }

    void withdraw() {
        int amount;
        System.out.println("Enter amount to be withdrawn");
        amount = sc.nextInt();
        try {
            if (amount < 1)
                throw new InvalidAmtExp();
            else if (amount > balance)
                throw new MinimumBalExp();
            else if (amount > 1000) // Added condition for a new exception
                throw new NegativeBalanceExp();
            else {
                balance -= amount;
                System.out.println("Amount withdrawn, Current Balance= " + balance);
            }
        } catch (InvalidAmtExp i) {
            System.out.println(i.getMessage());
        } catch (MinimumBalExp m) {
            System.out.println(m.getMessage());
        } catch (NegativeBalanceExp n) {
            System.out.println(n.getMessage());
        } catch (Exception e) {
            System.out.println("Exception occurred");
        }
    }

    void deposit() {
        int amount;
        System.out.println("Enter amount to be deposited");
        amount = sc.nextInt();
        try {
            if (amount < 1)
                throw new InvalidAmtExp();
            else {
                balance += amount;
                System.out.println("Amount deposited, Current Balance= " + balance);
            }
        } catch (InvalidAmtExp i) {
            System.out.println(i.getMessage());
        } catch (Exception e) {
            System.out.println("Exception occurred");
        }
    }
}

class InvalidAmtExp extends Exception {
    public String getMessage() {
        return "Entered amount is invalid";
    }
}

class MinimumBalExp extends Exception {
    public String getMessage() {
        return "Entered amount is more than current balance, amount withdraw failed";
    }
}

class NegativeBalanceExp extends Exception {
    public String getMessage() {
        return "Withdrawal amount exceeds a limit, amount withdraw failed";
    }
}
