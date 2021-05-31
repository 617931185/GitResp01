package com.mist.tank;

import java.awt.*;
import java.sql.Time;

public class Bullet {
    private static final int SPEED = 5,WIDTH = ResourceMgr.bulletD.getWidth(),HEIGHT = ResourceMgr.bulletD.getHeight();
    private int x,y;
    private Dir dir;
    private boolean living = true;
    private TankFrame tf = null;
    private Group group=Group.BAD;

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public Bullet(int x, int y, Dir dir, TankFrame tf,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group=group;
    }
    public void paint(Graphics g){
        if(!living){
            tf.bullets.remove(this);
        }
        switch (dir){
            case LEFT:g.drawImage(ResourceMgr.bulletL,x,y,null);break;
            case RIGHT:g.drawImage(ResourceMgr.bulletR,x,y,null);break;
            case UP:g.drawImage(ResourceMgr.bulletU,x,y,null);break;
            case DOWN:g.drawImage(ResourceMgr.bulletD,x,y,null);break;
        }
        move();
    }
    private void move(){
        switch (this.dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }
        if(x<0||y<0||x>TankFrame.getGameWidth()||y>TankFrame.getGameHeight())living = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Group getGroup() {
        return group;
    }

    public void collideWith(Tank tank) {
        Rectangle rect1 = new Rectangle(x,y,WIDTH,HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),tank.getWIDTH(),tank.getHEIGHT());
        if(rect1.intersects((rect2))){
            if(tank.getGroup()==Group.GOOD) System.exit(0);//主坦克挂了，游戏结束
            tank.die();
            this.die();
        }
    }

    private void die() {
        living = false;
    }
}
