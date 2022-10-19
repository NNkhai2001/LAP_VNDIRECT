/*
Coder:NNKhai
Date:08/09/2022
JSE:Unit04.AWT
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class Unit4_AwtExample1 extends Frame {
    
    private List list;
    
    public Unit4_AwtExample1() {
        MenuBar menuBar = new MenuBar();
        setMenuBar(menuBar);
        Menu menu = new Menu("File");
        menuBar.add(menu);
        MenuItem menuItem = new MenuItem("Exit", new MenuShortcut('A'));
        menu.add(menuItem);
        menuItem.addActionListener(e -> {
            System.exit(1);
        });
        //setLayout(new FlowLayout());
        setLayout(new BorderLayout());
        final TextField text = new TextField();
        //text.setBackground(Color.red);
        //add(text);
        // add(text, BorderLayout.CENTER);
        add(text, BorderLayout.PAGE_START);
        list = new List(4, true);
        IntStream.range(0, 10).forEach(i -> {
            list.add("Item " + i);
        });
        add(list, BorderLayout.CENTER);
        Button pushButton = new Button("press me");
        //add(pushButton);
        //add(pushButton, BorderLayout.LINE_END);
        add(pushButton, BorderLayout.PAGE_END);
        pushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//C1:                
/*
                System.out.println(text.getText());
                AwtExample1.this.setTitle(text.getText());
                String[] values = list.getSelectedItems();
                StringBuilder builder = new StringBuilder();
                Arrays.stream(values).forEach(value -> {
                    if (builder.length() > 0) {
                        builder.append("; ");
                    }
                    builder.append(value);
                });
                text.setText(builder.toString());
                 */
//C2:

                Supplier<StringBuilder> supplier = StringBuilder::new;
                BiConsumer<StringBuilder, String> consumer = (builder, value) -> {
                    if (builder.length() > 0) {
                        builder.append(",");
                    }
                    builder.append(value);
                };
                BinaryOperator<StringBuilder> operator = StringBuilder::append;
                Function<StringBuilder, String> finisher = StringBuilder::toString;
                String[] values = list.getSelectedItems();
                Collector<String, StringBuilder, String> collector
                        = Collector.of(supplier, consumer, operator, finisher);
                text.setText(Arrays.stream(values).collect(collector));
                
            }
        });
    }
    
    public static void main(String[] args) {
        Unit4_AwtExample1 screen = new Unit4_AwtExample1();
        screen.setSize(1000, 500);
        screen.setVisible(true);
        screen.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }
}

class ShowExampleActionListener implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Unit4_AwtExample1 Screen = new Unit4_AwtExample1();
        Screen.setSize(250, 400);
        Screen.setVisible(true);
        Screen.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }
}

class SystemTrayExample {
    
    public static void main(String[] args) {
        SystemTray tray = SystemTray.getSystemTray();
        Icon icon = UIManager.getIcon("OptionPane.informationIcon");
        Image image = ((ImageIcon) icon).getImage();
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo", null);
        trayIcon.addActionListener(new ShowExampleActionListener());
        try {
            tray.add(trayIcon);
        } catch (AWTException ex) {
            Logger.getLogger(SystemTrayExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

interface WindowClosing extends WindowListener {
    
    public default void windowOpened(WindowEvent e) {
    }
    
    public default void windowIconified(WindowEvent e) {
    }
    
    public default void windowDeiconified(WindowEvent e) {
    }
    
    public default void windowActivated(WindowEvent e) {
    }
    
    public default void windowDeactivated(WindowEvent e) {
    }
    
    public default void windowClosed(WindowEvent e) {
    }
}

class RobotExample {
    
    static void leftClick(Robot robot) {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(200);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(200);
    }
    
    static void type(Robot robot, String s) {
        byte[] bytes = s.getBytes();
        for (byte b : bytes) {
            int code = b;
            if (code > 96 && code < 123) {
                code = code - 32;
            }
            robot.delay(40);
            robot.keyPress(code);
            robot.keyRelease(code);
        }
    }
    
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
            robot.delay(2 * 1000);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            robot.mouseMove((int) dim.getWidth() / 2, (int) dim.getHeight() / 2);
            leftClick(robot);
            String userDir = System.getProperty("user.home");
            File file = new File(new File(userDir), "temp.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            Desktop.getDesktop().edit(file);
            robot.delay(500);
            type(robot, "Hello,world");
            robot.delay(5 * 1000);
            System.exit(0);
            
        } catch (AWTException ex) {
            
        } catch (IOException ex) {
            
        }
        
    }
}
