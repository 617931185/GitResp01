package com.mist.tank;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        tf.tanks.add(tf.myTank);
        for (int i = 0; i < 5; i++)
                tf.tanks.add(new Tank(20+120*i,150, Dir.DOWN,tf,Group.BAD,true,new Random(i+100),1));
        while(tf.tanks.size()>1){
            Thread.sleep(50);
            tf.repaint();
        }
        System.exit(0);
    }
}

