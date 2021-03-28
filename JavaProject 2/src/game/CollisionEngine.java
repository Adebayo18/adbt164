package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
 * @author      Abdulazeez, Jempeji, Abdulazeez.Jempeji@city.ac.uk
 * @version     Version 1.1
 * @since       Version 1.0
 */


public class CollisionEngine implements CollisionListener {

    /**
     * Represents the CowboyCharacter.
     */
    private CowboyCharacter cowboy;
    /**
     * Represents the GameLevel
     */
    private GameLevel gameLevel;

    /**
     * Represents the Game Object
     */
    private Game game;



    /**
     *  Creates a collision detector object
     *
     * @param  character represents CowboyCharacter object.
     * @return Collision Detector Object.
     */
    public CollisionEngine(CowboyCharacter character){
        cowboy = character;
    }

    /**
     *  Creates a collision detector object
     * @param  game represents the references of the game object
     * @param  level represents any levels of the game.
     * @return Collision Detector Object.
     */

    public CollisionEngine(GameLevel level, Game game) {
        this.gameLevel = level;
        this.game = game;
    }

    /**
     *  Handles all the collision in the game
     *
     * @param  collisionEvent represents collisionEvent
     * @return no value.
     */

    @Override
    public void collide(CollisionEvent collisionEvent) {
        //other body represents what the ball collided with
        CowboyCharacter c = gameLevel.getCowboy();
        game.view.cowboy = c;
        if(collisionEvent.getReportingBody() instanceof RedBallCharacter && collisionEvent.getOtherBody() ==c){
            System.out.println("Collision has occurred between healthBall and Cowboy");
            c.incrementHealthCount();
            c.incrementRedBallCount();

            // Sound when the cowboy hits the healthBall
            Sound C = new Sound("data/hit.wav");
            C.play();

            System.out.println("red ball count: "+c.getRedBallCount());

            //reporting body represents the object which has had an object collided with it
            collisionEvent.getReportingBody().destroy();//get the ball in question and destroy it
        }else if (collisionEvent.getReportingBody() instanceof ZombieCharacter && collisionEvent.getOtherBody() == c) {
            System.out.println("Collision has occurred between Cowboy and Zombie");
            c.decrementHealthCount();
            collisionEvent.getReportingBody().destroy();

            // Sound when the cowboy hits the Zombie
            Sound D = new Sound("data/zombieSound.wav");
            D.play();

        }else if (collisionEvent.getReportingBody() instanceof ZombieCharacter2 && collisionEvent.getOtherBody() == c){
            System.out.println("Collision has occurred between Cowboy and Zombie");
            c.decrementHealthCount();
            collisionEvent.getReportingBody().destroy();

            // Sound when the cowboy hits the Zombie
            Sound F = new Sound("data/zombieSound.wav");
            F.play();

        }else if(gameLevel.isComplete()){
            System.out.println("next level");
            game.goToNextLevel();
        }

    }
}
