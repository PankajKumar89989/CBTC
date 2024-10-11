import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Invalid withdraw amount or insufficient balance.");
        }
    }

    public void transfer(Account targetAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            targetAccount.deposit(amount);
            System.out.println("Transferred " + amount + " to " + targetAccount.getAccountNumber());
        } else {
            System.out.println("Invalid transfer amount or insufficient balance.");
        }
    }
}

public class BankY {
    private static final HashMap<String, Account> accounts = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nWelcome to BankY!");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Account Details");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    viewAccountDetails();
                    break;
                case 6:
                    System.out.println("Thank you for using BankY. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter account holder name: ");
        String accountHolderName = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Account newAccount = new Account(accountNumber, accountHolderName, initialBalance);
        accounts.put(accountNumber, newAccount);
        System.out.println("Account created successfully!");
    }

    private static void deposit() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdraw() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.print("Enter withdraw amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void transfer() {
        System.out.print("Enter source account number: ");
        String sourceAccountNumber = scanner.nextLine();
        Account sourceAccount = accounts.get(sourceAccountNumber);
        if (sourceAccount != null) {
            System.out.print("Enter target account number: ");
            String targetAccountNumber = scanner.nextLine();
            Account targetAccount = accounts.get(targetAccountNumber);
            if (targetAccount != null) {
                System.out.print("Enter transfer amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                sourceAccount.transfer(targetAccount, amount);
            } else {
                System.out.println("Target account not found.");
            }
        } else {
            System.out.println("Source account not found.");
        }
    }

    private static void viewAccountDetails() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Holder: " + account.getAccountHolderName());
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
}

