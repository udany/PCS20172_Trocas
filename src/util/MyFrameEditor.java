package util;

import base.BaseModel;
import model.ProductCategory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public abstract class MyFrameEditor<T extends BaseModel> extends MyFrame {
    protected T current;
    protected JPanel mainPanel;

    public Event<T> onSave = new Event<>();

    public MyFrameEditor(){
        super();

        onOpen.addListener(e -> {
            if (e.data.length > 0 && e.data[0] != null) {
                current = (T) e.data[0];
                setTitle("Editando "+getModelName());
            } else {
                current = create();
                setTitle("Criando "+getModelName());
            }

            fillForm();

            centerOnScreen();
        });
    }

    protected abstract T create();
    protected abstract void save();
    protected abstract void readForm();
    protected abstract void fillForm();
    protected String getModelName(){ return ""; }
}
