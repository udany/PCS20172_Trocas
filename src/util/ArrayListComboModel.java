package util;

import base.BaseModel;

import javax.swing.*;
import java.util.List;

public class ArrayListComboModel<E> extends ArrayListModel<E> implements ComboBoxModel<E> {
    private E selected;

    public ArrayListComboModel(List<E> list){
        super(list);
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public E getElementAt(int index) {
        return list.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selected = (E)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }
}
