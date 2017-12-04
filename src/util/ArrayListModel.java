package util;

import base.BaseModel;
import base.BaseStore;
import base.SyncedList;
import lombok.Getter;

import javax.swing.*;
import java.util.List;
import java.util.function.Predicate;

public class ArrayListModel<E> extends AbstractListModel<E> implements ComboBoxModel<E> {
    @Getter protected List<E> list;

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

    public void setList(List<E> l){
        list = l;
        fireContentsChanged(this, 0, this.getSize());
    }

    public void add(E item){
        int size = getSize();
        list.add(item);
        fireIntervalAdded(this, size, size);
    }

    public void add(List<E> items){
        int size = getSize();
        int size2 = items.size();

        list.addAll(items);
        fireIntervalAdded(this, size, size+size2-1);
    }

    public void remove(E item){
        int idx = this.list.indexOf(item);
        this.list.remove(item);

        fireIntervalRemoved(this, idx, idx);
    }
    public void remove(List<E> items){
        for (E item : items) remove(item);
    }

    private E selected;
    @Override
    public void setSelectedItem(Object anItem) {
        selected = (E)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }
}
