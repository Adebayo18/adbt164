package game;

import city.cs.engine.BodyImage;
import city.cs.engine.Walker;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;


public class CharacterController extends KeyAdapter {

    //logic for controlling the cowboy is in Character controller
    private static final float JUMPING_VALUE = 15;
    private static final float WALKING_SPEED_VALUE = 5;


    private static final BodyImage imageR = new BodyImage("data/cowboy.png", 5f);
    private static final BodyImage imageL = new BodyImage("data/imageL.png", 5f);
    private static final BodyImage jumpRight = new BodyImage("data/jump.png", 5f);
    private static final BodyImage cowboyLeft = new BodyImage("data/cowboyLeft.gif", 5f);
    private static final BodyImage cowboyRight = new BodyImage("data/cowboyRight.gif", 5f);
    private static final BodyImage jumpLeft = new BodyImage("data/jumpLeft.png", 5f);

    //Flags for setting the cowboy jump to the left or right
    private boolean isFacingLeft = false;

    //we want this body to refer to the cowboy
    private Walker body;

   private Game game;

    public CharacterController(Walker body, Game game) {

        this.body = body;
        this.game = game;
    }


    @Override
    public void keyTyped(KeyEvent e) {
        super.keyTyped(e);
    }

    /*handle key pressed of the form 'a' 'd' 'space bar' and the arrow direction keys*/

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // other key commands omitted

        //makes the character go left
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            body.startWalking(-WALKING_SPEED_VALUE);
            body.removeAllImages();
            body.addImage(cowboyLeft);
            isFacingLeft = true;

            //makes the character go right
        } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            body.startWalking(WALKING_SPEED_VALUE);
            body.removeAllImages();
            body.addImage(cowboyRight);

            // press Q to quit the application
        } else if (code == KeyEvent.VK_Q ) {
            System.exit(0);


            // press B to drop the bomb in the application
        } else if (code == KeyEvent.VK_B ) {
            Bomb b = new Bomb(game.getGameLevel().getCowboy().getWorld(),
                    game.getGameLevel().getCowboy().getPosition().add(new Vec2(-2, 0)),
                    3000);



            //makes the character jump
        } else if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_UP) {
            body.jump(JUMPING_VALUE);
            body.removeAllImages();
            if (isFacingLeft) {
                body.addImage(jumpLeft);
                isFacingLeft = false;
            } else {
                body.addImage(jumpRight);
            }


            // Save game file
        }else if (code == KeyEvent.VK_S) {
            try {
                GameSaverLoader.save(game.getGameLevel(),"Save.txt");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            // Load game file
        } else if (code == KeyEvent.VK_L){
            try {
               GameLevel level = GameSaverLoader.load(game, "Save.txt");
                game.setLevel(level);
                game.controller.updateCowboy(game.gameLevel.getCowboy());

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            body.stopWalking();
            body.removeAllImages();
            body.addImage(imageL);

        } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            body.stopWalking();
            body.removeAllImages();
            body.addImage(imageR);
        }
    }

    public void updateCowboy(CowboyCharacter cowboyCharacter){
        this.body = cowboyCharacter;
    }
}
