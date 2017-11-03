/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gridtest;

import static com.sun.javafx.geom.Curve.next;
import java.awt.Font;
import java.awt.Graphics;
import javafx.scene.input.*;
import java.awt.Point;
import java.awt.PointerInfo;
//import java.awt.event.MouseEvent;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author sibev
 */
public class Gridtest extends Application {
   
   public boolean isStart = false;
   public boolean isEnd = false;   
   public Rectangle currentStartPos = null;
   public Rectangle currentEndPos = null;
   public Scene scene;
   public Group root;
   
   @Override
   public void start(Stage stage)
   {
       scene = getScene();
        Button button = new Button("new grid");
        button.setOnAction((ActionEvent e) -> {
           scene = null;
           scene = getScene();
           root.getChildren().add(button);
           stage.setScene(scene);
        });
        button.setLayoutX(800);
        button.setLayoutY(125);
        root.getChildren().add(button);
       stage.setTitle("test");
       stage.setScene(scene);
       stage.setResizable(false);
       stage.setHeight(scene.getHeight());
       stage.setWidth(scene.getWidth());
       stage.show();
   }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    } 
    
    public boolean nextBoolean()
    {
        Random random = new Random();
        return random.nextBoolean();
    }
    
    public Scene getScene()
    {
       int x = 0;
       int y = 0;
       int i;
       MyRectangle myRec;
       Rectangle[] recArray = new Rectangle[3750];
       MyRectangle[] myRecArray = new MyRectangle[3750];           
       root = new Group();
       Scene scene = new Scene(root,1250,530, Color.WHITE);//was 750
       //Text text = new Text (10,40, "hellow world!");       
       
       for(i = 0; i < 3750; i++)
       {         
           if(x == 0 || x == 740)
           {
               myRec = new MyRectangle(x,y,10, true);                  
           } 
           else{
               myRec = new MyRectangle(x,y,10, nextBoolean());
           }
           
                Rectangle r = myRec.getRectangle();  
                r.setOnMouseClicked((MouseEvent event) -> {
                    if(isStart && !isEnd){
                        if(currentStartPos == null)
                        {
                            currentStartPos = r;
                            r.setFill(Color.CHARTREUSE);
                        }
                        else
                        {
                            currentStartPos.setFill(Color.TRANSPARENT);
                            currentStartPos = r;
                            currentStartPos.setFill(Color.CHARTREUSE);
                        }
                        
                    }
                    else if(isEnd && !isStart)
                    {
                        if(currentEndPos == null)
                        {
                            currentEndPos = r;
                            r.setFill(Color.CORNFLOWERBLUE);
                        }
                        else
                        {
                            currentEndPos.setFill(Color.TRANSPARENT);
                            currentEndPos = r;
                            currentEndPos.setFill(Color.CORNFLOWERBLUE);                    
                        }
                    }
                    else
                    {
                       System.out.println("Blok passable is:" + getPassable(r));
                    }
                });
                myRecArray[i] = myRec;                
                recArray[i] = r; 
                if(x >= 740)
                {
                    x = 0;
                    y += 10;
                }
                else
                {
                 x += 10;
                }
                
       }
       root.getChildren().addAll(recArray);
       Text uiAKnop = new Text(800, 50,"Druk op 'A' om een start positie te kiezen aan of uit te zetten");
       Text uiDKnop = new Text(800, 75,"Druk op 'D' om een eind positie te kiezen aan of uit te zetten");
       
       root.getChildren().addAll(uiAKnop,uiDKnop);
       scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
                   @Override
                   public void handle(KeyEvent e){
                       KeyCode keyCode = e.getCode();
                       switch (keyCode)
                       {
                           case A: isStart = !isStart;
                                   isEnd = false;
                                   break;
                           case D: isStart = false;
                                   isEnd = !isEnd; 
                       }                      
                    }
                });
       return scene;
    }
    
    public boolean getPassable(Rectangle r){
        if(r.getFill() == Color.BLACK){
            return false;
        }
        else
            return true;
    }
    
        
    
    
    private static class EventHandlerImpl implements EventHandler<MouseEvent> {

        private final Rectangle r;

        public EventHandlerImpl(Rectangle r) {
            this.r = r;
        }

        public void handle(MouseEvent me)
        {
            r.setFill(Color.RED);
        }
    }
}
