package sample;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;

public class MyThings extends ObservableListWrapper<MyThing> {
    public MyThings() {
        super(FXCollections.observableArrayList());
    }
    public void addTicket(String type, int quantity, double amount){

        super.add(new Ticket(type, quantity, amount));
    }
    public void addMySeats(String type, String seatNumbers, double amount){

        super.add(new MySeats(type,seatNumbers,amount));
    }
    public void addMyVictual(String title, String type, double price, String capacity) {
        super.add(new MyVictual(title, type, price, capacity));
    }
    public double calculateTotalAmount(){
        double amount = 0.0;
        for(int i = 0; i < super.size(); i++){
            if(super.get(i) instanceof MyVictual){
                amount+=((MyVictual) super.get(i)).getPrice();
            }else if(super.get(i) instanceof MySeats){
                amount+=((MySeats) super.get(i)).getAmount();
            }else if(super.get(i) instanceof Ticket){
                amount+=((Ticket) super.get(i)).getAmount();
            }
        }
        return amount;
    }
    public double calculateVictualAmount(){
        double amount = 0.0;
        for(int i = 0; i < super.size(); i++){
            if(super.get(i) instanceof MyVictual){
                amount+=((MyVictual) super.get(i)).getPrice();
            }
        }
        return amount;
    }
    public double calculateSeatsAmount(){
        double amount = 0.0;
        for(int i = 0; i < super.size(); i++){
            if(super.get(i) instanceof MySeats){
                amount+=((MySeats) super.get(i)).getAmount();
            }
        }
        return amount;
    }
    public double calculateTicketsAmount(){
        double amount = 0.0;
        for(int i = 0; i < super.size(); i++){

            if(super.get(i) instanceof Ticket){
                amount+=((Ticket) super.get(i)).getAmount();
            }
        }
        return amount;
    }
    public int countNumOfAddedVictuals(){
        int numOfAdded = 0;
        for(int i = 0; i < super.size(); i++){
            if(super.get(i) instanceof MyVictual){
                numOfAdded++;
            }
        }
        return numOfAdded;
    }

    public double calculateTicketsAmountByType(String type){
        double amount = 0.0;
        for(int i = 0; i < super.size(); i++){
            if(super.get(i) instanceof Ticket){
                if(((Ticket) super.get(i)).getType().equals(type)){
                    amount+=((Ticket) super.get(i)).getAmount();
                }
            }
        }
        return amount;
    }
}
