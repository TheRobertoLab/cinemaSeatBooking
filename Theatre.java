package sample;

import java.util.ArrayList;

public class Theatre{
    private int weekNumber;
    private String theatreTitle;
    private int numOfLargeSeats;
    private int numOfStandardSeats;
    private int numberOfVipSeats;
    private double standardSeatPrice;
    private double vipSeatPrice;
    private double largeSeatPrice;
    private int availableSeats;
    private int totalNumOfSeats;
    private int saveIndex = 0;
    private ArrayList<Seat> theatreSeats;


    public Theatre(String theatreTitle, int weekNumber, int numOfLargeSeats, int numOfStandardSeats,
                   int numberOfVipSeats, double standardSeatPrice, double vipSeatPrice, double largeSeatPrice, int availableSeats, int totalNumOfSeats) {
        this.weekNumber = weekNumber;
        this.theatreTitle = theatreTitle;
        this.numOfLargeSeats = numOfLargeSeats;
        this.numOfStandardSeats = numOfStandardSeats;
        this.numberOfVipSeats = numberOfVipSeats;
        this.standardSeatPrice = standardSeatPrice;
        this.vipSeatPrice = vipSeatPrice;
        this.largeSeatPrice = largeSeatPrice;
        this.availableSeats = availableSeats;
        this.totalNumOfSeats = totalNumOfSeats;
        theatreSeats = new ArrayList<>();
        createSeats(numOfStandardSeats,"Standard", standardSeatPrice);
        createSeats(numberOfVipSeats, "Vip", vipSeatPrice);
        createSeats(numOfLargeSeats,"Large", largeSeatPrice);
    }


    public int getWeekNumber() {
        return weekNumber;
    }

    public String getTheatreTitle() {
        return theatreTitle;
    }

    public double getVipSeatPrice() {
        return vipSeatPrice;
    }

    public double getLargeSeatPrice() {
        return largeSeatPrice;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getStandardSeatPrice() {
        return standardSeatPrice;
    }

    public void reduceAvailableSeats(int num){
        this.availableSeats=this.availableSeats-num;
    }

    public void createSeats(int numOfSeats, String type, double price) {
        //I use saveIndex to continue seat id each time
        int size = saveIndex+1;
        for (int i = size; i < size+numOfSeats; i++) {
            Seat s = new Seat( type, price, ""+i,false,false);
            theatreSeats.add(s);
            saveIndex = i;
        }
    }

    public ArrayList<Seat> getTheatreSeats() {
        return theatreSeats;
    }
    public void makeSelectedSeatsUnavailable(){
        for(Seat st : theatreSeats){
            if(st.isSelected()){
                st.setTaken(true);
            }
        }
    }
    public Seat findSeatByID(String id){
        Seat temp = null;
        for(Seat s : theatreSeats){
           if(s.getId().equals(id)){
               temp = s;
           }
        }
        return temp;
    }
    @Override
    public String toString() {
        return "Theatre{" +
                "weekNumber=" + weekNumber +
                ", theatreTitle='" + theatreTitle + '\'' +
                ", numOfLargeSeats=" + numOfLargeSeats +
                ", numOfStandardSeats=" + numOfStandardSeats +
                ", numberOfVipSeats=" + numberOfVipSeats +
                ", standardSeatPrice=" + standardSeatPrice +
                ", vipSeatPrice=" + vipSeatPrice +
                ", largeSeatPrice=" + largeSeatPrice +
                ", availableSeats=" + availableSeats +
                ", totalNumOfSeats=" + totalNumOfSeats +
                '}';
    }
}
