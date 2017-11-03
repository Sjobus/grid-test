/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gridtest;

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
   @Override
   public void start(Stage stage)
   {
       int x = 0;
       int y = 0;
       int i;
       
       Rectangle[] recArray = new Rectangle[150];
       Random random;    
       Group root = new Group();
       Scene scene = new Scene(root,1250,530, Color.WHITE);//was 750
       //Text text = new Text (10,40, "hellow world!");
       Button button = new Button("klick me!");
       
       for(i = 0; i < 150; i++)
       {         
                Rectangle r = new Rectangle(x,y,50,50);
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.DIMGREY);
                r.setStrokeWidth(3);
                r.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
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
                          r.setFill(Color.BLACK);  
                        }                            
                    }                    
                });                
                recArray[i] = r; 
                if(x >= 700)
                {
                    x = 0;
                    y += 50;
                }
                else
                {
                 x += 50;
                }
                
       }
       root.getChildren().addAll(recArray);
       Text uiAKnop = new Text(800, 50,"Druk op 'A' om een start positie te kiezen aan of uit te zetten");
       Text uiDKnop = new Text(800, 75,"Druk op 'D' om een eind positie te kiezen aan of uit te zetten");
       Text uiKnop = new Text(800, 100,"klik om een blokade te plaatsen");
       root.getChildren().addAll(uiAKnop,uiDKnop,uiKnop);
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
