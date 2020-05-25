package sample;

import java.io.Serializable;

public class BasketItem implements Serializable {
   private String filmTitle;
   private String startTime;
   private String theatreTitle;
   private String tickets;
   private String victuals;
   private String mySeats;
    private int weekNumber;
   private double mySeatsAmount;
   private double myVictualsAmount;
   private double ticketsAmount;
   private double totalAmount;

    public BasketItem(String filmTitle, String theatreTitle, String startTime, int weekNumber,
                      String tickets,  String victuals, String mySeats, double mySeatsAmount, double myVictualsAmount, double ticketsAmount, double amount) {
        this.filmTitle = filmTitle;
        this.theatreTitle = theatreTitle;
        this.startTime = startTime;
        this.weekNumber = weekNumber;
        this.tickets = tickets;
        this.victuals = victuals;
        this.mySeats = mySeats;
        this.totalAmount = amount;

        this.mySeatsAmount = mySeatsAmount;
        this.myVictualsAmount = myVictualsAmount;
        this.ticketsAmount = ticketsAmount;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public String getTheatreTitle() {
        return theatreTitle;
    }

    public void setTheatreTitle(String theatreTitle) {
        this.theatreTitle = theatreTitle;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public String getVictuals() {
        return victuals;
    }

    public void setVictuals(String victuals) {
        this.victuals = victuals;
    }

    public String getMySeats() {
        return mySeats;
    }

    public void setMySeats(String mySeats) {
        this.mySeats = mySeats;
    }

    public double getAmount() {
        return totalAmount;
    }

    public void setAmount(double amount) {
        this.totalAmount = amount;
    }

    public double getMySeatsAmount() {
        return mySeatsAmount;
    }

    public void setMySeatsAmount(double mySeatsAmount) {
        this.mySeatsAmount = mySeatsAmount;
    }

    public double getMyVictualsAmount() {
        return myVictualsAmount;
    }

    public void setMyVictualsAmount(double myVictualsAmount) {
        this.myVictualsAmount = myVictualsAmount;
    }

    public double getTicketsAmount() {
        return ticketsAmount;
    }

    public void setTicketsAmount(double ticketsAmount) {
        this.ticketsAmount = ticketsAmount;
    }
}
