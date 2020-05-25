package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ChoiceSummaryPageController implements Initializable {
    public AnchorPane rootPane;
    public JFXButton cancelButton;
    public Label lblFilmTitle;
    public Label lblTime;
    public Label lblPlaceAndWeek;
    public JFXButton addButton;
    private Film selectedFilm;
    //Lists
    public ListView <MyThing> myChoicesListView;
    public ImageView filmImage;

    //Declare Basket Here
    public Basket basket;

    private Image ticketImage = new Image("sample/Assets/TicketsCol.png");
    private Image seatImage = new Image("sample/Assets/ChairBigger.png");
    private Image victualImage = new Image("sample/Assets/ColorSnackAndDrink.png");

    public ChoiceSummaryPageController(){
        selectedFilm = ChoicesPageController.getSelectedFilm();
        basket = MainPageController.basket;
    }

    public String makeTicketString(){
        StringBuilder sb = new StringBuilder();
        for(MyThing mt : ChooseTicketsPageController.myThings){
            if(mt instanceof Ticket){
                sb.append(((Ticket) mt).getQuantity()).append("x ").append(((Ticket) mt).getType()).append(" ");
            }
        }
        return sb.toString();
    }
    public String makeVictualString(){
        StringBuilder sb = new StringBuilder();
        for(MyThing mt : ChooseTicketsPageController.myThings){
            if(mt instanceof Victual){
                sb.append(((Victual) mt).getTitle()).append(" ").append(((Victual) mt).getCapacity()).append(" ");
            }
        }
        return sb.toString();
    }
    public String makeSeatsString(){
        StringBuilder sb = new StringBuilder();
        for(MyThing mt : ChooseTicketsPageController.myThings){
            if(mt instanceof MySeats){
               sb.append(((MySeats) mt).getType()).append(": ").append(((MySeats) mt).getSeatNumbers()).append(" ");
            }
        }
        return sb.toString();
    }
    public void pressAddToBasket(ActionEvent actionEvent) {
        MyThings mt = ChooseTicketsPageController.myThings;

        //Make basket item and add it to the basket
        basket.addItem(selectedFilm.getTitle(), selectedFilm.getTheatreTitle(), selectedFilm.getStartTime(),
                selectedFilm.getWeekNumber(), makeTicketString(),
                makeVictualString(), makeSeatsString(),  mt.calculateSeatsAmount(), mt.calculateVictualAmount(), mt.calculateTicketsAmount(), mt.calculateTotalAmount());


        AlertMaker.showInformationClose(rootPane, " To see your basket click on the Basket on the left side",
                "Choice Added Successfully", (Stage) addButton.getScene().getWindow());
    }

    public void pressCancel(ActionEvent actionEvent) {
        AlertMaker.showConfirmationToClose("Are you sure want to close?",
                "Your Choice Will Not Save", (Stage) cancelButton.getScene().getWindow());
    }

    private void updateTexts(Film film){
        lblFilmTitle.setText(film.getTitle());
        lblTime.setText("Time: " + film.getStartTime());
        lblPlaceAndWeek.setText("Place: " + film.getTheatreTitle() + ", Week " + film.getWeekNumber());
        filmImage.setImage(film.getImage());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FadeTransitionOwn.makeFadeTransition(rootPane);
        updateTexts(selectedFilm);
        myChoicesListView.setItems(ChooseTicketsPageController.myThings);
        customizeCell();
    }
    //Here I didn't create another class because there is not much data.
    public void customizeCell(){
        myChoicesListView.setCellFactory(listView -> new ListCell<MyThing>() {
            private ImageView imageView = new ImageView();
            public void updateItem(MyThing myThing, boolean empty) {
                super.updateItem(myThing, empty);
                if (empty || myThing == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if(myThing instanceof MySeats){
                        imageView.setImage(seatImage);
                    }else if(myThing instanceof Ticket){
                        imageView.setImage(ticketImage);
                    }else if(myThing instanceof Victual){
                        imageView.setImage(victualImage);
                    }
                    setText(myThing.toString());
                    setGraphic(imageView);
                }
            }

        });
    }
}
