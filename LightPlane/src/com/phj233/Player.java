package com.phj233;
import java.awt.*;

public class Player {
    public MyPlane myPlane;
    public  int width=102;
    public  int height =126;
    public int x,y;
    public int attackMode=0;
    public Image[] images=new Image[]{
            Toolkit.getDefaultToolkit().getImage("images/player1.png"),
            Toolkit.getDefaultToolkit().getImage("images/player2.png")

    };
    public int imageindex=0;
    public Player(MyPlane myPlane){
        this.myPlane=myPlane;
        this.x=(BaseFrame.frameWidth-this.width)/2;
        this.y=BaseFrame.frameHeight-this.height *2;
    }
    public void drawSelf(Graphics g){
        g.drawImage(this.images[imageindex],x,y,width, height,null );
        if (this.myPlane.timer%50==0){
            imageindex++;
            if (this.imageindex==this.images.length) this.imageindex=0;
        }
        for(int i=0;i<this.myPlane.items.size();i++) {
            Item item=this.myPlane.items.get(i);
            if((this.x>=item.x-this.width && this.x<=item.x+item.width) && (this.y>=item.y-this.height && this.y<=item.y+item.height) ) {
                item.eated();
            }
        }
    }

}


