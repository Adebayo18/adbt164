package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author      Abdulazeez, Jempeji, Abdulazeez.Jempeji@city.ac.uk
 * @version     Version 1.1
 * @since       Version 1.0
 */


public class ControlPanel {


    /**
     * Represents the mainPanel of JPanel .
     */
    private JPanel mainPanel;

    /**
     * Represents the resumeButton.
     */
    private JButton resumeButton;

    /**
     * Represents the pauseButton.
     */
    private JButton pauseButton;

    /**
     * Represents the quitButton.
     */
    private JButton quitButton;

    /**
     * Represents the soundButton.
     */
    private JButton soundButton;

    /**
     * Represents the restartButton.
     */
    private JButton restartButton;

    /**
     * Represents the muteButton.
     */
    private JButton muteButton;

    /**
     * Represents the level3Button.
     */
    private JButton level3Button;

    /**
     * Represents the level2Button.
     */
    private JButton level2Button;

    /**
     * Represents the level1Button.
     */
    private JButton level1Button;

    /**
     * Represents the saveGameButton.
     */
    private JButton  saveGameButton;

    /**
     * Represents the loadGameButton.
     */
    private JButton  loadGameButton;



    /**
     * Represents the Game Object
     */
    private Game game;



    /**
     *  Creates the mainPanel
     * @param  mainPanel represents the references of the game object
     * @return no value
     */

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public ControlPanel(Game game) {
        this.game = game;
        // Code used for the Resume button on the GUI
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.resume();

            }
        });
        // Code used for the Pause button on the GUI
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.pause();

            }
        });
        // Code used for the Quit button on the GUI
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
        // Code used for the Sound button on the GUI
        soundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.soundPlay();
            }
        });
        // Code used for the Restart button on the GUI
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.restartLevel();

            }
        });
        // Code used for the Mute button on the GUI
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.soundStop();
            }
        });
        // Code used to move to level 3 on the GUI
        level3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.toLevelThree();

            }
        });
        // Code used to move to level 2 on the GUI
        level2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.toLevelTwo();

            }
        });
        // Code used to move to level 1 on the GUI
        level1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.toLevelOne();

            }
        });
        // Code for Save Game button used on the GUI
        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GameSaverLoader.save(game.getGameLevel(), "Save.txt");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                System.out.println("Level saved successful");
            }
        });

        // Code for Load Game button used on the GUI
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GameLevel level = GameSaverLoader.load(game, "Save.txt");
                    game.setLevel(level);
                    game.controller.updateCowboy(game.gameLevel.getCowboy());

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.out.println("Level loaded successful");
            }
        });

    }
        public JPanel getMainPanel () {
            return mainPanel;
        }
}
