package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChooseTicketsPageController implements Initializable {
    //Buttons
    public JFXButton nextButton;

    //Labels holds chosen film prices text.
    public Label lblNormalTicketPrice;
    public Label lblStudentTicketPrice;
    public Label lblTicketForTwoPrice;
    public Label lblChildTicketPrice;

    //Combo Boxes to choose type of tickets.
    public ComboBox<Integer> childComboBox;
    public ComboBox<Integer> forTwoComboBox;
    public ComboBox<Integer> studentComboBox;
    public ComboBox<Integer> normalComboBox;

    //Labels which store any details about choice.
    public Label lblFilmTitle;
    public Label lblAvailableSeats;
    public Label lblStartTime;
    public Label lblTheatreTitle;
    public Label lblWeekNumber;
    public ImageView filmImage;
    public AnchorPane rootPane;
    public JFXButton cancelButton;

    //Relationship objects
    private SearchEngine searchEngine;
    private Film selectedFilm;
    public static MyThings myThings;

    //Selected number of tickets by customer
    private int numOfSelectedNormal = 0;
    private int numOfSelectedStudent = 0;
    private int numOfSelectedForTwo = 0;
    private int numOfSelectedChild = 0;
    public static int numOfSelectedTicketsTotal = 0;
    public ChooseTicketsPageController() {
        searchEngine = new SearchEngine();
        selectedFilm = ChoicesPageController.getSelectedFilm();
        myThings = new MyThings();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        normalComboBox.getItems().setAll(0, 1, 2, 3);
        forTwoComboBox.getItems().setAll(0, 1, 2, 3);
        studentComboBox.getItems().setAll(0, 1, 2, 3);
        childComboBox.getItems().setAll(0, 1, 2, 3);

        //Block child tickets if age restrictions greater than 10
        if (selectedFilm.getAgeRestrictions() > 10) {
            childComboBox.setDisable(true);
        }
        //Get selected film and update details
        updateTexts(selectedFilm);

        //Found theatre according to providing film and week and update details.
        Theatre searchedTheatre = searchEngine.searchTheatre(selectedFilm.getTheatreTitle(), selectedFilm.getWeekNumber());
        if (searchedTheatre != null) {
            updateTheatresDetails(searchedTheatre);
        }
    }

    public void updateTheatresDetails(Theatre theatre) {
        lblTheatreTitle.setText("Place: " + theatre.getTheatreTitle());
        lblAvailableSeats.setText("Seats Available: " + theatre.getAvailableSeats());
    }

    public void updateTexts(Film film) {
        //Film
        filmImage.setImage(film.getImage());

        //Update Film Details
        lblFilmTitle.setText(film.getTitle());
        lblStartTime.setText(film.getStartTime());
        lblWeekNumber.setText("Week " + film.getWeekNumber() + ", Saturday");

        //Update Price Texts
        NumberFormat price = NumberFormat.getCurrencyInstance(Locale.UK);
        lblNormalTicketPrice.setText("" + price.format(film.getNormalTicketPrice()));
        lblStudentTicketPrice.setText("" + price.format(film.getStudentTicketPrice()));
        lblTicketForTwoPrice.setText("" + price.format(film.getTicketForTwoPrice()));
        lblChildTicketPrice.setText("" + price.format(film.getChildTicketPrice()));
    }

    public void pressNextButton(ActionEvent actionEvent) {
        Theatre searchedTheatre = searchEngine.searchTheatre(selectedFilm.getTheatreTitle(), selectedFilm.getWeekNumber());
        numOfSelectedTicketsTotal = calculateSelectedTicketsQuantity();

        if(numOfSelectedTicketsTotal <= searchedTheatre.getAvailableSeats()) {
            if (numOfSelectedTicketsTotal == 0) {
                AlertMaker.showErrorAlert(rootPane, "Please select any type and number of tickets");
            } else {
                createTickets();
                displaySeatsPage("ChooseSeatsPage.fxml");
                AlertMaker.showNotification(numOfSelectedTicketsTotal + " ticket(-s) has been added to your basket");
            }
        }else{
            AlertMaker.showErrorAlertWithHeader(rootPane, "Please, reduce selected seats or choose another film", "There is no enough available seats in theatre");
        }
    }
    public void displaySeatsPage(String path) {
        try {
            Stage mainStage = (Stage) nextButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(root, 1200, 722);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int getNumOfTickets(){
        return numOfSelectedTicketsTotal;
    }
    public void doSelectionNormal(ActionEvent actionEvent) {
        numOfSelectedNormal = normalComboBox.getSelectionModel().getSelectedItem();
    }

    public void doSelectionStudent(ActionEvent actionEvent) {
        numOfSelectedStudent = studentComboBox.getSelectionModel().getSelectedItem();
    }


    public void doSelectionChild(ActionEvent mouseEvent) {
        numOfSelectedChild = childComboBox.getSelectionModel().getSelectedItem();
    }

    public void doSelectionTicketForTwo(ActionEvent mouseEvent) {
        //We need to multiple by 2, because is ticket for two persons.
        numOfSelectedForTwo = 2*forTwoComboBox.getSelectionModel().getSelectedItem();
    }

    public int calculateSelectedTicketsQuantity() {
        return numOfSelectedNormal + numOfSelectedChild + numOfSelectedStudent + numOfSelectedForTwo;
    }
    //IMPORTANT
    //This method is here, because we need calculate amount before adding ticket to MyThings list
    private double calculateAmount(int numOfTickets, double ticketPrice) {
        return numOfTickets*ticketPrice;
    }

    public void closeButtonPressed(ActionEvent actionEvent) {
        AlertMaker.showConfirmationToClose("Your choice will not save",
                "Are your sure want to cancel? " , (Stage) cancelButton.getScene().getWindow());
    }
    public void createTickets(){
        //Creating tickets and adding to the list.
        if (numOfSelectedNormal > 0) {
            myThings.addTicket("Normal Ticket", numOfSelectedNormal, calculateAmount(numOfSelectedNormal, selectedFilm.getNormalTicketPrice()));
        }
        if (numOfSelectedStudent > 0) {

            myThings.addTicket("Student Ticket", numOfSelectedStudent, calculateAmount(numOfSelectedStudent, selectedFilm.getStudentTicketPrice()));
        }
        if (numOfSelectedForTwo > 0) {
            //Divide by two, because further we'll need to select 6 seats, but pay for 3
            myThings.addTicket("Ticket For Two", numOfSelectedForTwo/2, calculateAmount(numOfSelectedForTwo, selectedFilm.getTicketForTwoPrice())/2);
        }
        if (numOfSelectedChild > 0) {
            myThings.addTicket("Child Ticket", numOfSelectedChild, calculateAmount(numOfSelectedChild, selectedFilm.getChildTicketPrice()));
        }
        for(MyThing mt : myThings){
            System.out.println(mt);
        }
    }
}
