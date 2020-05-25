package sample;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class FadeTransitionOwn {
    public static void makeFadeTransition(Node node){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1500));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(0.1);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);
        fadeTransition.play();
    }
}
