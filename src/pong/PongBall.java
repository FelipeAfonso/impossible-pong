package pong;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PongBall {
    private final int Radius;
    private int positionX;
    private int positionY;
    private int speedX = 1;
    private int speedY = 1;
    
    public PongBall(int r, int x, int y){
        this.Radius = r;
        this.positionX = x;
        this.positionY = y;
    }
    
    public void Paint(Graphics2D g){
        g.fillOval(positionX, positionY, Radius*2, Radius*2);
    }
    
    public Rectangle getBounds(){
        return new Rectangle(positionX, positionY, Radius*2, Radius*2);
    }
    
    public void Movement(){
        if(positionX >= mainForm.Width - 32 && speedX > 0)
            speedX = -speedX;
        if(positionX <= 0 && speedX < 0)
            speedX = -speedX;
        	
        if(positionY >= mainForm.Height - 32 && speedY > 0)
            speedY = -speedY;
        if(positionY <= 0 && speedY < 0)
            speedY = -speedY;
        
        this.positionX += speedX;
        this.positionY += speedY;
    }
    
    public void IncreaseSpeed(){
        if(speedX>=0)
        	speedX++;
        else
        	speedX--;
        if(speedY>=0)
        	speedY++;
        else
        	speedY--;
    }
    
    public void Kick(){
    	this.speedY = -speedY;
        IncreaseSpeed();
    }
    
    public boolean collision(Rectangle rect){
        return rect.getBounds().intersects(this.getBounds());
    }
    
    public int getY(){
    	return this.positionY;
    }
    
    public void resetBall(){
    	this.speedX = 1;
    	this.speedY = 1;
    	this.positionX = 150;
    	this.positionY = 180;
    }
    
}

