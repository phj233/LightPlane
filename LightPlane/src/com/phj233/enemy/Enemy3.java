package com.phj233.enemy;

import com.phj233.common.BaseFrame;
import com.phj233.common.MyPlane;

import java.awt.*;

public class Enemy3 extends Enemy{
    public Enemy3(MyPlane myPlane) {
        super(myPlane);
        // TODO Auto-generated constructor stub
        this.width = 169;
        this.height = 258;
        this.hp = 8;

        this.x = (int) (Math.random()*(BaseFrame.frameWidth - this.width));
        this.y = - this.height;

        this.image = Toolkit.getDefaultToolkit().getImage("images/enemy3_n1.png");

    }
    //敌机爆炸效果，放在放到数组中
    public Image[] dieImages = new Image[] {
            Toolkit.getDefaultToolkit().getImage("images/enemy3_down1.png"),
            Toolkit.getDefaultToolkit().getImage("images/enemy3_down2.png"),
            Toolkit.getDefaultToolkit().getImage("images/enemy3_down3.png"),
            Toolkit.getDefaultToolkit().getImage("images/enemy3_down4.png"),
    };
    //存放当前图片的下标
    public int imageIndex = 0;
    //画敌机
    public void drawSelf(Graphics g) {
        //判断敌机是否被击中
        if(hp>0) {
            g.drawImage(this.image,x,y,width,height,null);
        }
        else {
            //被打中
            g.drawImage(this.dieImages[imageIndex],x,y,width,height,null);
            //每隔10毫秒切换一张图片
            if(this.myPlane.timer%50==0) {
                imageIndex++;
                if(imageIndex>=this.dieImages.length) {
                    //敌机死了
                    MyPlane.sc+=3;
                    killed();
                }

            }
        }
        //敌机向下移动,如果敌机出界就销毁
        if(this.myPlane.timer%35==0) {
            y=y+10;
            if(y>=BaseFrame.frameHeight) {
                killed();
            }
        }
    }

}
