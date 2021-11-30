package com.dansan.minipong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Programador
 */
public class MiniPong extends JPanel{    
    static final int ancho = 800;
    static final int alto = 600;
    int speed=1;
    Pelota pelota = new Pelota(this);
    Raqueta1 raqueta1 = new Raqueta1(0, 0, this);
    Raqueta2 raqueta2 = new Raqueta2(ancho-35, alto-200, this);
    
    public MiniPong(){
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                raqueta1.keyPressed(e);
                raqueta2.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                raqueta1.keyReleased(e);
                raqueta2.keyReleased(e);
            }
        });
        setFocusable(true);
    }
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        //Linea del medio
        g2d.fillRect((ancho/2)-5, 0, 10, alto);        
        pelota.paint(g2d);
        raqueta1.paint(g2d);
        raqueta2.paint(g2d);
        
        g2d.setColor(Color.red);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf(raqueta1.puntos), 20, 20);
        g2d.drawString(String.valueOf(raqueta2.puntos), 750, 20);
    }
    
    public void move(){
        pelota.move();
        raqueta1.move();
        raqueta2.move();
    }
    
    public void finDelPunto(int i){
        //Accion de contar punto a quien corresponda
        speed=1;
        raqueta1.ya=0;
        raqueta2.ya=0;
        if(i==1){
            raqueta1.puntos++;
            pelota.x = 40;
            pelota.y = 20;
            raqueta1.x = 0;
            raqueta1.y = 0;
            
            pelota.xa=speed;
            pelota.ya=speed;
            JOptionPane.showMessageDialog(this, "Fin del Punto","Fin del Punto",JOptionPane.INFORMATION_MESSAGE);
        }            
        else if(i==2){
            raqueta2.puntos++;
            pelota.x = ancho-35-pelota.diametro;
            pelota.y = alto-200-pelota.diametro;
            raqueta2.x = ancho-35;
            raqueta2.y = alto-200;
            
            pelota.xa=-speed;
            pelota.ya=-speed;
            JOptionPane.showMessageDialog(this, "Fin del Punto","Fin del Punto",JOptionPane.INFORMATION_MESSAGE);
        }                
        if(raqueta1.puntos==5 || raqueta2.puntos==5){
            JOptionPane.showMessageDialog(this, "Score: "+String.valueOf(raqueta1.puntos)+"-"+String.valueOf(raqueta2.puntos),"Fin del Juego",JOptionPane.INFORMATION_MESSAGE);            
            System.exit(ABORT);
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Mini Pong");
        frame.setSize(ancho, alto);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MiniPong cancha = new MiniPong();
        frame.add(cancha);        
        frame.setVisible(true);        
        boolean seguir=true;   
        while(seguir){
            cancha.move();
            cancha.repaint();
            Thread.sleep(10);
        }
    }
    
}
