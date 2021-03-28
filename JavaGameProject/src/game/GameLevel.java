package game;

import city.cs.engine.Body;
import city.cs.engine.Walker;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author      Abdulazeez, Jempeji, Abdulazeez.Jempeji@city.ac.uk
 * @version     Version 1.1
 * @since       Version 1.0
 */


public abstract class GameLevel extends World {


    /**
     * Represents CowboyCharacter
     */

    public CowboyCharacter cowboy;


    /**
     * Represents zombieCharacters
     */

    private ArrayList<ZombieCharacter> zombieCharacters;

    /**
     * Represents redBalls
     */

    private ArrayList<Body> redBalls;

    /**
     * Represents encounter
     */


    CollisionEngine encounter;

    /**
     * Represents the platformPosition
     */

    private ArrayList<Vec2> platformPosition;

    /**
     * Represents the bgmusic (Background Music)
     */
    Sound bgmusic;



    /**
     *  To create the game objects
     * @param  game represents the references of the game object
     * @param  num represents the number of HealthBall
     * @return no value.
     */

    public GameLevel(Game game, int num){

                zombieCharacters = new ArrayList<>();
                cowboy = new CowboyCharacter(this);
                redBalls = new ArrayList<>();
                encounter = new CollisionEngine(this, game);
                cowboy.addCollisionListener(encounter);
                createEnemies();
                createHealthBall(num);
    }

    /**
     *  To create the enemies
     * @return no value.
     */

    public void createEnemies(){
        for (int j = 0; j < 3; j++) {
            ZombieCharacter zombie = new ZombieCharacter(this);
            zombie.setPosition(new Vec2(1, 8));
            //zombie.setAlwaysOutline(true);
            zombie.addCollisionListener(encounter);
        }
    }

    /**
     *  To create createHealthBall
     * @param  num represents the number of Health Ball objects.
     * @return no value.
     */

    public void createHealthBall(int num){
        for (int i = 0; i < num; i++){
            //redBalls increase the health count of a character
            Body redBall = new RedBallCharacter(this);
            redBall.setPosition(new Vec2(i*2-10,25));
            //redBall.setAlwaysOutline(true);
            //now each ball has a collision listener thus whenever the ball collides with another object collision occurs
            redBall.addCollisionListener(encounter);
        }
    }

    /**
     *  Returns a CowboyCharacter
     * @return cowboy
     */

    public CowboyCharacter getCowboy(){
        return cowboy;
    }

    /**
     *  Returns a ZombieCharacter
     * @return zombieCharacters
     */

    public ArrayList<ZombieCharacter> getZombieCharacters() {
        return zombieCharacters;
    }

    /**
     *  Returns a RedBall
     * @return redBalls
     */

    public ArrayList<Body> getRedBalls() {
        return redBalls;
    }

    /**
     *  Pause game
     * @return pause.
     */
    public abstract boolean isComplete();


    /**
     *  Returns if the game is complete
     * @return  isComplete.
     */

    public abstract String getLevelName();

}
