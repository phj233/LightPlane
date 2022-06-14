package com.phj233;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class FrameMouseListener implements MouseMotionListener {

    public BaseFrame baseFrame;

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.baseFrame.myPlane.player.x = e.getX() - this.baseFrame.myPlane.player.width/2;
        this.baseFrame.myPlane.player.y = e.getY() - this.baseFrame.myPlane.player.height /2;
    }
}
