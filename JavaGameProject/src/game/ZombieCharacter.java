package game;

import city.cs.engine.*;

/**
 * @author      Abdulazeez, Jempeji, Abdulazeez.Jempeji@city.ac.uk
 * @version     Version 1.1
 * @since       Version 1.0
 */


public class ZombieCharacter extends Walker {

    private int healthCount;

    //extends walker because the walker class represents a dynamic body which stands upright
    //the following code provides us with a Zombie character image and the appropriate shape that matches the image

    /**
     * Represents the shape of the ZombieCharacter.
     */

    private static final Shape shape = new PolygonShape(-0.55f,1.97f, -1.4f,0.25f, -1.41f,-2.38f, 0.13f,-2.33f, 1.21f,0.22f, 1.01f,1.62f);

    /**
     * Represents the image of the ZombieCharacter.
     */
    private static final BodyImage image = new BodyImage("data/zombie.png",5.0f);


    /**
     *  Create ZombieCharacter
     * @param world represents the virtual world.
     * @return ZombieCharacter
     */

    public ZombieCharacter(World world) {
        super(world, shape);
        addImage(image);
        healthCount = 100;
    }

    /**
     *  Gets the Health Count
     * @return getHealthCount
     */

    public int getHealthCount() {
        return healthCount;
    }

    /**
     *  increment players Health count
     * @return no value.
     */

    public void incrementHealthCount(){
        healthCount++;
    }

    /**
     *  decrement players health
     * @return no value.
     */

    public void decrementHealthCount(){
        healthCount--;
        System.out.println("health level reduced");
    }
}
