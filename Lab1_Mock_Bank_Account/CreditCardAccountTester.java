public class CreditCardAccountTester {

	
    public static void main(String[] args) {
        CreditCardAccount account1 = new CreditCardAccount(199.0, 0.055, 500.0, "9999999999999999", 2024, 11);
        
        System.out.println("\nStarting Values: balance = 199, interest rate = 0.055, credit limit = 500\n");

        
        double chargeAmount1 = 200.0;
        if (account1.addCharge(chargeAmount1)) {
            System.out.println("add charge of " + chargeAmount1 + "\tcharge successful");
        } else {
            System.out.println("add charge of " + chargeAmount1 + "\tcharge failed");
        }
        System.out.println("\t\t\tbalance = " + account1.getBalance());
        System.out.println("\t\t\tcredit remaining = " + account1.creditRemaining());
        System.out.println("");
        
        
        account1.makePayment();
        System.out.println("minimum payment made\tbalance = " + account1.getBalance());
        System.out.println("\t\t\tcredit remaining = " + account1.creditRemaining());
        System.out.println("");

        account1.addInterest();
        System.out.printf("add interest\t\tbalance = " + "%.2f", account1.getBalance());
        System.out.printf("\n\t\t\tcredit remaining = " + "%.2f", account1.creditRemaining());
        System.out.println("\n");

        double chargeAmount2 = 300.0;
        if (account1.addCharge(chargeAmount2)) {
            System.out.println("add charge of " + chargeAmount2 + "\tcharge successful");
        } else {
            System.out.println("add charge of " + chargeAmount2 + "\tcharge failed");
            System.out.println("");
        }

        double chargeAmount3 = 52.24;
        if (account1.addCharge(chargeAmount3)) {
            System.out.println("add charge of " + chargeAmount3 + "\tcharge successful");
            System.out.printf("\t\t\tbalance = " + "%.2f\n", account1.getBalance());
            System.out.printf("\t\t\tcredit remaining = " + "%.2f\n", account1.creditRemaining());

        } else {
            System.out.println("add charge of " + chargeAmount3 + "\tcharge failed");
            System.out.println("");
        }

        account1.makePayment(450.0);
        System.out.println("\nmake payment of 450\tbalance = " + account1.getBalance());
        System.out.println("\t\t\tcredit remaining = " + account1.creditRemaining());
        System.out.println("");

        account1.addInterest();
        System.out.println("add interest\t\tbalance = " + account1.getBalance());
        System.out.println("\t\t\tcredit remaining = " + account1.creditRemaining());
        System.out.println("\n------------------------------");
        
        
        System.out.println("\nHow Long To Pay Off\n");
        testPayOff(5985.0, 0.015);
        testPayOff(3000.0, 0.085);
        testPayOff(3000.0, 0.15);
        testPayOff(5000.0, 0.19);
        
        System.out.println("\n------------------------------");
        System.out.println("\nExtra Credit test");
        System.out.println("\nCurrent Account: balance = 5000, interest rate = 0.16, credit limit = 10000");
        System.out.println("New Account: balance = 1000, interest rate = 0.03, credit limit = 15000");
        System.out.println("Expected Results: Transfer Succeeded, Original Balance: 0, New Account Balance: 6050\n");
        CreditCardAccount currentAccount1 = new CreditCardAccount(5000.0, 0.16, 10000.0, "9999999999999999", 2025, 12);
        CreditCardAccount newAccount1 = new CreditCardAccount(1000.0, 0.03, 15000.0, "9876543210987654", 2026, 6);
        
        
        boolean transferResult1 = currentAccount1.transferToNewAccount(newAccount1);

        if (transferResult1) {
            System.out.println("Transfer succeeded");
            System.out.println("Original balance = 0");
            System.out.println("New account balance = " + newAccount1.getBalance());
        } else {
            System.out.println("Transfer failed because there is not enough available credit in the new account");
            System.out.println("No changes to either account");
        }
    }
        
    public static void testPayOff(double balance, double interestRate) {
        CreditCardAccount account = new CreditCardAccount(balance, interestRate, 0, "", 0, 0);
        int months = account.howLongToPayOff();
        System.out.println("balance = " + balance);
        System.out.println("interest rate = " + interestRate);
        System.out.println("How Many Months to Pay Off: " + months);
        System.out.println();
    }
}
