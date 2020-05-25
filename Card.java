package sample;

public class Card {
    private String holdersFullName;
    private String cvv;
    private String cardNumber;
    private String paymentMethod;
    private double balance;
    private int monthOfExpiry;
    private int yearOfExpiry;

    public Card(String holdersFullName, String cvv, int monthOfExpiry, int yearOfExpiry, String cardNumber, String paymentMethod, double balance) {
        this.holdersFullName = holdersFullName;
        this.cvv = cvv;
        this.monthOfExpiry = monthOfExpiry;
        this.yearOfExpiry = yearOfExpiry;
        this.cardNumber = cardNumber;
        this.paymentMethod = paymentMethod;
        this.balance = balance;
    }

    public String getHoldersFullName() {
        return holdersFullName;
    }

    public void setHoldersFullName(String holdersFullName) {
        this.holdersFullName = holdersFullName;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public int getMonthOfExpiry() {
        return monthOfExpiry;
    }

    public void setMonthOfExpiry(int monthOfExpiry) {
        this.monthOfExpiry = monthOfExpiry;
    }

    public int getYearOfExpiry() {
        return yearOfExpiry;
    }

    public void setYearOfExpiry(int yearOfExpiry) {
        this.yearOfExpiry = yearOfExpiry;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
