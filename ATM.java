import java.util.Scanner;
class BankAccount {
    private double balance;
    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            this.balance = 0; 
            System.out.println("Initial balance cannot be negative. Setting to 0.");
        } else {
            this.balance = initialBalance;
        }
    }
    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited $%.2f. New balance: $%.2f%n", amount, balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (amount > balance) {
            System.out.printf("Insufficient balance. Current balance: $%.2f%n", balance);
            return false;
        }
        balance -= amount;
        System.out.printf("Successfully withdrew $%.2f. New balance: $%.2f%n", amount, balance);
        return true;
    }
}
public class ATM {
    private BankAccount account;
    private Scanner scanner;
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }
    public void displayMenu() {
        System.out.println("\n--- Welcome to the ATM ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
    public void start() {
        boolean running = true;
        while (running) {
            displayMenu();
            String choiceStr = scanner.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1-4).");
                continue; 
            }

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositFunds();
                    break;
                case 3:
                    withdrawFunds();
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 4.");
            }
        }
        scanner.close(); 
    }
    private void checkBalance() {
        System.out.printf("Your current balance is: $%.2f%n", account.getBalance());
    }
    private void depositFunds() {
        System.out.print("Enter amount to deposit: $");
        String amountStr = scanner.nextLine();
        double amount;

        try {
            amount = Double.parseDouble(amountStr);
            account.deposit(amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a numeric value.");
        }
    }
    private void withdrawFunds() {
        System.out.print("Enter amount to withdraw: $");
        String amountStr = scanner.nextLine();
        double amount;

        try {
            amount = Double.parseDouble(amountStr);
            account.withdraw(amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a numeric value.");
        }
    }
      public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.00); 
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
