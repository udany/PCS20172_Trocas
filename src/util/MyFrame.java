package util;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public static void setNimbusLookAndFeel(){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        JFrame.setDefaultLookAndFeelDecorated(true);
    }

    public void centerOnScreen(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    public void open(Object... data){
        setVisible(true);
    }

    public void close(){
        setVisible(false);
    }
}
