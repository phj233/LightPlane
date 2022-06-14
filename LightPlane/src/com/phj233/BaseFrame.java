package com.phj233;

import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame{
    public static int state=1;
    public static int frameHeight=700;
    public static int frameWidth=480;
    public MyPlane myPlane;
    //鼠标监听器
    public FrameMouseListener frameMouseListener;
    public keyListener keylistener;
    public void setTouchListener() {
        this.frameMouseListener=new FrameMouseListener();
        this.frameMouseListener.baseFrame = this;
        this.addMouseMotionListener(this.frameMouseListener);
    }
    public void setKeyListener(){
        this.keylistener=new keyListener();
        this.keylistener.baseFrame=this;
        this.addKeyListener(this.keylistener);
    }
    public void addEnemyType(Class c){
        this.myPlane.enemyType.add(c);
    }
    public BaseFrame(){
        super("雷霆战机");
        //获取电脑屏幕分辨率
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //设置窗口大小和位置
        setBounds(((int)screenSize.getWidth()-frameWidth)/2,0,frameWidth,frameHeight);
        //设置布局方式
        setLayout(null);
        //创建mypanel对象
        this.myPlane=new MyPlane();
        this.myPlane.setBounds(0,0,frameWidth,frameHeight);
        //将组件添加到窗口中
        this.add(this.myPlane);
        //设置监听器
        setTouchListener();
        setKeyListener();
        //添加敌机类型
        addEnemyType(Enemy1.class);
        addEnemyType(Enemy2.class);
        addEnemyType(Enemy3.class);
        //设置窗口
        setVisible(true);
        //设置窗口关闭行为
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
