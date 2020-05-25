package sample;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;

public class Basket extends ObservableListWrapper<BasketItem> {
    private double totalAmount;
    public Basket(){
        super(FXCollections.observableArrayList());
    }
    public void addItem(String filmTitle, String theatreTitle, String startTime, int weekNumber, String tickets,  String victuals,
                        String mySeats, double mySeatsAmount, double myVictualsAmount, double ticketsAmount, double totalAmount){
        super.add(new BasketItem(filmTitle, theatreTitle, startTime, weekNumber, tickets, victuals, mySeats, mySeatsAmount, myVictualsAmount, ticketsAmount, totalAmount));
    }
    public void clear(){
        super.clear();
    }

    public BasketItem findByTitleAndWeek(String title, int week){

        BasketItem temp;
        int indexLocation = -1;
        for (int i = 0; i < super.size(); i++) {
            temp = super.get(i);

            if (temp.getFilmTitle().equals(title) && temp.getWeekNumber() == week){
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

    public double calcTotalAmount(){
        totalAmount = 0;
        for(int i = 0; i < super.size(); i++){
            totalAmount += super.get(i).getAmount();
        }
        return totalAmount;
    }

    public void reduceAmountByCode(DiscountCode code){
        setTotalAmount(totalAmount-totalAmount*code.getPercentage()/100);

    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public double getTotalAmount(){
        return this.totalAmount;
    }
}
