package sample;

import java.util.ArrayList;

public class Cards extends ArrayList<Card> {
    public Cards(){
        super(new ArrayList<>());
        //I need to declare new cards here or in the controller?
        addCard("Robert Gilevic", "134", 12, 2021, "1111111111111111", "Visa", 200.0);
    }
    public void addCard(String holdersFullName, String cvv, int monthOfExpiry, int yearOfExpiry, String cardNumber, String paymentMethod, double balance){
        super.add(new Card(holdersFullName, cvv, monthOfExpiry, yearOfExpiry, cardNumber, paymentMethod, balance));
    }

    public Card findByNumber(String cardNumber){
        Card temp;
        int indexLocation = -1;
        for (int i = 0; i < super.size(); i++) {
            temp = super.get(i);

            if (temp.getCardNumber().equals(cardNumber)){
                indexLocation = i;
                break;
            }
        }
        if (indexLocation == -1) {
            return null;
        } else {
            return super.get(indexLocation);
        }
    }
}
