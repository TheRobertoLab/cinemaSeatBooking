package sample;

import java.text.NumberFormat;
import java.util.Locale;

public class Ticket extends MyThing{
    private String type;
    private int quantity;
    private double amount;

    public Ticket(String type, int quantity, double amount) {
        this.type = type;
        this.quantity = quantity;
        this.amount = amount;
    }
    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.UK);
        return "Type: " + type + ", Quantity: " + quantity + " Amount: " + format.format(amount);
    }
}
