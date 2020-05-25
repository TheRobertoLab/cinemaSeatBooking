package sample;

import java.text.NumberFormat;
import java.util.Locale;

public class MySeats extends MyThing{
    private String title;
    private String type;
    private String seatNumbers;
    private double amount;

    public MySeats(String type, String seatNumbers, double amount) {
        this.type = type;
        this.seatNumbers = seatNumbers;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(String seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        NumberFormat total = NumberFormat.getCurrencyInstance(Locale.UK);
        return "Type: " + type + ", Seat Numbers: " + seatNumbers + ", Amount " + total.format(amount);
    }
}
