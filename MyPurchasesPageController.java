package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MyPurchasesPageController implements Initializable {
    public AnchorPane rootPane;
    public Label lblTopInfo;
    public ListView <Purchase> purchasesListView;
    public JFXButton clearButton;
    public static Purchases purchases = new Purchases();
    public JFXButton deleteItemButton;
    public Customer loggedCustomer;

    public MyPurchasesPageController(){
        loggedCustomer = LoginPageController.getLoggedCustomer();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        purchasesListView.setItems(purchases);
        purchasesListView.setCellFactory(cell -> new CustomPurchaseCellController());
        check();
        updateText();
    }
    public static Purchases getPurchases(){
        return purchases;
    }
    public void check(){
        if(purchases.size() == 0){
            clearButton.setDisable(true);
            deleteItemButton.setDisable(true);
        }
    }

    public void clearButtonPressed(ActionEvent actionEvent) {
        AlertMaker.showConfirmationToClear(rootPane, "Undo is not available", "Are you sure you want to clear?", purchases);
        updateText();
        check();
        purchasesListView.getSelectionModel().clearSelection();
    }
    public void updateText(){
        if(purchases.size() == 0){
            lblTopInfo.setText("You have no purchases");
        }else if(purchases.size() == 1){
            lblTopInfo.setText("You have 1 purchase");
        }else{
            lblTopInfo.setText("You have " + purchases.size() + " purchases");
        }
    }
    public void deleteButtonPressed(ActionEvent actionEvent) {
        Purchase selectedPurchase = purchasesListView.getSelectionModel().getSelectedItem();
        if(selectedPurchase != null ){
            purchases.remove(selectedPurchase);
            updateText();
            check();
            purchasesListView.getSelectionModel().clearSelection();
            AlertMaker.showNotification("Purchase has been deleted successfully");
        }else{
            AlertMaker.showWarning(rootPane, "Please select an item");
        }
    }
}
