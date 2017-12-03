package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MyFrame extends JFrame {
    public Event<ActionEvent> onClose;
    public Event<ActionEvent> onOpen;
    public Event<ActionEvent> onFocus;
    public Event<ActionEvent> onBlur;

    protected MyFrame(){
        onClose = new Event<>();
        onOpen = new Event<>();
        onFocus = new Event<>();
        onBlur = new Event<>();

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                open();
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                close();
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {
                onFocus.emit();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                onBlur.emit();
            }
        });
    }

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
        onOpen.emit();
        setVisible(true);
    }

    public void close(){
        onClose.emit();
        setVisible(false);
    }
}
