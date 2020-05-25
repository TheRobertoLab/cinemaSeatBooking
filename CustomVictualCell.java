package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class CustomVictualCell extends ListCell<Victual> {
    public Label lblTitle;
    public Label lblCapacity;
    public Label lblPrice;
    public FXMLLoader cell;
    public AnchorPane anchorPane;
    public ImageView imageView;

    @Override
    public void updateItem(Victual victual, boolean empty){
        super.updateItem(victual, empty);
        if(empty || victual == null){
            setText(null);
            setGraphic(null);
        }else{
            if(cell == null){
                cell = new FXMLLoader(getClass().getResource("CustomVictualCell.fxml"));
                cell.setController(this);

                try {
                    cell.load();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            setText(null);
            lblTitle.setText(victual.getTitle());
            lblCapacity.setText("Capacity: " + victual.getCapacity());

            NumberFormat price = NumberFormat.getCurrencyInstance(Locale.UK);
            lblPrice.setText("Price "+ price.format(victual.getPrice()));

            imageView.setImage(victual.getImage());


            setGraphic(anchorPane);
        }
    }
}
