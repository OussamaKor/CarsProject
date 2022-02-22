import javafx.collections.ObservableList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;


public class CarPane extends Pane {
    private final int heigth ;
    private final int width ;
    
    private double posX ;
    private double posY ;
    private final double Radius ;
    
    
    public CarPane(int h , int w)
    {
        heigth = h ;
        width = w ;
        posX=0 ;
        posY= 300 ;
        Radius = w*0.1 ;  
        DrawCar() ;
    }
    public void DrawCar()
    {
        Circle roue1 ;
        Circle roue2  ;
        ObservableList<Double> points;
        Rectangle base ;
        Polyline cover = new Polyline() ;
        roue1 = new Circle(posX+Radius*3,posY-Radius,Radius);
        roue1.setStroke(Color.BLACK);
        roue1.setFill(Color.BLACK);
        roue2 = new Circle(posX+Radius*7,posY-Radius,Radius);
        roue2.setStroke(Color.BLACK);
        roue2.setFill(Color.BLACK);

        
        base=new Rectangle(posX,posY-Radius*4,heigth,width/5);
        base.setStroke(Color.BLACK);
        base.setFill(Color.BLUE);
        
        points = cover.getPoints();
        
       cover.getPoints().clear();
        
        points.addAll(posX+Radius*2, posY-Radius*4); // start point
        points.addAll(posX+Radius*4,posY-Radius*6); // up right
        points.addAll(posX+Radius*6, posY-Radius*6); // right
        points.addAll(posX+Radius*8, posY-Radius*4); // down right
        points.addAll(posX+Radius*2,posY-Radius*4); // connect to starting point
     
        getChildren().clear();
        getChildren().addAll(roue1, roue2 , base, cover);
        
        cover.setFill(Paint.valueOf("#2ea3dd"));
        cover.setStroke(Color.TRANSPARENT) ;  
    }
    public void moveCar(int Distance)
    {
        if (posX<1000)
            posX=posX+Distance ;
        else
            posX=0 ;
        DrawCar() ;
    }
    public void jumpCar(int Distance)
    {
        posY=posY-Distance ;

        DrawCar() ;
        if(posY<200)
        {
            posY= 200;
            DrawCar() ;
        }
    }
    
    
    
    
    
    
}
