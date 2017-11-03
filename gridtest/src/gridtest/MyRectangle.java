/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gridtest;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author sibev
 */
public class MyRectangle extends Rectangle {
    
    public int x;
    public int y;
    public int dimensions;
    public boolean passable =false;
    public boolean isStart = false;
    public boolean isEnd = false;
    
    MyRectangle(int x, int y, int dimensions,int passable)
    {
        this.x = x;
        this.y = y;
        this.dimensions = dimensions;
        if(passable == 1)
        {
            this.passable = true;
        }        
    }
    
    public Rectangle getRectangle()
    {
        Rectangle r = new MyRectangle(x,y,dimensions,dimensions);
        if(passable)
        {
            r.setFill(Color.TRANSPARENT);
            r.setStroke(Color.BLACK);
            r.setStrokeWidth(2);
            //System.out.println("passable Myrectangle");
            return r;
        }
        else
        {
            r.setFill(Color.BLACK);
            //System.out.println("non passable MyRectangle");
            return r;
        }
        
    }
}
