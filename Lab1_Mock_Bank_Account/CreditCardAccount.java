import java.text.NumberFormat;
import java.time.LocalDate;

public class CreditCardAccount {
    private double balance;
    private double interestRate;
    private double creditLimit;
    private String cardNumber;
    private int expirationMonth;
    private int expirationYear;
    
    private static final double MIN_PAYMENT_FULL = 25.0;
    private static final double MIN_PAYMENT_25 = 1000.0;
    private static final double MIN_PAYMENT_TWO_PERCENT = 25.0;
    
    public double getBalance() {
        return balance;
    }

    public CreditCardAccount(double balance, double interestRate, double creditLimit, String cardNumber, int expirationYear, int expirationMonth) {
        this.balance = balance;
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
        this.cardNumber = cardNumber;
        this.expirationYear = expirationYear;
        this.expirationMonth = expirationMonth;
    }

    public boolean isExpired() {
        LocalDate currentDate = LocalDate.now();
        return (currentDate.getYear() > expirationYear || 
                (currentDate.getYear() == expirationYear && currentDate.getMonthValue() > expirationMonth));
    }

    public double creditRemaining() {
        return creditLimit - balance;
    }

    public boolean addCharge(double amount) {
        if (balance + amount <= creditLimit) {
            balance += amount;
            return true; 
        }
        return false;
    }

    public double calculateMinimumPayment() {
        if (balance <= MIN_PAYMENT_FULL) {
            return balance;
        } else if (balance <= MIN_PAYMENT_25) {
            return MIN_PAYMENT_TWO_PERCENT;
        } else {
            return balance * 0.02; 
        }
    }

    public boolean makePayment(double paymentAmount) {
        if (balance - paymentAmount >= 0) {
            balance -= paymentAmount;
            return true;
        } else {
            balance = 0;
            return false;
        }
    }

    public double getInterestRate() {
        return interestRate;
    }

    public boolean makePayment() {
        double minimumPayment = calculateMinimumPayment();
        return makePayment(minimumPayment);
    }

    public void addInterest() {
        double monthlyInterest = balance * (interestRate / 12.0);
        balance += monthlyInterest;
    }

    public int howLongToPayOff() {
        CreditCardAccount tempAccount = new CreditCardAccount(balance, interestRate, creditLimit, cardNumber, expirationYear, expirationMonth);
        int months = 0;

        while (tempAccount.balance > 0) {
            double minimumPayment = tempAccount.calculateMinimumPayment();
            tempAccount.makePayment(minimumPayment);
            tempAccount.addInterest();
            months++;
        }

        return months;
    }

    @Override
    public String toString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String formattedBalance = formatter.format(balance);
        return "Card Number: " + cardNumber + "\n" +
               "Balance: " + formattedBalance + "\n" +
               "Credit Limit: " + creditLimit;
    }
    
    public boolean transferToNewAccount(CreditCardAccount newAccount) {
        if (newAccount.creditRemaining() >= balance &&
            newAccount.getInterestRate() < interestRate &&
            newAccount.howLongToPayOff() < howLongToPayOff() / 2) {

            double transferAmount = balance * 1.01;
            newAccount.balance += transferAmount;
            balance = 0;

            return true;
        }
        return false;
    }
}
