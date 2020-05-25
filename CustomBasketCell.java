package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class CustomBasketCell extends ListCell<BasketItem> {
    public ImageView imageView;
    public FXMLLoader cell;
    public AnchorPane anchorPane;
    public Label lblFilmTitle;
    public Label lblPlaceAndWeek;
    public Label lblStartTime;
    public Label lblAmount;
    public TextArea textArea;

    private Image choicesImage = new Image("sample/Assets/choices.png");

    @Override
    public void updateItem(BasketItem basketItem, boolean empty){
        super.updateItem(basketItem, empty);
        if(empty || basketItem == null){
            setText(null);
            setGraphic(null);
        }else{
            if(cell == null){
                cell = new FXMLLoader(getClass().getResource("CustomBasketCell.fxml"));
                cell.setController(this);

                try {
                    cell.load();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            imageView.setImage(choicesImage);
            lblFilmTitle.setText(basketItem.getFilmTitle());
            lblPlaceAndWeek.setText(basketItem.getTheatreTitle() + ", Week " + basketItem.getWeekNumber());
            lblStartTime.setText(basketItem.getStartTime());
            textArea.setText("Tickets: "
                    + basketItem.getTickets()
                    + "\nSeats: " + basketItem.getMySeats()
                    + "\nVictuals: " + basketItem.getVictuals());

            NumberFormat amount = NumberFormat.getCurrencyInstance(Locale.UK);
            lblAmount.setText("" + amount.format(basketItem.getAmount()));

            setText(null);
            setGraphic(anchorPane);
        }
    }
}
