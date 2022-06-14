package com.phj233;

import java.awt.*;

public class Item01 extends Item{
    public Item01(MyPlane myPlane) {
        super(myPlane)    ;
        // TODO Auto-generated constructor stub
        this.width=30;
        this.height=30;
        this.images=new Image[] {
                Toolkit.getDefaultToolkit().getImage("images/star01.png"),
                Toolkit.getDefaultToolkit().getImage("images/star02.png"),
                Toolkit.getDefaultToolkit().getImage("images/star03.png"),
                Toolkit.getDefaultToolkit().getImage("images/star04.png"),
                Toolkit.getDefaultToolkit().getImage("images/star05.png"),
                Toolkit.getDefaultToolkit().getImage("images/star06.png"),
                Toolkit.getDefaultToolkit().getImage("images/star07.png"),
                Toolkit.getDefaultToolkit().getImage("images/star08.png"),
                Toolkit.getDefaultToolkit().getImage("images/star09.png"),
        };
        this.imageSpeed=(int)(Math.random()*20+5);
        this.speed=3;
    }

}
