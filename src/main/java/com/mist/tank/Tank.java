package com.mist.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x,y;
    private Dir dir;
    private int SPEED = 1;
    private int WIDTH = ResourceMgr.tankD.getWidth(),HEIGHT = ResourceMgr.tankD.getHeight();
    private boolean moving = false;
    private TankFrame tf = null;
    private boolean living = true;
    private Group group=Group.BAD;
    private Random random=null;

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public Tank(int x, int y, Dir dir, TankFrame tf,Group group,Boolean moving,Random random,int SPEED) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group=group;
        this.moving=moving;
        this.random=random;
        this.SPEED=SPEED;
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

    public void paint(Graphics g) {
        if(!living) tf.tanks.remove(this);
        switch (dir){
            case LEFT:g.drawImage(ResourceMgr.tankL,x,y,null);break;
            case RIGHT:g.drawImage(ResourceMgr.tankR,x,y,null);break;
            case UP:g.drawImage(ResourceMgr.tankU,x,y,null);break;
            case DOWN:g.drawImage(ResourceMgr.tankD,x,y,null);break;
        }
        move();
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Group getGroup() {
        return this.group;
    }

    private void move(){
        if(!this.moving) return;
        if(this.group==Group.BAD){
            randomDir();
            if(Math.floorMod(this.random.nextInt(),29)<1)this.fire(Group.BAD);
        }
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
    }

    private void randomDir() {
        int r = Math.floorMod(this.random.nextInt(),10);
        if(r==0)this.dir=Dir.LEFT;
        else if(r==1)this.dir=Dir.RIGHT;
        else if(r==2)this.dir=Dir.UP;
        else this.dir=Dir.DOWN;
    }

    public void fire(Group group) {
        tf.bullets.add(new Bullet(x+WIDTH/2-Bullet.getWIDTH()/2,y+HEIGHT/2-Bullet.getHEIGHT()/3,dir,this.tf,group));
    }

    public void die() {
        this.living = false;
    }
}
