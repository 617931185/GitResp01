package com.mist.tank;
import sun.jvm.hotspot.ui.DeadlockDetectionPanel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class TankFrame extends Frame {
    Tank myTank = new Tank(200,200,Dir.DOWN);
    public TankFrame(){
        this.setSize(800,600);
        this.setResizable(false);
        this.setTitle("tank war");
        this.setVisible(true);
        this.addKeyListener(new KeyAdapter() {
            boolean bl=false;
            boolean bu=false;
            boolean br=false;
            boolean bd=false;
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key){
                    case KeyEvent.VK_LEFT:
                        bl=true;
                        break;
                    case KeyEvent.VK_UP:
                        bu=true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        br=true;
                        break;
                    case KeyEvent.VK_DOWN:
                        bd=true;
                        break;
                    default:
                        break;
                }
                setTankDir(myTank);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key){
                    case KeyEvent.VK_LEFT:
                        bl=false;
                        break;
                    case KeyEvent.VK_UP:
                        bu=false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        br=false;
                        break;
                    case KeyEvent.VK_DOWN:
                        bd=false;
                        break;
                    default:
                        break;
                }
                setTankDir(myTank);
            }
            private void setTankDir(Tank tank) {
                if(bl)tank.setDir(Dir.LEFT);
                if(bu)tank.setDir(Dir.UP);
                if(br)tank.setDir(Dir.RIGHT);
                if(bd)tank.setDir(Dir.DOWN);
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
        myTank.paint(g);

    }

}
