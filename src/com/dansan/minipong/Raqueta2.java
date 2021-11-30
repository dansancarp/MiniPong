package com.dansan.minipong;

import static com.dansan.minipong.Raqueta1.ancho;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author Programador
 */
public class Raqueta2 {
    static final int ancho = 20;
    static final int alto = 100;
    int x;
    int y;
    int ya;
    int puntos=0;
    
    MiniPong cancha;
    
    public Raqueta2(int x,int y,MiniPong cancha){
        this.x = x;
        this.y = y;
        this.cancha = cancha;
    }
    
    public void paint(Graphics2D g){
        g.fillRect(x, y, ancho, alto);
    }
    
    public void move(){
        if(y + ya > 0 && y + ya < cancha.getHeight()-alto)
            y = y + ya;
    }
    
    public void keyReleased(KeyEvent e){
        ya = 0;
    }
    
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_W)
            ya = -cancha.speed;
        if(e.getKeyCode()==KeyEvent.VK_S)
            ya = cancha.speed;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x,y,ancho,alto);
    }
    
    public int getTopX(){
        return x;
    }
    
}
