package sample;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChoicesPageController implements Initializable {
    //JavaFX objects
    public TextField searchTF;
    public JFXButton searchButton;
    public JFXButton confirmButton;
    public ComboBox<String> chooseWeekCombo;
    public AnchorPane rootPane;
    public ListView <Film> filmListView;

    private Films films;

    //Declare and make default week number as 1
    private Integer extractedWeekNumber = 1;

    //Make this field static to pass to the next windows
    public static Film selectedFilm;
    private SearchEngine searchEngine;
    private Customer loggedCustomer;

    public ChoicesPageController(){
        films = MainPageController.films;
        loggedCustomer = LoginPageController.getLoggedCustomer();
        searchEngine = new SearchEngine();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Remove if theatre has no available seats
        films.removeUnavailableFilms();
        chooseWeekCombo.getItems().addAll("Week 1", "Week 2", "Week 3");

        showFilms();
        filmListView.setCellFactory(cell -> new CustomFilmListCell());
    }
    private void setSelectedFilm(Film film){
        selectedFilm = film;
    }

    public static Film getSelectedFilm(){
        return selectedFilm;
    }
    //When user selects week in the combobox
    public void purgeFilms(ActionEvent actionEvent) {
        //Extract just week number
        extractedWeekNumber = new Integer(chooseWeekCombo.getValue().substring(5, 6));
        filmListView.getItems().clear();
        makeFadeTransition(filmListView);
        showFilms();
    }
    private void showFilms(){
        for(Film fm : films){
            if(fm.getWeekNumber()==extractedWeekNumber)
                filmListView.getItems().addAll(fm);
        }
        //If list is empty, disable buttons and TF, so player cannot click
        if(filmListView.getItems().isEmpty()){
            searchTF.setDisable(true);
            confirmButton.setDisable(true);
            searchButton.setDisable(true);
        }else{
            searchTF.setDisable(false);
            confirmButton.setDisable(false);
            searchButton.setDisable(false);
        }
    }
    private void makeFadeTransition(Node node){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1500));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(0.1);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public void confirmButtonClicked(ActionEvent actionEvent){
        int selectedIndex = filmListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex == -1) {
            AlertMaker.showErrorAlert(rootPane, "Select a Film");

        } else {
            setSelectedFilm(filmListView.getSelectionModel().getSelectedItem());

            if(!checkCustomersAge(loggedCustomer, getSelectedFilm())){
                AlertMaker.showErrorAlertWithHeader(rootPane, "Your age: " + loggedCustomer.getAge()
                                + "\nFilm age restrictions: " + getSelectedFilm().getAgeRestrictions(),
                            "You Cannot Book Tickets for This Film");
            }
            else if(searchEngine.searchBasketItem(getSelectedFilm()) != null){
                AlertMaker.showErrorAlertWithHeader(rootPane, "Select another film or pay for items in a basket" +
                        "\nand then select this film again", "This film already is in the basket");
            }else{
                filmListView.getSelectionModel().clearSelection();
                displayChooseTicketsPage("ChooseTicketsPage.fxml");
            }
        }
    }
    public void displayChooseTicketsPage(String path) {
        try {
            Stage stage = new Stage();
            Parent chooseTicketsPage = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(chooseTicketsPage, 1200, 722);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("sample/assets/quadLogo.png"));
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void searchButtonPressed(ActionEvent actionEvent) {
        Film searchedFor;
        String findTitle = searchTF.getText();

        if(findTitle.trim().isEmpty()){
            AlertMaker.showErrorAlert(rootPane, "Enter a Title");
        }else{
            searchedFor = searchEngine.findFilmByTitle(findTitle);
            if(searchedFor == null) {
                AlertMaker.showWarning(rootPane, "Film Not Found");
            }else if(extractedWeekNumber != searchedFor.getWeekNumber()){
                AlertMaker.showWarning(rootPane, "The Film is Provided for Week " + searchedFor.getWeekNumber());
            }else {

                AlertMaker.showNotification("Film has been selected");
                filmListView.getSelectionModel().select(searchedFor);
                filmListView.scrollTo(searchedFor);
            }
        }
    }
    private boolean checkCustomersAge(Customer customer, Film film){
        int customersAge = customer.getAge();
        return customersAge >= film.getAgeRestrictions();
    }
}
