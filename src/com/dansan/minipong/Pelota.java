package com.dansan.minipong;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Programador
 */
public class Pelota {
    
    Image imagen = new ImageIcon(getClass().getResource("Bola.png")).getImage();
    MiniPong cancha;
    int x=0; 
    int y=0;
    int xa;
    int ya;
    int diametro;
    
    public Pelota(MiniPong cancha){
        this.cancha = cancha;
        xa = cancha.speed;
        ya = cancha.speed;
        diametro = imagen.getWidth(null);
    }
    
    
    
    public void paint(Graphics2D g){        
        g.drawImage(imagen, x, y, cancha);
    }
    
    public void move(){
        boolean cambioDireccion=true;
        if(x + xa < 0)
            cancha.finDelPunto(2);
        else if(x + xa > cancha.getWidth()-diametro)
            cancha.finDelPunto(1);
        else if(y + ya < 0)
            ya = cancha.speed;
        else if(y + ya > cancha.getHeight()-diametro)
            ya = -cancha.speed;
        else if(collision1())
            {
            xa = cancha.speed;
            x = cancha.raqueta1.getTopX();
            cancha.speed++;
        }
        else if(collision2()){
            xa = -cancha.speed;
            x = cancha.raqueta2.getTopX()-diametro;
            cancha.speed++;
        }
        else{
            cambioDireccion = false;
        }
        
        if(cambioDireccion){
            Sound.PELOTA.play();
        }
        
        x = x + xa;
        y = y + ya;
    }

    private boolean collision1(){
        return cancha.raqueta1.getBounds().intersects(getBounds());
    }
    
    private boolean collision2(){
        return cancha.raqueta2.getBounds().intersects(getBounds());
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x,y,diametro,diametro);
    }
    
}
