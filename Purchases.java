package sample;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class Purchases extends ObservableListWrapper<Purchase>{
    public Purchases(){
        super(FXCollections.observableArrayList());
    }

    public void addPurchasesPaidByCard(Basket basket){
        for(BasketItem bi : basket){
            super.add(new Purchase(bi.getFilmTitle(), bi.getTheatreTitle(), bi.getStartTime(), bi.getWeekNumber(),
                    bi.getTickets(),bi.getVictuals(), bi.getMySeats(), bi.getMySeatsAmount(), bi.getMyVictualsAmount(),
                    bi.getTicketsAmount(), bi.getAmount(), getCurrentDate(), generateUniqueID(10), true));
        }
    }
    public void addPurchasesPaidByCash(Basket basket){
        for(BasketItem bi : basket){
            super.add(new Purchase(bi.getFilmTitle(), bi.getTheatreTitle(), bi.getStartTime(), bi.getWeekNumber(),
                    bi.getTickets(),bi.getVictuals(), bi.getMySeats(), bi.getMySeatsAmount(), bi.getMyVictualsAmount(),
                    bi.getTicketsAmount(), bi.getAmount(), getCurrentDate(), generateUniqueID(10), false));
        }
    }
    public String generateUniqueID(Integer length){
        String data = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < length; i++){
            stringBuilder.append(data.charAt(random.nextInt(data.length())));
        }
        return stringBuilder.toString();
    }
    public String getCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale.UK);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
