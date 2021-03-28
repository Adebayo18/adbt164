package game;

import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class MovableCharacter extends Walker {
    private BufferedImage[] idleSprite;
    private BufferedImage[] walkingSprite;
    private BufferedImage[] JumpingSprite;
    private BufferedImage[] fallingSprite;


    public MovableCharacter(World world) {
        super(world);
        //loading sprites

       /* try {
            idleSprite = new BufferedImage[1]; //stores an idle sprite image
            JumpingSprite = new BufferedImage[1];
            fallingSprite = new BufferedImage[1];
            walkingSprite = new BufferedImage[6];

            idleSprite[0] = ImageIO.read(new File("data/charSprites.png"));


        }catch (Exception e){
            e.printStackTrace();
        }
        */


    }



    public MovableCharacter(World world, Shape shape) {
        super(world, shape);
    }
}
