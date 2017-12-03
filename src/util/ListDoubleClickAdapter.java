package util;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class ListDoubleClickAdapter<T> extends MouseAdapter {
    private Consumer<T> function;

    public ListDoubleClickAdapter(Consumer<T> fn){
        function = fn;
    }


    @Override
    public void mouseClicked(MouseEvent evt) {
        JList list = (JList) evt.getSource();

        if (evt.getClickCount() == 2) {
            // Double-click detected
            int index = list.locationToIndex(evt.getPoint());
            Object item = list.getModel().getElementAt(index);
            function.accept((T)item);
        }
    }
}
