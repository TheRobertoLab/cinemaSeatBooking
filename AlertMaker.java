package sample;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


public class AlertMaker {
    //I made it to create alerts quickly
    public static void showErrorAlert(Node node, String text){
        BoxBlur blur = new BoxBlur(4,4,4);
        node.setEffect(blur);
        Alert error = new Alert(Alert.AlertType.ERROR, text, ButtonType.OK);
        error.showAndWait();

        if(error.getResult() == ButtonType.OK){
            node.setEffect(null);
        }
    }
    public static void showErrorAlertWithHeader(Node node, String text, String header){
        BoxBlur blur = new BoxBlur(4,4,4);
        node.setEffect(blur);
        Alert error = new Alert(Alert.AlertType.ERROR, text, ButtonType.OK);
        error.setHeaderText(header);
        error.showAndWait();

        if(error.getResult() == ButtonType.OK){
            node.setEffect(null);
        }
    }
    public static void showWarning(Node node, String text){
        BoxBlur blur = new BoxBlur(4,4,4);
        node.setEffect(blur);
        Alert error = new Alert(Alert.AlertType.WARNING, text, ButtonType.OK);

        error.showAndWait();

        if(error.getResult() == ButtonType.OK){
            node.setEffect(null);
        }
    }
    public static void showWarningWithoutNode(String text){
        Alert error = new Alert(Alert.AlertType.WARNING, text, ButtonType.OK);

        error.showAndWait();

    }
    public static void showWarningWithText(String text, String header){
        Alert warning = new Alert(Alert.AlertType.WARNING, text, ButtonType.OK);
        warning.setHeaderText(header);
        warning.showAndWait();
    }
    public static void showInformationClose(Node node, String text, String header, Stage stage){
        BoxBlur blur = new BoxBlur(4,4,4);
        node.setEffect(blur);
        Alert information = new Alert(Alert.AlertType.INFORMATION, text, ButtonType.OK);
        information.setHeaderText(header);
        information.showAndWait();

        if(information.getResult() == ButtonType.OK){
            stage.close();
        }
    }
    public static void showInformation(String text, String header){
        Alert information = new Alert(Alert.AlertType.INFORMATION, text, ButtonType.OK);
        information.setHeaderText(header);
        information.showAndWait();
    }
    public static void showConfirmationToClose(String text, String header, Stage stage){
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, text, ButtonType.YES, ButtonType.NO);
        confirmation.setHeaderText(header);
        confirmation.showAndWait();
        if(confirmation.getResult() == ButtonType.YES){
            stage.close();
        }
    }
    public static void showConfirmationToClear(Node node, String text, String header, Purchases purchases){
        BoxBlur blur = new BoxBlur(4,4,4);
        node.setEffect(blur);
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, text, ButtonType.YES, ButtonType.NO);
        confirmation.setHeaderText(header);
        confirmation.showAndWait();
        if(confirmation.getResult() == ButtonType.YES){
            purchases.clear();
            node.setEffect(null);
        }else if(confirmation.getResult() == ButtonType.NO){
            node.setEffect(null);
        }
    }
    public static void showNotification(String text){
        Image image = new Image("sample/Assets/confirmationBigger.png");
        Notifications notifications = Notifications.create().title("Success").position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(5))
                .text(text).graphic(new ImageView(image));
        notifications.show();
    }
}
