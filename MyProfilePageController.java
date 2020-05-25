package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MyProfilePageController implements Initializable {
    private int PASSWORD_LENGTH = 8;

    public JFXDatePicker datePicker;
    public JFXButton saveButton;
    public Label lblCustomerName;
    public Label lblCustomerAge;
    public JFXButton uploadButton;
    public ImageView profileImage;
    public JFXButton savePasswordButton;
    public JFXPasswordField oldPassTF;
    public JFXPasswordField newPassTF;
    public JFXPasswordField repeatPassTF;
    public AnchorPane rootPane;

    private Customer loggedCustomer;

    public MyProfilePageController(){
        loggedCustomer = LoginPageController.getLoggedCustomer();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DatePickerFormatConverter.convertDate(datePicker);
        setCustomerImage(profileImage, loggedCustomer);
        updateTexts(loggedCustomer);
    }
    private void updateTexts(Customer customer){
        lblCustomerName.setText("Name: " + customer.getName() + " " + customer.getSurname());
        lblCustomerAge.setText(customer.getAge() + " years old");
        
    }

    public void uploadButtonPressed(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Files .png, .jpg", "*.png", " *.jpg");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());
        if(file != null){
            updateCustomerImage(file, profileImage);
            loggedCustomer.setPathToImage(file.toURI().toString());
            DataSaver.saveCustomers(LoginPageController.customers);
        }
    }

    public void saveButtonPressed(ActionEvent actionEvent) {
        LocalDate date = datePicker.getValue();
        LocalDate currentDate = LocalDate.now();

        if(date != null){
            if(DateHandler.checkDate(date, currentDate)){
                loggedCustomer.setAge(DateHandler.calculateCustomersAge(date, currentDate));
                AlertMaker.showInformation("Now you are " + loggedCustomer.getAge() + " years old", "Date of Birth Changed Successfully");
                updateTexts(loggedCustomer);
                DataSaver.saveCustomers(LoginPageController.getLoadedCustomers());
            }else{
                AlertMaker.showErrorAlert(rootPane, "Wrong date. You cannot born in the future :)");
            }
        }else{
            AlertMaker.showErrorAlert(rootPane, "Select a Date");
        }
    }

    private void updateCustomerImage(File file, ImageView imageView){
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    private void setCustomerImage(ImageView imageView, Customer customer){
        imageView.setImage(new Image(customer.getPathToImage()));
    }

    public void savePasswordPressed(ActionEvent actionEvent) {
        String passwordOld = oldPassTF.getText();
        String passNew = newPassTF.getText();
        String repeatedNew = repeatPassTF.getText();

        if(passwordOld.trim().isEmpty() || passNew.trim().isEmpty() || repeatedNew.trim().isEmpty()){
            AlertMaker.showErrorAlert(rootPane, "Please Complete All Fields");
        }else{
            if(!passwordOld.equals(loggedCustomer.getPassword())){
                clearFields();
                AlertMaker.showErrorAlert(rootPane, "Wrong details");
            }else if(passwordOld.equals(passNew)){
                clearFields();
                AlertMaker.showErrorAlert(rootPane, "This password already used");
            }else if(!passNew.equals(repeatedNew)){
                clearFields();
                AlertMaker.showErrorAlert(rootPane, "Passwords do not match");
            }else if(passNew.length() < PASSWORD_LENGTH){
                clearFields();
                AlertMaker.showErrorAlert(rootPane, "Password must contain at least 8 characters");
            }else{
                clearFields();
                loggedCustomer.setPassword(passNew);
                //Update file
                DataSaver.saveCustomers(LoginPageController.getLoadedCustomers());
                AlertMaker.showInformation("Your new password is: " + passNew, "Password Changed Successfully");
            }
        }
    }
    private void clearFields(){
        oldPassTF.setText("");
        newPassTF.setText("");
        repeatPassTF.setText("");
    }
}
