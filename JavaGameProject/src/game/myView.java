package game;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class myView extends city.cs.engine.UserView {
    private Image background;
     CowboyCharacter cowboy;
    private Game game;

    //BufferedImage spriteSheet;

    public myView(World w, CowboyCharacter cowboy, int width, int height) {
        super(w, width, height);
        background = new ImageIcon("data/BGI.gif").getImage();
        this.cowboy = cowboy;
        this.game = game;

    }

    public myView(World w, int width, int height) {
        super(w, width, height);
        background = new ImageIcon("data/BGI.gif").getImage();

    }


    @Override
    protected void paintForeground(Graphics2D g){
        g.setFont(new Font("Ayuthaya",Font.BOLD, 15));
        g.setColor(Color.black);
        g.drawString("Score: " + cowboy.getRedBallCount(),10,60);
        g.drawString("Health: " + cowboy.getHealthCount(),10,80);
    }

    @Override
    protected void paintBackground(Graphics2D g) {

        g.drawImage(background, 0, 0, this);

    }

    public void updateBackground(String imagePath){
        background = new ImageIcon(imagePath).getImage();

    }
}
