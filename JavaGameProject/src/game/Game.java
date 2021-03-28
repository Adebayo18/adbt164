package game;

import city.cs.engine.SoundClip;

import javax.swing.*;
import java.awt.*;


/**
 * @author      Abdulazeez, Jempeji, Abdulazeez.Jempeji@city.ac.uk
 * @version     Version 1.1
 * @since       Version 1.0
 */

/**
 * A world with some bodies.
 */
public class Game {

    public CharacterController controller;

    /**
     * used to handle the control of the character.
     */

    public GameLevel getGameLevel() {
        return gameLevel;
    }

    public GameLevel gameLevel;

    /**
     * Represents the gamLevel
     */

    public myView view;
    /**
     * Initialise a new Game.
     */

    private Animation animation;

    JFrame frame;

    /**
     * A graphical display of the world (a specialised JPanel).
     */

    public Game() {


        // make the world

        gameLevel = new Level1(this);

        // make a view
        view = new myView(gameLevel, gameLevel.getCowboy(), 500, 600);
        //view.setZoom(20);

        // uncomment this to draw a 1-metre grid over the view
        //view.setGridResolution(1);
        controller = new CharacterController(gameLevel.getCowboy(),this);

        //view
        // add the view to a frame (Java top level window)a
        frame = new JFrame("Doodle Jump");
        // display the world in the window
        frame.add(view);
        // give keyboard focus to the frame whenever the mouse enters
        view.addMouseListener(new GiveFocus(frame, view));

        frame.addKeyListener(controller);

        ControlPanel controlPanel = new ControlPanel(this);
        frame.add(controlPanel.getMainPanel(), BorderLayout.WEST);


        // enable the frame to quit the application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // don't let the frame be resized
        frame.setResizable(false);
        // size the frame to fit the world view
        frame.pack();
        // finally, make the frame visible
        frame.setVisible(true);
        // get keyboard focus
        frame.requestFocusInWindow();

        // uncomment this to make a debugging view
        //JFrame debugView = new DebugViewer(world, 500, 600);

        // start our game world simulation!
        gameLevel.start();
        System.out.println("hello");

   }
    // The player in the current level
    public CowboyCharacter getPlayer() {
        return gameLevel.getCowboy();
    }

    /**
     *  Update the level of the game to a particular level
     *
     * @param  level represents any levels of the game.
     * @return no return value.
     */


    // Loads saved game file
    public void setLevel(GameLevel level) {
        //stop the current level
        this.gameLevel.stop();
        gameLevel.bgmusic.stop();
        this.gameLevel = level;
        //change the view to look into new level
        view.setWorld(this.gameLevel);

        // loads level 1 background from the saved file
        if(gameLevel instanceof Level1) {
            view.updateBackground("data/BGI.gif");

            // loads level 2 background from the saved file
        }else if (gameLevel instanceof Level2){
            view.updateBackground("data/Background2.jpeg");

            // loads level 3 background from the saved file
        }else if (gameLevel instanceof Level3){
            view.updateBackground("data/Background3.jpeg");

        }
        //cowboy in the new world
        //start the simulation in the new level
        gameLevel.start();
    }


    // Used to go to the next level in game

    public void goToNextLevel() {

        /**
         *  Moves the game to the next level.
         * @return no return value.
         */


        if (gameLevel instanceof Level1) {
            //stop the current level
            gameLevel.stop();
            //create the new (appropriate) level
            //level now refers to new level
            gameLevel = new Level2(this);
            //change the view to look into new level
            view.setWorld(gameLevel);
            //change the controller to control the
            //cowboy in the new world
            controller.updateCowboy(gameLevel.getCowboy());
            //update level 2 background
            view.updateBackground("data/Background2.jpeg");
            //start the simulation in the new level
            gameLevel.start();

        } else if (gameLevel instanceof Level2) {

            /**
             *  Moves the game to the next level.
             * @return no return value.
             */

            //stop the current level
            gameLevel.stop();
            //create the new (appropriate) level
            //level now refers to new level
            gameLevel = new Level3(this);
            //change the view to look into new level
            view.setWorld(gameLevel);
            //change the controller to control the
            //student in the new world
            controller.updateCowboy(gameLevel.getCowboy());
            //update level 3 background
            view.updateBackground("data/Background3.jpeg");
            //start the simulation in the new level
            gameLevel.start();
        } else {
            System.out.println("Well done! Game complete.");
            JDialog diaScore = new JDialog(frame, true);
            HighScore highScore = new HighScore(this);
            diaScore.getContentPane().add(highScore.getPnlScores());
            diaScore.pack();
            diaScore.setVisible(true);

        }
    }

    /**
     * Run the game.
     */
    public static void main(String[] args) {

        new Game();
    }
    // code to pause the game
    public void pause() {
        gameLevel.stop();
        gameLevel.bgmusic.stop();
    }
    // code to resume the game
    public void resume() {
        gameLevel.start();
        gameLevel.bgmusic.play();

    }
    // code to play the game sound
    public void soundPlay() {
        gameLevel.bgmusic.play();
    }

    // code to mute the game sound
    public void soundStop() {
        gameLevel.bgmusic.stop();
    }

    // code to restart each of the levels
    public void restartLevel() {

        if (gameLevel instanceof Level1
        ) {
            gameLevel.bgmusic.stop();
            gameLevel = new Level1(this);
            view.setWorld(gameLevel);
            view.updateBackground("data/BGI.gif");
            controller.updateCowboy(gameLevel.getCowboy());
            gameLevel.start();

        }else if (gameLevel instanceof Level2){
            gameLevel.bgmusic.stop();
            gameLevel = new Level2(this);
            view.setWorld(gameLevel);
            view.updateBackground("data/Background2.jpeg");
            controller.updateCowboy(gameLevel.getCowboy());
            gameLevel.start();

        }else if (gameLevel instanceof Level3){
            gameLevel.bgmusic.stop();
            gameLevel = new Level3(this);
            view.setWorld(gameLevel);
            view.updateBackground("data/Background3.jpeg");
            controller.updateCowboy(gameLevel.getCowboy());
            gameLevel.start();

        }
    }

    // code to go to each of the levels
    public void toLevelOne() {
        soundStop();
        gameLevel.stop();
        gameLevel = new Level1(this);
        view.updateBackground("data/BGI.gif");
        view.setWorld(gameLevel);
        controller.updateCowboy(gameLevel.getCowboy());
        gameLevel.start();

    }

    public void toLevelTwo() {
        soundStop();
        gameLevel.stop();
        gameLevel = new Level2(this);
        view.updateBackground("data/Background2.jpeg");
        view.setWorld(gameLevel);
        controller.updateCowboy(gameLevel.getCowboy());
        gameLevel.start();
    }

    public void toLevelThree() {
        soundStop();
        gameLevel.stop();
        gameLevel = new Level3(this);
        view.updateBackground("data/Background3.jpeg");
        view.setWorld(gameLevel);
        controller.updateCowboy(gameLevel.getCowboy());
        gameLevel.start();
    }
}