/*
Coder:NNKhai
Date:08/09/2022
JSE:Unit04.AWT
*/
import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Unit4_CheckBoxExample extends Frame {
    public Unit4_CheckBoxExample() {
        setLayout(new FlowLayout());
        Checkbox checkbox1 = new Checkbox("Checkbox1");
        checkbox1.setFont(new Font("Arial",Font.BOLD,14));
        add(checkbox1);
        final  Checkbox checkbox2 = new Checkbox("Checkbox2");
        add(checkbox2);
        checkbox2.addItemListener((e -> {
            System.out.println("value 2 = "+checkbox2.getState());
        }));
    }
    public static void main(String[] args) {
        Unit4_CheckBoxExample screen = new Unit4_CheckBoxExample();
        screen.setSize(1000, 500);
        screen.setVisible(true);
        screen.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }
}