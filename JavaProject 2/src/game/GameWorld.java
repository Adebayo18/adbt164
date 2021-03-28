package game;

/*A game world is a world with some bodies*/

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class GameWorld extends World {
    //todo make into an array for faster computation
    private Image background;
    private CowboyCharacter cowboy;
    private ZombieCharacter zombie;
    private ArrayList<Vec2> platformPosition;

    private static final BodyImage platformTexture = new BodyImage("data/platform.jpeg");
    private static final BodyImage groundTexture = new BodyImage("data/ground.jpeg", 2);

    public GameWorld(){
        super();


        // make the ground
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

        // add another platform here
        StaticBody platform5 = new StaticBody(this, platform1Shape);
        platform5.setPosition(new Vec2(12,14.5f));
        platform5.addImage(platformTexture);

        // add another platform here
        StaticBody platform6 = new StaticBody(this, platform1Shape);
        platform6.setPosition(new Vec2(-5,20.5f));
        platform6.addImage(platformTexture);

        // make a right wall
        Shape wall1Shape = new BoxShape(0.5f, 20f);
        StaticBody wall1 = new StaticBody(this, wall1Shape);
        wall1.setPosition(new Vec2(13, 0f));

        // make a left wall
        Shape wall2Shape = new BoxShape(0.5f, 20f);
        StaticBody wall2 = new StaticBody(this, wall2Shape);
        wall2.setPosition(new Vec2(-13, 0f));


        // make a character
        cowboy = new CowboyCharacter(this);
        cowboy.setPosition(new Vec2(4,-10));
        //cowboy.setAlwaysOutline(true);

        platformPosition = new ArrayList<>();
        platformPosition.add(platform1.getPosition());
        platformPosition.add(platform2.getPosition());
        createEnemies();
        createHealthBall();

    }

    public void createHealthBall(){
        for (int i = 0; i < 15; i++){
            //redBalls increase the health count of a character
            Body redBall = new RedBallCharacter(this);
            redBall.setPosition(new Vec2(i*2-10,25));
            //redBall.setAlwaysOutline(true);
            //now each ball has a collision listener thus whenever the ball collides with another object collision occurs
            redBall.addCollisionListener(new CollisionEngine(cowboy));
        }
    }

    public void createEnemies(){
        for (int j = 0; j < 2; j++) {
            zombie = new ZombieCharacter(this);
            zombie.setPosition(new Vec2(platformPosition.get(j).x + 2.0f, platformPosition.get(j).y + 0.5f));
           //zombie.setAlwaysOutline(true);
            zombie.addCollisionListener(new CollisionEngine(cowboy));
        }
    }

    protected void paintBackground(Graphics2D g){
        g.drawImage(background,0,0, (ImageObserver) this);
    }

    public CowboyCharacter getPlayer(){
        return cowboy;
    }
}
