import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.KeyFrame ;
import javafx.animation.Timeline ;
import javafx.scene.input.KeyCode;
import javafx.util.Duration ;

public class Car extends Application {
@Override 
public void start(Stage primaryStage) {
    CarPane Car = new CarPane(100,100);
    Scene scene = new Scene(Car,1000, 300);
    EventHandler<ActionEvent>
    eventHandler = e -> { 
        scene.setOnKeyPressed(ke -> {
            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.UP)) {
                Car.moveCar(10) ;
                return;
            }
            if (keyCode.equals(KeyCode.DOWN)) {
                Car.moveCar(-7) ;
                return;
            }
            if (keyCode.equals(KeyCode.SPACE)) {

                Car.jumpCar(100) ;

                return;
            }

        });
        scene.setOnKeyReleased(kr ->{
            KeyCode keyCode = kr.getCode();
            if (keyCode.equals(KeyCode.SPACE)) {
                Car.jumpCar(-100) ;
              return;
            }
        }) ;
        Car.moveCar(4) ;
    } ;
Timeline animation = new Timeline(
    new KeyFrame(Duration.millis(10),eventHandler) ) ;
    animation.setCycleCount(Timeline.INDEFINITE) ;
    animation.play();
    
    scene.setOnMousePressed(e->{
    animation.pause() ;
    }) ;
    
    scene.setOnMouseReleased(e->{
    animation.play() ;
    }) ;

primaryStage.setTitle("CrazyCar");

primaryStage.setScene(scene); 

primaryStage.show(); }

public static void main(String[] args) {

Application.launch(args);} }
