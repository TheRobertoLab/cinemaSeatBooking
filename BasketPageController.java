package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class BasketPageController implements Initializable {
    public Label lblNumOfItems;
    public ListView <BasketItem> listView;
    public Label lblTotalAmount;
    public Basket basket;
    public JFXButton paymentButton;
    public JFXButton deleteItemButton;
    public AnchorPane rootPane;
    public JFXButton cashButton;
    private Purchases purchases;
    private SearchEngine searchEngine;

    public BasketPageController(){
        searchEngine = new SearchEngine();
        basket = MainPageController.basket;
        purchases = MyPurchasesPageController.getPurchases();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        check();
        listView.setItems(basket);
        listView.setCellFactory(cell -> new CustomBasketCell());
        updateTexts();
        updateTotalAmountLbl();
    }

    public void updateTexts(){
        //Update label according to number of items in the basket
        if(basket.size() == 0){
            lblNumOfItems.setText("You have no items in your basket");
        }else if(basket.size() == 1){
            lblNumOfItems.setText("You have " + basket.size() + " item in your basket");
        }else{
            lblNumOfItems.setText("You have " + basket.size() + " items in your basket");
        }
    }

    public void updateTotalAmountLbl(){
        NumberFormat amount = NumberFormat.getCurrencyInstance(Locale.UK);
        lblTotalAmount.setText("Total Amount: " + amount.format(basket.calcTotalAmount()));
    }

    public void check(){
        if(basket.size() == 0){
            paymentButton.setDisable(true);
            deleteItemButton.setDisable(true);
            cashButton.setDisable(true);
        }else{
            paymentButton.setDisable(false);
            deleteItemButton.setDisable(false);
            cashButton.setDisable(false);
        }
    }

    public void deleteButtonPressed(ActionEvent actionEvent) {
        BasketItem selectedItem = listView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            basket.remove(selectedItem);
            AlertMaker.showNotification("Item deleted successfully");
            updateTexts();

            //Update total amount field and then label
            //totalAmount = basket.calcTotalAmount();
            updateTotalAmountLbl();
            //If now basket is empty, disable the buttons
            check();
            listView.getSelectionModel().clearSelection();
        }else{
            AlertMaker.showErrorAlert(rootPane, "Please select an Item");
        }
    }

    public void pressPaymentButton(ActionEvent actionEvent) {
        showPaymentWindow("PaymentWindow.fxml");
    }

    public void pressCashButton(ActionEvent actionEvent) {
        NumberFormat gb = NumberFormat.getCurrencyInstance(Locale.UK);
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you confirm that you want to pay by cash?", ButtonType.YES, ButtonType.NO);
        confirmation.setHeaderText("Please Confirm");
        confirmation.showAndWait();

        if(confirmation.getResult() == ButtonType.YES){
            purchases.addPurchasesPaidByCash(basket);

            for(BasketItem bi : basket){
                Theatre searchedTheatre = searchEngine.searchTheatre(bi.getTheatreTitle(), bi.getWeekNumber());
                searchedTheatre.makeSelectedSeatsUnavailable();
                searchedTheatre.reduceAvailableSeats(ChooseTicketsPageController.getNumOfTickets());
            }

            AlertMaker.showInformation("You can review your purchases in My Purchases section" +
                    "\nCost of the purchase: " + gb.format(basket.getTotalAmount()), "You Should Show Your Ticket to the Cashier");
            basket.clear();
            updateTexts();
            updateTotalAmountLbl();
            check();

        }
    }

    public void showPaymentWindow(String path) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(root, 752, 672);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
