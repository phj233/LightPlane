package com.phj233;
import java.awt.*;
public class Enemy {
    //使用MyPanel的原因是，要调用MyPanel中的timer
    public MyPlane myPlane;
    //width，height是敌机宽度与高度
    public int width, height, x, y;
    public Image image;
    public int hp; //敌机的生命值
    public Item[] items; //奖品

    public Enemy(MyPlane myPlane) {
        this.myPlane = myPlane;
    }

    public void drawSelf(Graphics g) {

    }

    //移除死掉或者飞出界面的敌机
    public void killed() {
        if (items != null && items.length > 0) {
            //敌机死后，释放奖品
            for (int i = 0; i < items.length; i++) {
                Item item = items[i];
                item.x = this.x + 25 * i;
                item.y = this.y;

                this.myPlane.items.add(item);
            }
        }

        this.myPlane.enemys.remove(this);
    }

    //敌机处在被攻击状态，其生命值减少
    public void underAttack() {
        if (hp > 0) {
            hp--;
        }
    }
}

