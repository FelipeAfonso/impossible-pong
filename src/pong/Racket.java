package pong;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Racket {
    private int Width;
    private int Height;
    private int positionX;
    private int positionY;
    
    public Racket(int x, int y, int width, int height){
        this.Height = height;
        this.Width = width;
        this.positionX = x;
        this.positionY = y;
    }
    
    public void Paint(Graphics2D g){
        g.fillRect(positionX, positionY, Width, Height);
    }
    
    public void moveLeft(double amount){
    	if(this.positionX >= 0)
    		this.positionX += (int) -amount;
    }
    
    public void moveRight(int frameWidth, double amount){
        if(this.positionX <= frameWidth - this.Width - 8)
    		this.positionX += (int) amount;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(positionX, positionY, Width, Height);
    }
}
