package com.phj233.common;

import com.phj233.bullet.Bullet;
import com.phj233.enemy.Enemy;
import com.phj233.item.Item;
import com.phj233.listener.keyListener;
import com.phj233.player.Player;

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
    public ArrayList<Bullet> bullets = new ArrayList<>();
    //存放所有的敌机
    public ArrayList<Enemy> enemys = new ArrayList<>();
    //存放所有的敌机类型
    public ArrayList<Enemy> enemyType = new ArrayList<>();

    private JButton againButton = new JButton();

    private JButton pauseButton = new JButton();

    public JButton gameoverButton = new JButton();
    public void drawText(Graphics graphics,String str,Color color,int size,int x,int y){
        graphics.setColor(color);
        graphics.setFont(new Font("仿宋",Font.BOLD,size));
        graphics.drawString(str, x, y);
    }

    public void drawPauseMenu(Graphics g) {
        genMenu(g);
    }

    public MyPlane(){
        this.bgImg= Toolkit.getDefaultToolkit().getImage("images/bg.png");
        this.player=new Player(this );
        this.drawableThread=new DrawableThread(this);
        this.drawableThread.start();
    }
    public ArrayList<Item> items = new ArrayList<>();
    //绘制
    public void paintComponent(Graphics g){
        if (BaseFrame.state==State.RUNNING.getState()) {
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
        //暂停按钮
        g.drawImage(Toolkit.getDefaultToolkit().getImage("images/pause_nor.png"),BaseFrame.frameWidth-50,10,40,40,null);
        //创建子弹
        if (timer % 100 == 0) {
            // 根据玩家得分设置攻击模式
            if (sc >= 0 && sc <= 50) {
                this.player.attackMode = 1;
            } else if (sc > 50 && sc <= 150) {
                this.player.attackMode = 2;
            } else if (sc > 150 && sc <= 250) {
                this.player.attackMode = 3;
            }

            // 根据攻击模式生成子弹
            switch (this.player.attackMode) {
                case 1:
                    createBullet(1, 0);
                    break;
                case 2:
                    createBullet(2, 20);
                    break;
                case 3:
                    createBullet(3, 20);
                    break;
                }
            }
        //画全部子弹
        for (int i=0;i<bullets.size();i++){
            this.bullets.get(i).drawSelf(g);
        }
        //画敌机
        if(timer%400==0){
            if (!this.enemyType.isEmpty()){
                int index = (int)(Math.random()*this.enemyType.size());
                Enemy enemy;
                try {
                    enemy = (Enemy)(this.enemyType.get(index).getClass().getConstructors()[0].newInstance(this));
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                //创建好的敌机放进数组
                this.enemys.add(enemy);
            }
        }
        //将所有的敌机画出来
        for(int i=0;i<this.enemys.size();i++) {
            this.enemys.get(i).drawSelf(g);
            //检查player是否被敌机击中
            Enemy enemy = enemys.get(i);
            if (player.x >= enemy.x - player.width && player.x <= enemy.x + enemy.width && player.y >= enemy.y - player.height && player.y <= enemy.y + enemy.height) {
                player.reduceHp();
                enemy.killed();
            }
        }
        //将所有的奖品画出来
        for(int i=0;i<this.items.size();i++) {
            this.items.get(i).drawSelf(g);
        }
        } else if (BaseFrame.state==State.PAUSE.getState()) {
            drawPauseMenu(g);
        }
    }

    private void createBullet(int bulletCount, int offset) {
        for (int i = 0; i < bulletCount; i++) {
            Bullet bullet = new Bullet(this);
            bullet.x = this.player.x + this.player.width / 2 - bullet.width / 2 - offset + i * offset;
            bullet.y = this.player.y;
            this.bullets.add(bullet);
        }
    }

    public void gameOver() {
        BaseFrame.state = State.PAUSE.getState();
        drawGameoverMenu(this.getGraphics());

    }

    //绘画死亡菜单
    public void drawGameoverMenu(Graphics g) {
        genMenu(g);
    }

    private void genMenu(Graphics g) {
        repaint();
        if (BaseFrame.state == State.PAUSE.getState()) {
            // 绘制透明背景的死亡菜单
            g.setColor(new Color(0, 0, 0, 0));
            againButton.setBounds(BaseFrame.frameWidth / 2 - 50, BaseFrame.frameHeight / 2 - 100, 100, 50);
            pauseButton.setBounds(BaseFrame.frameWidth / 2 - 50, BaseFrame.frameHeight / 2 , 100, 50);
            gameoverButton.setBounds(BaseFrame.frameWidth / 2 - 50, BaseFrame.frameHeight / 2 + 100, 100, 50);
            againButton.setVisible(true);
            pauseButton.setVisible(true);
            gameoverButton.setVisible(true);
            againButton.setBorderPainted(false);
            pauseButton.setBorderPainted(false);
            gameoverButton.setBorderPainted(false);
            againButton.setIcon(new ImageIcon("images/again.png"));
            pauseButton.setIcon(new ImageIcon("images/pause_nor.png"));
            gameoverButton.setIcon(new ImageIcon("images/gameover.png"));
            againButton.setContentAreaFilled(false);
            pauseButton.setContentAreaFilled(false);
            gameoverButton.setContentAreaFilled(false);
            againButton.addActionListener(e -> {
                // 重新开始游戏
                BaseFrame.state = State.RUNNING.getState();
                sc = 0;
                this.enemys.clear();
                this.bullets.clear();
                this.items.clear();
                player = new Player(this);
                addKeyListener(new keyListener());
                // 关闭死亡菜单
                remove(againButton);
                remove(pauseButton);
                remove(gameoverButton);
                repaint();
                requestFocusInWindow();

            });
            pauseButton.addActionListener(e -> {
                // 继续游戏
                BaseFrame.state = State.RUNNING.getState();
                // 关闭死亡菜单
                remove(againButton);
                remove(pauseButton);
                remove(gameoverButton);
                repaint();
                requestFocusInWindow();
            });
            gameoverButton.addActionListener(e -> {
                // 结束游戏
                System.exit(0);
            });
            add(againButton);
            add(pauseButton);
            add(gameoverButton);
        } else {
            remove(againButton);
            remove(pauseButton);
            remove(gameoverButton);
        }
    }
}
