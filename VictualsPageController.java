package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VictualsPageController implements Initializable {
    public AnchorPane rootPane;
    public ListView<Victual> victualListView;
    public JFXButton cancelButton;
    public JFXButton addBasketButton;
    public JFXButton confirmButton;
    private MyThings myThings;
    private Victuals victuals;

    public VictualsPageController(){
        myThings = ChooseTicketsPageController.myThings;
        victuals = new Victuals();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        makeFadeIn(rootPane);
        //Firsly Initialise all Victuals
        victualListView.getItems().addAll(victuals);
        victualListView.setCellFactory(cell -> new CustomVictualCell());
    }
    public void makeFadeIn(Node node){
        FadeTransitionOwn.makeFadeTransition(node);
    }

    public void pressShowSnacks(ActionEvent actionEvent) {
        //Update list by Snacks
        makeFadeIn(victualListView);
        victualListView.getItems().clear();
        for(Victual vc : victuals){
            if(vc.getType().equals("Snack"))
                victualListView.getItems().addAll(vc);
        }
    }

    public void pressShowDrinks(ActionEvent actionEvent) {
        //Update list by Drinks
        makeFadeIn(victualListView);
        victualListView.getItems().clear();
        for(Victual vc : victuals){
            if(vc.getType().equals("Drink"))
                victualListView.getItems().addAll(vc);
        }
    }

    public void pressCancel(ActionEvent actionEvent) {
        AlertMaker.showConfirmationToClose("Are you sure you want to close?", "You Choice Won't Save",
                (Stage) cancelButton.getScene().getWindow());
    }

    public void pressAddToBasket(ActionEvent actionEvent) {
        Victual selectedVictual = victualListView.getSelectionModel().getSelectedItem();
        if(selectedVictual != null){
            //Maximum customer can add 5 victuals to the basket, because if not, there will be no space to print it in the document.
            if(myThings.countNumOfAddedVictuals() < 5) {
                //Create and add victual
                myThings.addMyVictual(selectedVictual.getTitle(), selectedVictual.getType(), selectedVictual.getPrice(), selectedVictual.getCapacity());
                AlertMaker.showNotification(selectedVictual.getTitle() + " has been added to the basket");
                victualListView.getSelectionModel().clearSelection();
            }else{
                AlertMaker.showErrorAlert(rootPane, "Your basket of Victuals is full, please press confirm button");
            }
        }else{
            AlertMaker.showErrorAlert(rootPane, "Please Select a Victual");
        }
    }

    public void pressConfirm(ActionEvent actionEvent) {
        displayChoiceSummary("ChoiceSummaryPage.fxml");
    }
    public void displayChoiceSummary(String path){
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
