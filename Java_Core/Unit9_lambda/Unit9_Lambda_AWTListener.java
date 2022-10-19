import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//interface WindowClosing extends WindowListener {
//
//    public default void windowOpened(WindowEvent e) {
//    }
//
//    public default void windowIconified(WindowEvent e) {
//    }
//
//    public default void windowDeiconified(WindowEvent e) {
//    }
//
//    public default void windowActivated(WindowEvent e) {
//    }
//
//    public default void windowDeactivated(WindowEvent e) {
//    }
//
//    public default void windowClosed(WindowEvent e) {
//    }
//}
public class Unit9_Lambda_AWTListener {
    public static void main(String[] args) {
        Frame screen = new Frame();
        Button button = new Button("Press me");
        screen.add(button);
        /*c1
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Welcome to java by Example",
                        "Java Sample",JOptionPane.INFORMATION_MESSAGE);
            }
        });
         */
        button.addActionListener(e ->
                JOptionPane.showMessageDialog(null,"Info Box:Welcome to Java by Example",
                        "Java Sample",JOptionPane.INFORMATION_MESSAGE)
                );
        screen.setSize(250,400);
        screen.setVisible(true);
    }

}