package com.mist.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class TankFrame extends Frame {
    Tank myTank = new Tank(200, 400, Dir.DOWN, this, Group.GOOD,false,null,5);
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    private static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;

    public static int getGameWidth() {
        return GAME_WIDTH;
    }

    public static int getGameHeight() {
        return GAME_HEIGHT;
    }

    //坦克框架构造方法
    public TankFrame() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("tank war");
        this.setVisible(true);
        this.addKeyListener(new MyKeyListener());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //解决闪烁问题
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    //绘制主坦克
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.setColor(c);
        g.drawString("子弹的数量:\t"+bullets.size(),10,50);
        g.drawString("敌方坦克的数量:\t"+(tanks.size()-1),10,70);
        myTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                if(bullets.get(i).getGroup()!=tanks.get(j).getGroup())
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
    }

    //自定义一个按键监听器内部类
    class MyKeyListener extends KeyAdapter {
        boolean bl = false;
        boolean bu = false;
        boolean br = false;
        boolean bd = false;

        //重写按键方法
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bl = true;
                    break;
                case KeyEvent.VK_UP:
                    bu = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = true;
                    break;
                default:
                    break;
            }
            setTankDir(myTank);
        }

        //重写抬键方法
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bl = false;
                    break;
                case KeyEvent.VK_UP:
                    bu = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = false;
                    break;
                case KeyEvent.VK_ALT:
                    myTank.fire(Group.GOOD);
                    break;
                default:
                    break;
            }
            setTankDir(myTank);
        }

        //自定义设置Tank朝向的方法，若所有朝向的boolean值都是false则setMoving=false，Tank将不会移动
        private void setTankDir(Tank tank) {
            if (!bl && !br && !bu && !bd) myTank.setMoving(false);
            else {
                if (bl) tank.setDir(Dir.LEFT);
                if (bu) tank.setDir(Dir.UP);
                if (br) tank.setDir(Dir.RIGHT);
                if (bd) tank.setDir(Dir.DOWN);
                myTank.setMoving(true);
            }
        }
    }
}







