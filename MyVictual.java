package sample;

import java.text.NumberFormat;
import java.util.Locale;

public class MyVictual extends Victual{

   public MyVictual(String title, String type, double price, String capacity){
       super(title, type, price, capacity);
   }
    @Override
    public String toString() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.UK);
        return "Title: " + super.getTitle() + ", Type: " + super.getType() + ", Price: " + format.format(super.getPrice()) +
                ", Capacity: " + super.getCapacity();
    }
}
