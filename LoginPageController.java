package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    //JavaFX objects declaration
    public JFXButton startButton;
    public Label nameError;
    public Label surnameError;
    public JFXTextField nameTF;
    public JFXTextField surnameTF;
    public AnchorPane rootPane;
    public JFXButton registerButton;
    public JFXPasswordField passwordTF;
    private SearchEngine searchEngine;
    private Customer searchedCustomer;

    public static Customer loggedCustomer;
    public static Customers customers;

    public LoginPageController(){
        customers = new Customers();
        searchEngine = new SearchEngine();
    }
    public static Customers getLoadedCustomers(){
        return customers;
    }
    //There is a problem with setResizable, when it false, it changes a little bit a window size, so I use this only on the Main Page.
    public void loadNewWindow(String location, int width, int height, int minHeight, int minWidth, boolean resizable){
        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(location));
            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);
            stage.setResizable(resizable);
            stage.setMinHeight(minHeight);
            stage.setMinWidth(minWidth);
            stage.getIcons().add(new Image("sample/assets/quadLogo.png"));
            stage.show();

        }catch (IOException e) {
            System.out.println("I cannot display the next window.");
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
        loadCustomers();
        System.out.println(customers);
    }

    private void setLoggedUser(Customer customer){
        loggedCustomer = customer;
    }

    public static Customer getLoggedCustomer(){
        return loggedCustomer;
    }
    public void loginButtonPressed(ActionEvent actionEvent) {
        String name = nameTF.getText();
        String surname = surnameTF.getText();
        String password = passwordTF.getText();

        //During this method's implementation, I thought about security,
        //so user cannot see whether he entered details existing in DB or not,
        //he cannot know whether he typed wrong name/surname or password,
        //it just prints "Check Your Details". Nice

        if(checkFields(name, surname, password)) {
            if (checkWhetherRegistered(name, surname)) {
                if (searchedCustomer.getPassword().equals(password)) {
                    setLoggedUser(searchedCustomer);
                    Stage currentStage = (Stage) startButton.getScene().getWindow();
                    currentStage.close();
                    loadNewWindow("MainPage.fxml", 1436, 847, 550, 1020, false);
                    AlertMaker.showNotification("Logged Success");
                }else{
                    AlertMaker.showErrorAlert(rootPane, "Check your details");
                }
            }else{
                AlertMaker.showWarning(rootPane, "Check your details");
            }
        }else{
            AlertMaker.showErrorAlert(rootPane, "Please complete all fields");
        }
    }

    public void registerButtonPressed(ActionEvent actionEvent) {
        loadNewWindow("RegisterPage.fxml", 493, 582, 582, 193, true);
    }

    private boolean checkWhetherRegistered(String name, String surname){
        searchedCustomer = searchEngine.findCustomerByNameAndSurname(name, surname);
        if(searchedCustomer != null){
            return true;
        }else{

            return false;
        }
    }
    private boolean checkFields(String name, String surname, String pass){
        if(name.trim().isEmpty() || surname.trim().isEmpty() || pass.trim().isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    private void loadCustomers(){
        try {

            FileInputStream in = new FileInputStream("Customers.dat");
            ObjectInputStream oIn = new ObjectInputStream(in);
            List<Customer> list = (List<Customer>)oIn.readObject();
            customers.addAll(list);
            oIn.close();

        } catch (Exception e) {

            AlertMaker.showWarning(rootPane, "Failed to Load the Data");
            System.out.println("Error : " + e);

        }
    }
}
