package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    public Button basketButton;
    public Button bookTicketsBtn;
    public Button exitButton;
    public Button editDetails;
    public AnchorPane anchorPaneHolder;
    public Button myPurchasesButton;
    public Label nameLabel;
    private Customer loggedCustomer;
    private AnchorPane choicesPage;


    //I need to create these objects at once
    // and then update them, so I created their here
    public static Theatres theatres = new Theatres();
    public static Films films = new Films();
    public static Cards cards = new Cards();
    public static Basket basket = new Basket();

    public MainPageController(){
        loggedCustomer = LoginPageController.getLoggedCustomer();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
        nameLabel.setText(loggedCustomer.getName()+ " " + loggedCustomer.getSurname());
        //Firstly load for the customer choices page
        try {
            choicesPage = FXMLLoader.load(getClass().getResource("ChoicesPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Update holder pane with new window.
        setNode(choicesPage);
    }

    private void setNode(Node node) {
        anchorPaneHolder.getChildren().clear();
        anchorPaneHolder.getChildren().add(node);

        FadeTransitionOwn.makeFadeTransition(node);
    }

    public void onMouseEntered(MouseEvent mouseEvent) {
        //Change button color when mouse is entered
        if(mouseEvent.getSource() == bookTicketsBtn){
            bookTicketsBtn.setStyle("-fx-background-color: #d1d1d1");
        }else if(mouseEvent.getSource() == myPurchasesButton) {
            myPurchasesButton.setStyle("-fx-background-color: #d1d1d1");
        }else if(mouseEvent.getSource() == basketButton) {
            basketButton.setStyle("-fx-background-color: #d1d1d1");
        }else if(mouseEvent.getSource() == exitButton) {
            exitButton.setStyle("-fx-background-color: #d1d1d1");
        }else if(mouseEvent.getSource() == editDetails) {
            editDetails.setStyle("-fx-background-color: #d1d1d1");
        }
    }

    public void onMouseExited(MouseEvent mouseEvent) {
        //Return button colors when mouse is exited
        if (mouseEvent.getSource() == bookTicketsBtn) {
            bookTicketsBtn.setStyle("-fx-background-color:  #dddddd");
        } else if (mouseEvent.getSource() == myPurchasesButton) {
            myPurchasesButton.setStyle("-fx-background-color:  #dddddd");
        } else if (mouseEvent.getSource() == basketButton) {
            basketButton.setStyle("-fx-background-color:  #dddddd");
        } else if (mouseEvent.getSource() == exitButton) {
            exitButton.setStyle("-fx-background-color:  #dddddd");
        } else if (mouseEvent.getSource() == editDetails) {
            editDetails.setStyle("-fx-background-color: #d6d6d6");
        }
    }

    //All these windows system shows on the Main Page by adding node to the holder pane.
    public void pressBookTicketsButton(ActionEvent actionEvent) throws IOException {
        choicesPage = FXMLLoader.load(getClass().getResource("ChoicesPage.fxml"));
        setNode(choicesPage);
    }

    public void pressMyPurchasesButton(ActionEvent actionEvent) throws IOException {
        AnchorPane purchasesPage = FXMLLoader.load(getClass().getResource("MyPurchasesPage.fxml"));
        setNode(purchasesPage);
    }

    public void pressMyProfileButton(ActionEvent actionEvent) throws IOException {
        AnchorPane profilePage = FXMLLoader.load(getClass().getResource("MyProfilePage.fxml"));
        setNode(profilePage);
    }

    public void pressBasketButton(ActionEvent actionEvent) throws IOException {
        AnchorPane basketPage = FXMLLoader.load(getClass().getResource("BasketPage.fxml"));
        setNode(basketPage);
    }

    public void pressExit(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        AlertMaker.showConfirmationToClose("Are your sure want to exit?", "Your choices will not save" , stage);
    }
}
