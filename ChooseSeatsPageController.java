package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChooseSeatsPageController implements Initializable {
    public AnchorPane rootPane;
    public JFXButton confirmButton;
    public Label lblAvailableSeats;
    public Label lblShouldToChooseNumber;
    public JFXButton cancelButton;
    public FlowPane flowPane;
    public ToggleButton exampleButtonSeat;
    public Label lblFilmTitle;
    public Label lblTheatreTitle;
    public Label lblStandard;
    public Label lblVip;
    public Label lblLarge;
    private int numOfAddedTickets;
    private Film selectedFilm;
    private SearchEngine searchEngine;
    private MyThings myThings;
    private Theatre searchedTheatre;
    private ArrayList<ToggleButton> seatButtons;

    public ChooseSeatsPageController() {
        searchEngine = new SearchEngine();
        numOfAddedTickets = ChooseTicketsPageController.getNumOfTickets();
        selectedFilm = ChoicesPageController.getSelectedFilm();
        seatButtons = new ArrayList<>();
        myThings = ChooseTicketsPageController.myThings;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        makeFadeIn(rootPane);
        exampleButtonSeat.setSelected(true);
        searchedTheatre = searchEngine.searchTheatre(selectedFilm.getTheatreTitle(), selectedFilm.getWeekNumber());

        updateSeatsPrices();
        updateTexts(selectedFilm, numOfAddedTickets, searchedTheatre.getAvailableSeats(), searchedTheatre.getTheatreTitle());
        drawSeats(searchedTheatre, flowPane);
    }

    private void makeSeatsSelected(Theatre theatre){
        for(ToggleButton tg : seatButtons){

            if(tg.isSelected()) {
                theatre.findSeatByID(tg.getId()).setSelected(true);
            }
        }
    }

    private double calcAmountForSeats(Theatre theatre, String type){
        double amount=0;
        for(ToggleButton tg : seatButtons){
            if(tg.isSelected()){
                if(theatre.findSeatByID(tg.getId()).getType().equals(type)){
                    amount+=theatre.findSeatByID(tg.getId()).getPrice();
                }
            }
        }
        return amount;
    }

    private String getSelectedSeatNumbers(Theatre theatre, String type){
        StringBuilder sb = new StringBuilder();
        for(ToggleButton tg : seatButtons){
            if(tg.isSelected()){
                if(theatre.findSeatByID(tg.getId()).getType().equals(type)){
                    sb.append(tg.getText()).append(" ");
                }
            }
        }
        return sb.toString();
    }

    private int calcNumOfSelectedSeats(Theatre theatre, String type){
        int quantity = 0;
        for(ToggleButton tg : seatButtons){
            if(tg.isSelected()){
                if(theatre.findSeatByID(tg.getId()).getType().equals(type)) {
                    quantity++;
                }
            }
        }
        return quantity;
    }

    private void drawSeats(Theatre theatre, FlowPane flowPane) {
        //The main method to draw seats on the window
        //Setting id to the button, because we will find seat by id later
        for(Seat st :  theatre.getTheatreSeats()){
            ToggleButton seatButton = new ToggleButton(st.getId());
            seatButton.setId(st.getId());
            if (st.getType().equals("Vip")) {
                seatButton.setStyle("-fx-border-color: #e8bb4a; -fx-border-width: 2");
            }
            if (st.getType().equals("Large")) {
                seatButton.setStyle("-fx-border-color: #7ed13e; -fx-border-width: 2");
            }
            if (st.isTaken()) {
                seatButton.setStyle("-fx-background-color: RED; -fx-text-fill: WHITE");
                seatButton.setDisable(true);
            }
            seatButton.setPrefSize(49, 47);
            seatButtons.add(seatButton);
            flowPane.getChildren().add(seatButton);
        }
    }

    private void updateTexts(Film selectedFilm, int totalQuantity, int availableSeats, String theatreTitle){
        lblFilmTitle.setText(selectedFilm.getTitle());
        lblShouldToChooseNumber.setText("You should choose " + totalQuantity + " seat(-s)");
        lblAvailableSeats.setText("Available seats: " + availableSeats);
        lblTheatreTitle.setText(theatreTitle);
    }

    private void makeFadeIn(Node node){
        FadeTransitionOwn.makeFadeTransition(node);
    }

    public void cancelPressed(ActionEvent actionEvent) {
        AlertMaker.showConfirmationToClose("Your choice will not save",
                "Are your sure you want to close? " , (Stage) cancelButton.getScene().getWindow());
    }

    private void createMySeats(){
        //Need to create for each type of ticket one object, not for each ticket
        if(calcNumOfSelectedSeats(searchedTheatre, "Vip") > 0){
            myThings.addMySeats("Vip", getSelectedSeatNumbers(searchedTheatre, "Vip"), calcAmountForSeats(searchedTheatre, "Vip"));
        }
        if(calcNumOfSelectedSeats(searchedTheatre, "Standard") > 0){
            myThings.addMySeats("Standard", getSelectedSeatNumbers(searchedTheatre, "Standard"), calcAmountForSeats(searchedTheatre, "Standard"));
        }
        if(calcNumOfSelectedSeats(searchedTheatre, "Large") > 0){
            myThings.addMySeats("Large", getSelectedSeatNumbers(searchedTheatre, "Large"), calcAmountForSeats(searchedTheatre, "Large"));
        }
    }

    public void confirmButtonPressed(ActionEvent actionEvent) {
        int total = calcNumOfSelectedSeats(searchedTheatre, "Vip")+calcNumOfSelectedSeats(searchedTheatre, "Standard")+calcNumOfSelectedSeats(searchedTheatre, "Large");

        if(total > searchedTheatre.getAvailableSeats()) {
            AlertMaker.showErrorAlert(rootPane, "Please reduce selected seats");
        }else{
            if(total < numOfAddedTickets) {
                AlertMaker.showErrorAlert(rootPane, "You should select " + numOfAddedTickets + " seat(-s)");
            }else if(total > numOfAddedTickets){
                AlertMaker.showErrorAlert(rootPane, "You should select " + numOfAddedTickets + " seat(-s)");
            }else{
                makeSeatsSelected(searchedTheatre);
                createMySeats();
                displayVictualsPage("VictualsPage.fxml");
            }
        }
    }
    private void updateSeatsPrices(){
        NumberFormat gb = NumberFormat.getCurrencyInstance(Locale.UK);
        lblStandard.setText("- Standard (+ " + gb.format(searchedTheatre.getStandardSeatPrice()) + ")");
        lblVip.setText("- V.I.P seat (+ " + gb.format(searchedTheatre.getVipSeatPrice()) + ")");
        lblLarge.setText("- Large Seat (+ " + gb.format(searchedTheatre.getLargeSeatPrice()) + ")");

    }
    private void displayVictualsPage(String path){
        try{
            Stage mainStage = (Stage) confirmButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(root, 1200, 722);
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
