package com.phj233;

import java.awt.*;

public class Bullet {
    public MyPlane myPlane;
    public int width=5;
    public int height=11;
    public int x,y;
    //储存子弹图片对象
    public Image[] images=new Image[]{
            Toolkit.getDefaultToolkit().getImage("images/bullet1.png"),
            Toolkit.getDefaultToolkit().getImage("images/bullet2.png")
    };
    public int imageindex=0;
    public Bullet(MyPlane myPlane){
        this.myPlane=myPlane;

    }
    public void drawSelf(Graphics g){
        g.drawImage(this.images[imageindex],x,y,width,height,null);
        if (this.myPlane.timer%50==0){
            imageindex++;
            if (imageindex==this.images.length) this.imageindex=0;
            y-=15;
            //从面板中移除子弹
            if (y<0) this.myPlane.bullets.remove(this);

        }
        //判断子弹是否打中敌机
        for(int i=0; i<this.myPlane.enemys.size(); i++) {
            Enemy e = this.myPlane.enemys.get(i);

            //判断
            if((this.x>=e.x-e.width && this.x<=e.x+e.width) && (this.y>=e.y-e.height && this.y<=e.y+e.height)) {
                //移除该子弹
                this.myPlane.bullets.remove(this);
                //减去敌机的hp值
                e.underAttack();
            }
        }

    }

}
