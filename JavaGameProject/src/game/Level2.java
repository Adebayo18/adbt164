package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Level2 extends GameLevel {
    CowboyCharacter cowboyCharacter;
    private Image background;

    private static final BodyImage platformTxt = new BodyImage("data/platformTxt.png");
    private static final BodyImage groundTxt = new BodyImage("data/groundTxt.png", 2);


    private ZombieCharacter2 zombieCharacter2;

    private ArrayList<Vec2> platformPosition;

    public Level2(Game game) {


        super(game, 15);

        zombieCharacter2 = new ZombieCharacter2(this);
        zombieCharacter2.addCollisionListener(encounter);

        cowboyCharacter = getCowboy();
        cowboyCharacter.setPosition(new Vec2(4, -10));

        //make background sound
        bgmusic = new Sound("data/cb.wav");
        bgmusic.play();

        //make ground
        Shape shape = new BoxShape(14, 1);
        StaticBody ground = new StaticBody(this, shape); //referring to an object of this class
        ground.setPosition(new Vec2(0, -15.5f));
        ground.addImage(groundTxt);


        // make a platform
        Shape platform1Shape = new BoxShape(4, 0.5f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(-9, 5.5f));
        platform1.addImage(platformTxt);


        // add another platform here
        StaticBody platform2 = new StaticBody(this, platform1Shape);
        platform2.setPosition(new Vec2(5, -1.5f));
        platform2.addImage(platformTxt);

        // add another platform here
        StaticBody platform3 = new StaticBody(this, platform1Shape);
        platform3.setPosition(new Vec2(9, 7.5f));
        platform3.addImage(platformTxt);

        // add another platform here
        StaticBody platform4 = new StaticBody(this, platform1Shape);
        platform4.setPosition(new Vec2(-7, -6f));
        platform4.addImage(platformTxt);

        // add another platform here
        StaticBody platform5 = new StaticBody(this, platform1Shape);
        platform5.setPosition(new Vec2(12, 14.5f));
        platform5.addImage(platformTxt);

        // add another platform here
        StaticBody platform6 = new StaticBody(this, platform1Shape);
        platform6.setPosition(new Vec2(-5, 20.5f));
        platform6.addImage(platformTxt);

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
        createEnemies(2);

        ZombieCharacter2 zombieCharacter2 = getZombieCharacter2();
        zombieCharacter2.setPosition(new Vec2(3, -5));

    }

    private ZombieCharacter2 getZombieCharacter2() {
        return zombieCharacter2;
    }

    public void createEnemies(int n) {
        for (int j = 0; j < n; j++) {
            ZombieCharacter2 zombie2 = new ZombieCharacter2(this);
            zombie2.setPosition(new Vec2(6.6f, 8));
            //zombie.setAlwaysOutline(true);
            zombie2.addCollisionListener(encounter);
            // zombie.addCollisionListener(encounter);

        }
    }


    @Override
    public boolean isComplete() {
        if (cowboyCharacter.getRedBallCount() == 5) {
            cowboyCharacter.setRedBallCount(0);
            bgmusic.stop();

            return true;
        }
        else
            return false;
    }

    @Override
    public String getLevelName() {
        return "Level2";
    }
}
