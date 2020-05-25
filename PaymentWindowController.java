package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class PaymentWindowController implements Initializable {
    public AnchorPane rootPane;
    public Label lblTotalAmount;
    public JFXCheckBox masterCardCheck;
    public JFXCheckBox visaCheck;
    public JFXTextField nameTF;
    public JFXTextField numberTF;
    public JFXComboBox<Integer> mmExpireCombo;
    public JFXComboBox<Integer> yyExpireCombo;
    public JFXTextField discountCodeTF;
    public JFXButton applyButton;
    public JFXTextField cvvTF;
    public JFXButton declineButton;
    public JFXButton payButton;
    private boolean usedCode;
    public static Card searchedCard;
    private SearchEngine searchEngine;
    private Basket basket = MainPageController.basket;
    private Purchases purchases = MyPurchasesPageController.purchases;

    private DiscountCodes discountCodes;

    public PaymentWindowController(){
        discountCodes = new DiscountCodes();
        searchEngine = new SearchEngine();
        usedCode = false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateText();
        fillComboBoxes();

    }
    private void fillComboBoxes(){
        mmExpireCombo.getItems().setAll(1,2,3,4,5,6,7,8,9,10,11,12);

        int year = Calendar.getInstance().get(Calendar.YEAR);
        yyExpireCombo.getItems().setAll(year+1,year+2,year+3,year+4,year+5,year+6,year+7,year+8,year+9,year+10);
    }
    private void updateText(){
        NumberFormat amount = NumberFormat.getCurrencyInstance(Locale.UK);
        lblTotalAmount.setText("" + amount.format(basket.getTotalAmount()));
    }

    public void pressPayButton(ActionEvent actionEvent) {
        String cardNumber = numberTF.getText();
        searchedCard = searchEngine.searchCard(cardNumber);

        int numOfAddedTickets = ChooseTicketsPageController.getNumOfTickets();

        if(!checkFields()){
            AlertMaker.showErrorAlertWithHeader(rootPane, "Please complete all fields", "There are Empty Fields");
        }else {
            if (searchedCard == null) {
                AlertMaker.showErrorAlert(rootPane, "There is no card with " + cardNumber + " number.");
            }else{
                if(validateCard(searchedCard)){
                    if(searchedCard.getBalance() >= basket.getTotalAmount()){
                        //Reduce balance on the customer card
                        searchedCard.setBalance(searchedCard.getBalance()-basket.getTotalAmount());
                        //Move all basket items to purchases
                        purchases.addPurchasesPaidByCard(basket);

                        //From each basket item in the basket,
                        //find theatre and reduce available seats.
                        for(BasketItem bi : basket){
                            Theatre searchedTheatre = searchEngine.searchTheatre(bi.getTheatreTitle(), bi.getWeekNumber());
                            searchedTheatre.makeSelectedSeatsUnavailable();
                            searchedTheatre.reduceAvailableSeats(numOfAddedTickets);
                        }

                        basket.clear();
                        AlertMaker.showInformationClose(rootPane,"Now your balance amount: " + searchedCard.getBalance(),
                                "\nYou can review your purchases in 'My Purchases'", (Stage) payButton.getScene().getWindow());

                    }else{
                        AlertMaker.showErrorAlert(rootPane, "There is no enough balance in the card");
                    }
                }else{
                    AlertMaker.showErrorAlert(rootPane,"Details are wrong");
                }
            }
        }
    }

    public void pressDeclineButton(ActionEvent actionEvent) {
        AlertMaker.showConfirmationToClose("Card details won't be saved",
                "Are you sure you want to close?", (Stage) declineButton.getScene().getWindow());
    }

    public void pressApplyButton(ActionEvent actionEvent) {
        String enteredCode = discountCodeTF.getText();
        if(usedCode){
            AlertMaker.showErrorAlertWithHeader(rootPane, "You can use а code once per payment", "А Code is Used");
        }else{
            DiscountCode discountCode = discountCodes.findDiscountCode(enteredCode);
            if(discountCode != null){
                basket.reduceAmountByCode(discountCode);
                updateText();
                usedCode = true;
                AlertMaker.showNotification("Total amount reduced successfully");
            }else{
                AlertMaker.showWarning(rootPane, "There is no code " + enteredCode);
            }
        }
    }

    private boolean checkFields(){
        if(numberTF.getText().isEmpty() || nameTF.getText().isEmpty()
                || cvvTF.getText().isEmpty() || yyExpireCombo.getValue() == null
                || mmExpireCombo.getValue() == null || (!visaCheck.isSelected() && !masterCardCheck.isSelected())) {
            return false;

        }else{
            return true;
        }
    }
    private boolean validateCard(Card searchedCard){
        if(numberTF.getText().equals(searchedCard.getCardNumber())
                && cvvTF.getText().equals(searchedCard.getCvv())
                && mmExpireCombo.getValue().equals(searchedCard.getMonthOfExpiry())
                && yyExpireCombo.getValue().equals(searchedCard.getYearOfExpiry())
                && nameTF.getText().equals(searchedCard.getHoldersFullName())){
            if(masterCardCheck.isSelected() && searchedCard.getPaymentMethod().equals("MasterCard")
                    || visaCheck.isSelected() && searchedCard.getPaymentMethod().equals("Visa")){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public void masterCardCheckSelected(ActionEvent actionEvent) {
        visaCheck.setSelected(false);
    }

    public void visaCheckPressed(ActionEvent actionEvent) {
        masterCardCheck.setSelected(false);
    }
}
