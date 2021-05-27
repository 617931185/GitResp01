package com.mist.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    public TankFrame(){
        this.setSize(800,600);
        this.setResizable(false);
        this.setTitle("tank war");
        this.setVisible(true);
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
        g.fillRect(200,200,50,50);
    }
}
