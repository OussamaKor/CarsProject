


import javaapplication2.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import javafx.scene.paint.Color;



import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class FourCars extends Application{
    protected HBox paneForSpeed = new HBox(10);
	protected VBox paneForCars = new VBox(10);
	protected ObservableList<Node> textFields = paneForSpeed.getChildren();
	protected ObservableList<Node> cars = paneForCars.getChildren();

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		int Nb_Cars = 4;

		paneForSpeed.setAlignment(Pos.CENTER);

		
		for (int i = 0; i < Nb_Cars; i++) {
			paneForSpeed.getChildren().addAll(
                                
				new Label("Car " + (i + 1) + ":"), new TextField());
		}

		
		for (int i = 1; i < textFields.size(); i+= 2) {
			((TextField)textFields.get(i)).setPrefColumnCount(2);
		}

		
		for (int i = 0; i < Nb_Cars; i++) {
                        CarPanes car = new CarPanes();
			paneForCars.getChildren().add(car);
                        car.setStyle("-fx-border-color: black");
			car.setY(40);
		}

		
		

		for (int i = 1; i < textFields.size(); i += 2) {
			((TextField)textFields.get(i)).setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.ENTER) {
					ChangeSpeed();
				}
			});

		}
		
		
		BorderPane pane = new BorderPane();
		pane.setTop(paneForSpeed);
		pane.setCenter(paneForCars);

		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, 400, 190);
		primaryStage.setTitle("FourRacing"); 
		primaryStage.setScene(scene); 
		primaryStage.show(); 
	}

	
	private void ChangeSpeed() {
		for (int i = 1, j = 0; i < textFields.size(); i += 2, j++) {
			if (((TextField)textFields.get(i)).getText().length() > 0) {
				((CarPanes)cars.get(j)).ChangeSpeed(
					Double.parseDouble(((TextField)textFields.get(i)).getText()));
				((CarPanes)cars.get(j)).play();
			}
			else {
				((CarPanes)cars.get(j)).pause();
			}
		}
	}
         public static void main(String[] args) {
        Application.launch(args);

    }
    
}




    class CarPanes extends Pane {
	private double x = 0;
	private double y = 100;
	private double radius = 5;
	private Rectangle rectangle;
	private Polygon polygon;
	private Circle circle1;
	private Circle circle2;
	private Timeline animation;

	
	public CarPanes() {
		drawCar();
		animation = new Timeline(
			new KeyFrame(Duration.millis(50), e -> moveCar(1)));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
	}

	
	private void drawCar() {
		getChildren().clear();
		rectangle = new Rectangle(x, y - 20, 50, 10);
		polygon = new Polygon(x + 10, y - 20, x + 20, y - 30, x + 30, 
			y - 30, x + 40, y - 20);
		circle1 = new Circle(x + 15, y - 10, radius);
		circle2 = new Circle(x + 35, y - 10, radius);
                rectangle.setFill(Color.BLUE);
                polygon.setFill(Paint.valueOf("#2ea3dd"));
                circle1.setFill(Color.BLACK);
                circle2.setFill(Color.BLACK);
		getChildren().addAll(rectangle, circle1, circle2, polygon);
	}
       
	public void setY(double y) {
		this.y = y;
	}

	
	public void setX(double x) {
		this.x = x;
	}

	
	public double getX() {
		return x;
	}

	
	public double getY() {
		return y;
	}

	
	public void pause() {
		animation.pause();
	}

	
	public void play() {
		animation.play();
	}
        
        
	public void ChangeSpeed(double speed) {
		if (speed <= 100)
			animation.setRate(speed);
	}

	
	public void increaseSpeed() {
		animation.setRate(animation.getRate() + 1);
	}

	
	public void decreaseSpeed() {
                if(animation.getRate() >0)
                {
                    animation.setRate(animation.getRate()- 1) ;
                }
		
	}

	
	protected void moveCar(int Distance) {
		if (x <= getWidth()) {
			x += Distance;	
		} 
		else
			x = 0;

		drawCar();
	}
}