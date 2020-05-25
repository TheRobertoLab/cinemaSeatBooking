package sample;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;

public class Victuals extends ObservableListWrapper<Victual> {
    public Victuals() {
        super(FXCollections.observableArrayList());

        addVictual("Walkers", "Snack", 1.00, "50g", "sample/Assets/Walkers.jpg");
        addVictual("Coca-Cola", "Drink", 1.25, "0.5l", "sample/Assets/coke.jpg");
        addVictual("Sprite", "Drink", 1.50, "0.5l", "sample/Assets/Sprite.jpg");
        addVictual("Fanta", "Drink", 1.50, "0.5l", "sample/Assets/Fanta.jpg");
        addVictual("Ringos", "Snack", 1.20, "100 g.", "sample/Assets/Ringos.png");
        addVictual("Cheetos Cheese", "Snack", 1.19, "70g", "sample/Assets/Cheetos.jpg");
        addVictual("Oasis", "Drink", 1.29, "0.5l", "sample/Assets/Oasis.jpg");
        addVictual("Popcorn", "Snack", 0.99, "200g", "sample/Assets/Popcorn.jpg");
        addVictual("Evian", "Drink", 0.50, "0.5l", "sample/Assets/Evian.jpg");
        addVictual("M&M's", "Snack", 1.19, "45g", "sample/Assets/MM.jpg");
        addVictual("Digestives", "Snack", 1.55, "55g", "sample/Assets/Digestives.jpg");
    }
    public void addVictual(String title, String type, double price, String capacity, String path){
        super.add(new Victual(title, type, price, capacity, path));
    }
}
