/*
Coder:NNKhai
Date:08/09/2022
JSE:Unit04.AWT
*/

import java.awt.*;

public class Unit4_AWTSimple {

    public static void main(String[] args) {
        Frame screen = new Frame();
        screen.setLayout(null);
        Panel panel = new Panel();
        panel.setBounds(30, 60, 50, 70);
        panel.setBackground(Color.red);
        screen.add(panel);
        screen.setSize(300, 200);
        screen.setVisible(true);
    }
}

