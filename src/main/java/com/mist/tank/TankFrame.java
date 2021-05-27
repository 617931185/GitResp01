package com.mist.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    int x=200,y=200;
    public TankFrame(){
        this.setSize(800,600);
        this.setResizable(false);
        this.setTitle("tank war");
        this.setVisible(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("key pressed");
            }
            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("key released");
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    //传入一支画笔，绘制窗口，每次需要重新绘制就会被调用
    @Override
    public void paint(Graphics g){
        g.fillRect(x,y,50,50);
        x+=10;
        y+=10;
    }
}
