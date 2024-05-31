package com.phj233.common;

import com.phj233.enemy.Enemy;
import com.phj233.enemy.Enemy1;
import com.phj233.enemy.Enemy2;
import com.phj233.enemy.Enemy3;
import com.phj233.listener.FrameMouseListener;
import com.phj233.listener.keyListener;

import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame{
    public static int state = State.RUNNING.getState();
    public static int frameHeight=700;
    public static int frameWidth=480;

    public static MyPlane myPlane;
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
    public void addEnemyType(Enemy c){
        myPlane.enemyType.add(c);
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
        myPlane=new MyPlane();
        myPlane.setBounds(0,0,frameWidth,frameHeight);
        //将组件添加到窗口中
        this.add(myPlane);
        //设置监听器
        setTouchListener();
        setKeyListener();
        //添加敌机类型
        addEnemyType(new Enemy1(myPlane));
        addEnemyType(new Enemy2(myPlane));
        addEnemyType(new Enemy3(myPlane));
        //设置窗口
        setVisible(true);
        //设置窗口关闭行为
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
