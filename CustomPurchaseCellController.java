package sample;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.*;



public class CustomPurchaseCellController extends ListCell<Purchase> {
    public FXMLLoader cell;
    public HBox rootHBox;
    public Label lblFilmTitle;
    public Label lblPlaceAndWeek;
    public Label lblStartTime;
    public JFXButton printButton;
    public Label lblDate;
    public TextArea textArea;

    @Override
    public void updateItem(Purchase purchase, boolean empty) {
        super.updateItem(purchase, empty);
        if (empty || purchase == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (cell == null) {
                cell = new FXMLLoader(getClass().getResource("CustomPurchaseCell.fxml"));
                cell.setController(this);

                try {
                    cell.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            setText(null);
            lblFilmTitle.setText(purchase.getFilmTitle());
            lblPlaceAndWeek.setText(purchase.getTheatreTitle() + ", Week " + purchase.getWeekNumber());
            lblStartTime.setText(purchase.getStartTime());
            lblDate.setText("Date: " + purchase.getWhenBought());

            textArea.setText("Tickets: " + purchase.getTickets()
                            + "\nSeats: " + purchase.getMySeats()
                            + "\nVictuals: " + purchase.getVictuals());

            printButton.setOnAction(event -> {
                Stage stage = (Stage) printButton.getScene().getWindow();
                if(purchase.isPaidByCard()){
                    PDFGenerator.saveAndGeneratePDF(stage, purchase, PaymentWindowController.searchedCard);
                }else{
                    PDFGenerator.saveAndGeneratePDFPaidCash(stage, purchase);
                }
            });
            setGraphic(rootHBox);
        }
    }
}