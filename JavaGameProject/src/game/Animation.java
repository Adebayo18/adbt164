package game;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Animation extends JPanel {
    private BufferedImage img;
    private BufferedImage[] frames; //different positions of the characters
    private int currentFrame;

    private long startTime;
    private long delay;

    public Animation(BufferedImage spriteSheet){
        img = spriteSheet;
    }

    public void setFrames(BufferedImage[] images){
        frames = images;
        if(currentFrame >= frames.length) currentFrame = 0;
    }

    public void setDelay(long d){
        delay = d;

    }

    public void update(){
        if(delay == -1) return; //no animation will be done

        long elapsed = (System.nanoTime()-startTime)/1000000; //represents the time gone by since the last frame
        if(elapsed > delay){
            currentFrame++;
            startTime = System.nanoTime();
            if(currentFrame == frames.length){ //done to ensure we stay within the frame bounds
                currentFrame = 0;
            }
        }

    }

    public BufferedImage getImage(){
        return frames[currentFrame]; //this returns the current image we are on
    }

    public BufferedImage grabImage(int x, int y, int width, int height){
        return img.getSubimage(x,y, width, height);

    }
}
