package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Formatter;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class mainForm implements Runnable{
	
	private JFrame frame;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;
    
    public static final int Width = 300;
    public static final int Height = 500;
    
    private PongBall ball = new PongBall(15, 150, 180);
    private Racket racket = new Racket((Width/2)-37, 450, 75, 20);
    
    private double racketSpeed = 9;
    
    private int kicks = 0;
    private int bestScore = 0;
    
    public mainForm(){
        frame = new JFrame("Pong");
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(Width-15, Height-15));
        panel.setLayout(null);
        canvas = new Canvas();
        canvas.setBounds(0, 0, Width, Height);
        canvas.setIgnoreRepaint(true);
        panel.add(canvas);
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                moveIt(evt);
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
    }
    
    public void moveIt(KeyEvent evt) {
     switch (evt.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            	if(gameOver())
            		restart();
            	racket.moveLeft(racketSpeed);
                break;
            case KeyEvent.VK_RIGHT:
            	if(gameOver())
            		restart();
            	racket.moveRight(Width, racketSpeed);
                break;
            default:
            	if(gameOver())
            		restart();
            	break;
        }
    }
    
    private void restart(){
    	if(bestScore <= kicks)
    		this.bestScore = kicks;
    	this.kicks = 0;
    	ball.resetBall();
    }
    
    public void Paint() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Width, Height);
        g.setColor(Color.LIGHT_GRAY);
        Paint(g);
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("Rebatidas: %d Seu Recorde: %d", kicks, bestScore);
        g.drawString(sb.toString(), 20, 20);
        bufferStrategy.show();
        formatter.close();
    }
    
    public void GameOverPaint(){
    	Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.setColor(Color.LIGHT_GRAY);
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("Pressione qualquer botão para tentar novamente");
        g.drawString(sb.toString(), 10, 250);
        bufferStrategy.show();
        formatter.close();
    }
    
    protected void Paint(Graphics2D g) {
        ball.Paint(g);
        racket.Paint(g);
    }
    
    public boolean gameOver(){
    	if(ball.getY() >= 435){
    		return true;
    	} else
    		return false;
    }
    
    public void run() {
        while (true) {
        	if(gameOver()){
        		GameOverPaint();
        		
        	} else{
        		if(ball.collision(racket.getBounds())){
        			ball.Kick();
        			racketSpeed += 0.5;
        			kicks++;
        		}
            
        		ball.Movement();
        		Paint();
        		try {
        			Thread.sleep(15);
        		} catch (InterruptedException e) {}
            }
        }
    }
    
    public static void main(String[] args) {
        mainForm mF = new mainForm();
        new Thread(mF).start();
    }
}

