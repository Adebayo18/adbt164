package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Bomb implements ActionListener {

    private DynamicBody bomb;
    private DynamicBody explosion;

    // Adding sounds to the explosion
    private static SoundClip bombSound;
    private static SoundClip explosionSound;

    static {
        try {
            bombSound = new SoundClip("data/bomb.wav");
            explosionSound = new SoundClip("data/exp.wav");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private int timerCount;

    public Bomb (World world, Vec2 pos, int time) {

        bomb = new DynamicBody(world, new CircleShape(1f));
        bomb.addImage(new BodyImage("data/rock.png"));
        bomb.setPosition(pos);
        //bomb.setAlwaysOutline(true);

        // Creating timer for the bomb to explode
        Timer t = new Timer(time, this);
        t.start();
        t.setRepeats(false);

        timerCount = 0;
        // plays the bomb sound
        bombSound.play();

    }
        // Creating bomb explosion
    @Override
    public void actionPerformed(ActionEvent e) {

        timerCount++;

        //first code
        if (timerCount == 1) {
            bomb.destroy();
            // stops the bomb sound
            bombSound.stop();
            // starts the explosion sound
            explosionSound.play();

            explosion = new DynamicBody(bomb.getWorld(), new CircleShape(3f));
            explosion.addImage(new BodyImage("data/explosion.png", 7));
            explosion.setPosition(bomb.getPosition());
            //explosion.setAlwaysOutline(true);


            // Creating timer for the explosion to be removed from the world
            Timer t = new Timer(500, this);
            t.setRepeats(false);
            t.start();


            // Creating anonymous collision listener class for the explosion to destroy things
            explosion.addCollisionListener(new CollisionListener() {
                @Override
                public void collide(CollisionEvent collisionEvent) {
                    if (collisionEvent.getOtherBody() instanceof ZombieCharacter)
                        collisionEvent.getOtherBody().destroy(); //destroys the zombie characters

                    if (collisionEvent.getOtherBody() instanceof ZombieCharacter2)
                        collisionEvent.getOtherBody().destroy(); //destroys the zombie characters

                    if (collisionEvent.getOtherBody() instanceof RedBallCharacter)
                        collisionEvent.getOtherBody().destroy(); //destroys the red ball health
                }
            });
        }

        //second code
        if (timerCount == 2) {
            explosion.destroy();
            // stops the explosion sound
            //explosionSound.stop();
        }
    }
}
