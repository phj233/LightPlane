package com.phj233;

import java.awt.*;

public class Item {
    //使用MyPanel的原因是，要调用MyPanel中的timer
    public MyPlane myPlane;
    public int width,height,x,y;

    public Image[] images;

    //奖品的下移速度（与敌机速度一致）
    public int speed;

    //奖品的自身旋转速度（图片切换时间）
    public int imageSpeed;

    public Item(MyPlane myPlane) {
        this.myPlane = myPlane;
    }
    //存放当前图片的下标
    public int imageIndex = 0;

    //画奖品
    public void drawSelf(Graphics g) {
        g.drawImage(this.images[imageIndex],x,y,width,height,null);

        //每隔imageSpeed毫秒切换一张图片
        if(this.myPlane.timer%imageSpeed==0) {
            imageIndex++;
            //图片遍历到最后一个后，图片下标再次重0开始
            if(this.imageIndex==this.images.length) {
                this.imageIndex = 0;
            }
        }
        //每隔speed毫秒奖品下移一个单位
        if(this.myPlane.timer%speed==0) {
            //奖品下移
            y++;
            //奖品移动超出窗口，则销毁
            if(this.y>=BaseFrame.frameHeight) {
                this.myPlane.items.remove(this);
            }
        }

    }
    //奖品被吃掉
    public void eated() {
        this.myPlane.items.remove(this);
    }
}

