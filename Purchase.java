package sample;

import java.io.Serializable;

public class Purchase extends BasketItem implements Serializable {
    private String whenBought;
    private String orderUniqueID;
    private boolean paidByCard;

    public Purchase(String filmTitle, String theatreTitle, String startTime, int weekNumber, String tickets,
                    String victuals, String mySeats, double mySeatsAmount, double myVictualsAmount, double ticketsAmount,
                    double amount, String whenBought, String orderUniqueID, boolean paidByCard){

        super(filmTitle, theatreTitle, startTime, weekNumber, tickets, victuals, mySeats, mySeatsAmount, myVictualsAmount, ticketsAmount, amount);
        this.whenBought = whenBought;
        this.orderUniqueID = orderUniqueID;
        this.paidByCard = paidByCard;
    }

    public String getWhenBought() {
        return whenBought;
    }

    public String getOrderUniqueID() {
        return orderUniqueID;
    }

    public boolean isPaidByCard() {
        return paidByCard;
    }

}
