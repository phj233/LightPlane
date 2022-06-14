package com.phj233;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class MyPlane extends JPanel {
    public Image bgImg;
    public int timer=0;
    public int top=0;
    public static int sc=0;
    public DrawableThread drawableThread;
    public Player player;
    //存放所有子弹
    public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    //存放所有的敌机
    public ArrayList<Enemy> enemys = new ArrayList<Enemy>();
    //存放所有的敌机类型
    public ArrayList<Class> enemyType = new ArrayList<Class>();
    public static void drawText(Graphics graphics,String str,Color color,int size,int x,int y){
        graphics.setColor(color);
        graphics.setFont(new Font("仿宋",Font.BOLD,size));
        graphics.drawString(str, x, y);
    }

    public MyPlane(){
        this.bgImg= Toolkit.getDefaultToolkit().getImage("images/bg.png");
        this.player=new Player(this );
        this.drawableThread=new DrawableThread(this);
        this.drawableThread.start();
    }
    public ArrayList<Item> items = new ArrayList<Item>();
    //绘制
    public void paintComponent(Graphics g){
        if (BaseFrame.state==1) {
            super.paintComponent(g);

        //绘制背景图
        g.drawImage(this.bgImg,0,top-this.bgImg.getHeight(this),this.bgImg.getWidth(this),this.bgImg.getHeight(this),null);
        g.drawImage(this.bgImg,0,top,this.bgImg.getWidth(this),this.bgImg.getHeight(this),null);
        timer++;
        if (timer==10000) timer=0;
        //实现图片向下滚动
        if (timer%10==0){
            top++;
            if (top>this.bgImg.getHeight(this)) top=0;
        }
        //绘制飞机
        this.player.drawSelf(g);
        //计分板
        drawText(g, String.valueOf(sc),Color.black,40,10,50);
        //创建子弹
        if (timer%100==0){
            if (sc>=0 && sc<=50) this.player.attackMode=1;
            if (sc>50 && sc <=150) this.player.attackMode=2;
            if (sc>150 && sc <=250) this.player.attackMode=3;
            //根据玩家火力等级
            if (this.player.attackMode==1){
                Bullet bullet =new Bullet(this);
                bullet.x=this.player.x+this.player.width/2-bullet.width/2;
                bullet.y=this.player.y;
                this.bullets.add(bullet);
            } else if (this.player.attackMode==2) {
                Bullet bullet1=new Bullet(this);
                bullet1.x=this.player.x+this.player.width/2-bullet1.width+10;
                bullet1.y=this.player.y-15;
                this.bullets.add(bullet1);

                Bullet bullet2=new Bullet(this);
                bullet2.x=this.player.x+this.player.width/2-bullet2.width;
                bullet2.y=this.player.y-15;
                this.bullets.add(bullet2);

                Bullet bullet3=new Bullet(this);
                bullet3.x=this.player.x+this.player.width/2-bullet3.width-10;
                bullet3.y=this.player.y-15;
                this.bullets.add(bullet3);
            }else if (this.player.attackMode==3) {
                Bullet bullet1=new Bullet(this);
                bullet1.x=this.player.x+this.player.width/2-bullet1.width;
                bullet1.y=this.player.y-25;
                this.bullets.add(bullet1);

                Bullet bullet2=new Bullet(this);
                bullet2.x=this.player.x+this.player.width/2-bullet2.width-15;
                bullet2.y=this.player.y-15;
                this.bullets.add(bullet2);

                Bullet bullet3=new Bullet(this);
                bullet3.x=this.player.x+this.player.width/2-bullet3.width+15;
                bullet3.y=this.player.y-15;
                this.bullets.add(bullet3);

                Bullet bullet4=new Bullet(this);
                bullet4.x=this.player.x+this.player.width/2-bullet4.width-25;
                bullet4.y=this.player.y-5;
                this.bullets.add(bullet4);

                Bullet bullet5=new Bullet(this);
                bullet5.x=this.player.x+this.player.width/2-bullet5.width+25;
                bullet5.y=this.player.y-5;
                this.bullets.add(bullet5);
            }
        }
        //画全部子弹
        for (int i=0;i<bullets.size();i++){
            this.bullets.get(i).drawSelf(g);
        }
        //画敌机
        if(timer%400==0){
            if (this.enemyType.size()>0){
                int index = (int)(Math.random()*this.enemyType.size());
                Enemy enemy = null;
                try {
                    enemy = (Enemy)(this.enemyType.get(index).getConstructors()[0].newInstance(new Object[] {this}));
                } catch (InstantiationException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                //创建好的敌机放进数组
                this.enemys.add(enemy);
            }
        }
        //将所有的敌机画出来
        for(int i=0;i<this.enemys.size();i++) {
            this.enemys.get(i).drawSelf(g);
        }
        //将所有的奖品画出来
        for(int i=0;i<this.items.size();i++) {
            this.items.get(i).drawSelf(g);
        }
        }
    }
}
