package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.util.ArrayList;

public class Level3 extends GameLevel{
    CowboyCharacter cowboyCharacter;

    private static final BodyImage platformText = new BodyImage("data/platform.jpg");
    private static final BodyImage groundText = new BodyImage("data/ground.jpg", 2);

    private ArrayList<Vec2> platformPosition;

    public Level3(Game game) {
        super(game,10);
        cowboyCharacter = getCowboy();
        cowboyCharacter.setPosition(new Vec2(4,-10));


        //make background song
        bgmusic = new Sound("data/f.wav");
        bgmusic.play();

        //make ground
        Shape shape = new BoxShape(14, 1);
        StaticBody ground = new StaticBody(this, shape); //referring to an object of this class
        ground.setPosition(new Vec2(0, -15.5f));
        ground.addImage(groundText);


        // make a platform
        Shape platform1Shape = new BoxShape(4, 0.5f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(-9, 5.5f));
        platform1.addImage(platformText);


        // add another platform here
        StaticBody platform2 = new StaticBody(this, platform1Shape);
        platform2.setPosition(new Vec2(5,-1.5f));
        platform2.addImage(platformText);

        // add another platform here
        StaticBody platform3 = new StaticBody(this, platform1Shape);
        platform3.setPosition(new Vec2(9,7.5f));
        platform3.addImage(platformText);

        // add another platform here
        StaticBody platform4 = new StaticBody(this, platform1Shape);
        platform4.setPosition(new Vec2(-7,-6f));
        platform4.addImage(platformText);

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

    }



    @Override
    public boolean isComplete() {
        if (cowboyCharacter.getRedBallCount() == 7) {
            bgmusic.stop();
            return true;
        }
        else
            return false;
    }
    @Override
    public String getLevelName() {
        return "Level3";
    }

}
