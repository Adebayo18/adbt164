package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Level1 extends GameLevel {
    private Image background;
    CowboyCharacter cowboyCharacter;


    private static final BodyImage platformTexture = new BodyImage("data/platform.jpeg");
    private static final BodyImage groundTexture = new BodyImage("data/ground.jpeg", 2);

    private ArrayList<Vec2> platformPosition;

    public Level1(Game game) {
        super(game,8);
        bgmusic = new Sound("data/eskay.wav");


        bgmusic.play();
        cowboyCharacter = getCowboy();
        cowboyCharacter.setPosition(new Vec2(4,-10));

        //make ground
        Shape shape = new BoxShape(14, 1);
        StaticBody ground = new StaticBody(this, shape); //referring to an object of this class
        ground.setPosition(new Vec2(0, -15.5f));
        ground.addImage(groundTexture);
        // ground.setFillColor(Color.lightGray);


        // make a platform
        Shape platform1Shape = new BoxShape(4, 0.5f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(-9, 5.5f));
        platform1.addImage(platformTexture);
        //platform1.setFillColor(Color.lightGray);


        // add another platform here
        StaticBody platform2 = new StaticBody(this, platform1Shape);
        platform2.setPosition(new Vec2(5,-1.5f));
        platform2.addImage(platformTexture);
        //platform2.setFillColor(Color.lightGray);

        // add another platform here
        StaticBody platform3 = new StaticBody(this, platform1Shape);
        platform3.setPosition(new Vec2(9,7.5f));
        platform3.addImage(platformTexture);
        //platform3.setFillColor(Color.lightGray);

        // add another platform here
        StaticBody platform4 = new StaticBody(this, platform1Shape);
        platform4.setPosition(new Vec2(-7,-6f));
        platform4.addImage(platformTexture);
        //platform4.setFillColor(Color.lightGray);


        // make a right wall
        Shape wall1Shape = new BoxShape(0.5f, 20f);
        StaticBody wall1 = new StaticBody(this, wall1Shape);
        wall1.setPosition(new Vec2(13, 0f));

        // make a left wall
        Shape wall2Shape = new BoxShape(0.5f, 20f);
        StaticBody wall2 = new StaticBody(this, wall2Shape);
        wall2.setPosition(new Vec2(-13, 0f));

        platformPosition = new ArrayList<>();
        platformPosition.add(platform1.getPosition());
        platformPosition.add(platform2.getPosition());
        setEnemiesPositions();

    }

    public void setEnemiesPositions(){
        ArrayList<ZombieCharacter> zombieCharacters = getZombieCharacters();
        for (int i= 0; i < zombieCharacters.size(); i++) {
            ZombieCharacter zombieCharacter = zombieCharacters.get(i);
            zombieCharacter.setPosition(new Vec2(platformPosition.get(i).x + 2.0f, platformPosition.get(i).y + 0.5f));
            //zombie.setAlwaysOutline(true);

        }

    }

    protected void paintBackground(Graphics2D g){
        g.drawImage(background,0,0, (ImageObserver) this);
    }


    @Override
    public boolean isComplete() {
        if (cowboyCharacter.getRedBallCount() == 6) {
            cowboyCharacter.setRedBallCount(0);
            bgmusic.stop();
            return true;
        }
        else
            return false;
    }

    @Override
    public String getLevelName() {
        return "Level1";
    }

}
