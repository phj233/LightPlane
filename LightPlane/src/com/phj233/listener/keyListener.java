package com.phj233.listener;

import com.phj233.common.BaseFrame;
import com.phj233.common.State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.phj233.common.BaseFrame.state;

public class keyListener implements KeyListener {
    public BaseFrame baseFrame;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==32){
            switch (state) {
                case 1 -> state = 2;
                case 2 -> state = 1;
                default -> {
                }
            }
        }
        //按下ESC键绘制暂停界面
        if(e.getKeyCode()==27){
            if (state == State.RUNNING.getState()) {
                state = State.PAUSE.getState();
            } else if (state == State.PAUSE.getState()) {
                state = State.RUNNING.getState();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
