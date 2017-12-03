package util;

import base.BaseModel;
import base.BaseStore;
import base.SyncedList;

import javax.swing.*;
import java.util.List;
import java.util.function.Predicate;

public class ArrayListModel<E> extends AbstractListModel<E> {
    protected List<E> list;

    public ArrayListModel(List<E> list){
        this.list = list;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public E getElementAt(int index) {
        return list.get(index);
    }
}
