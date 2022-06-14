package com.phj233;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.phj233.BaseFrame.state;

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
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
