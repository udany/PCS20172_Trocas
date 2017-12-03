package util;

import base.BaseModel;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

public class ModelCellRenderer<T extends BaseModel>  implements ListCellRenderer {
    private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
    private Function<T, String> function;

    public ModelCellRenderer(Function<T, String> fn){
        function = fn;
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, function.apply((T) value), index, isSelected, cellHasFocus);

        return renderer;
    }
}