package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegisterPageController implements Initializable {
    public JFXDatePicker dateOfBirthPicker;
    public JFXTextField surnameTF;
    public JFXTextField enterNameTF;
    public JFXButton registerButton;
    public Label lblNameError;
    public Label lblErrorSurname;
    public AnchorPane rootPane;
    public JFXPasswordField passwordFieldOne;
    public JFXPasswordField passwordFieldTwo;
    private Customers customers;
    private SearchEngine searchEngine;
    private int PASSWORD_LENGTH = 8;

    public RegisterPageController(){
        searchEngine = new SearchEngine();
        customers = LoginPageController.customers;
    }
    public void registerButtonPressed(ActionEvent actionEvent) {

        String name = enterNameTF.getText();
        String surname = surnameTF.getText();
        String passwordOne = passwordFieldOne.getText();
        String passwordTwo = passwordFieldTwo.getText();
        LocalDate birthDate = dateOfBirthPicker.getValue();
        LocalDate currentDate = LocalDate.now();

        if(checkFields(name, surname, passwordOne, passwordTwo, birthDate)) {
            if(!checkForIntegers(name, surname)){
                if(!checkWhetherRegistered(name, surname)) {
                    if (DateHandler.checkDate(birthDate, currentDate)) {
                        if (validatePassword(passwordOne, passwordTwo)) {
                            createCustomer(name, surname, passwordOne, DateHandler.calculateCustomersAge(birthDate, currentDate));
                            AlertMaker.showInformationClose(rootPane, "Close window and log in",
                                    "Register Successful", (Stage) registerButton.getScene().getWindow());
                        }
                    } else {
                        AlertMaker.showErrorAlert(rootPane, "Your Date is Wrong");
                    }
                }else{
                    AlertMaker.showErrorAlert(rootPane, "Customer with same name and surname already exists!");
                }
            }
        }
    }

    private boolean checkFields(String name, String surname, String passOne, String passTwo, LocalDate birthDate){

        if(name.trim().isEmpty() || surname.trim().isEmpty() || birthDate == null || passOne.trim().isEmpty() || passTwo.trim().isEmpty()){
            AlertMaker.showErrorAlert(rootPane, "Please Complete All Fields");
            return false;
        }else {
            if ((surname.length() > 15 && name.length() > 15)) {
                lblErrorSurname.setText("Check Surname Length");
                lblNameError.setText("Check Name Length");
                return false;
            } else if (name.length() > 15) {
                lblNameError.setText("Check Name Length");
                return false;
            } else if (surname.length() > 15) {
                lblErrorSurname.setText("Check Surname Length");
                return false;
            } else{
                return true;
            }
        }
    }

    private boolean checkForIntegers(String name, String surname){
        boolean nameHasInteger = name.matches(".*\\d.*");
        boolean surnameHasInteger = surname.matches(".*\\d.*");

        if(nameHasInteger && surnameHasInteger) {
            lblErrorSurname.setText("Your surname contains number");
            lblNameError.setText("Your name contains number");
            return true;
        }else if(nameHasInteger){
            lblNameError.setText("Your name contains number");
            return true;

        }else if(surnameHasInteger){
            lblErrorSurname.setText("Your surname contains number");
            return true;
        }else{
            return false;
        }
    }

    private void createCustomer(String name, String surname, String password, int age){
        customers.addCustomer(name, surname, password, age);
        DataSaver.saveCustomers(customers);

    }

    public void handleReleasedKey(KeyEvent keyEvent) {
        //When customer presses any key on the both text fields - clear them
        //It helps to determine problems in the next
        if(keyEvent.getSource() == enterNameTF){
            lblNameError.setText("");
        }else if(keyEvent.getSource() == surnameTF){
            lblErrorSurname.setText("");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DatePickerFormatConverter.convertDate(dateOfBirthPicker);
    }

    private boolean validatePassword(String passOne, String passTwo){
        if(!passOne.equals(passTwo)){
            AlertMaker.showWarning(rootPane, "Passwords Do Not Match");
            return false;
        }else {
            if (passOne.length() < PASSWORD_LENGTH) {
                AlertMaker.showWarning(rootPane, "Your password must contain at least 8 characters");
                return false;
            }else{
                return true;
            }
        }
    }
    private boolean checkWhetherRegistered(String name, String surname){
        Customer searchedCustomer = searchEngine.findCustomerByNameAndSurname(name, surname);
        if(searchedCustomer != null){
            return true;
        }else{

            return false;
        }
    }
}
