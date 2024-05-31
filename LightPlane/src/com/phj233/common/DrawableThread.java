package com.phj233.common;

public class DrawableThread extends Thread{
    public MyPlane myPlane;
    public DrawableThread(MyPlane myPlane){
        this.myPlane=myPlane;
    }
    public void run(){
        while (true){
            this.myPlane.repaint();//重绘
            try {
                this.currentThread().sleep(1);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }
}
