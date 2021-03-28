package game;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GiveFocus implements MouseListener {

    private myView view;
    JFrame f;

    public GiveFocus(JFrame f,myView v){
        this.view = v;
        this.f = f;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
      //  view.requestFocus();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
      //  view.toFront();
        f.toFront();
        f.requestFocus();
     // view.requestFocus();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
